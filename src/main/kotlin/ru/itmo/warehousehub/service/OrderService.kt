package ru.itmo.warehousehub.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.itmo.warehousehub.dto.*
import ru.itmo.warehousehub.entity.Order
import ru.itmo.warehousehub.entity.OrderItem
import ru.itmo.warehousehub.repository.*
import java.math.BigDecimal

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository,
    private val userRepository: UserRepository,
    private val productRepository: ProductRepository,
    private val stockItemRepository: StockItemRepository
) {
    fun getAllOrders(): List<OrderResponse> {
        return orderRepository.findAll().map { order ->
            OrderResponse(
                id = order.id,
                customerId = order.customer.id,
                status = order.status,
                totalAmount = order.totalAmount,
                createdAt = order.createdAt,
                items = order.items.map { item ->
                    OrderItemResponse(
                        id = item.id,
                        productId = item.product.id,
                        productName = item.product.name,
                        quantity = item.quantity,
                        priceAtOrder = item.priceAtOrder
                    )
                }
            )
        }
    }

    @Transactional // <-- ВОТ ОНА, ТРАНЗАКЦИЯ!
    fun createOrder(request: OrderRequest): OrderResponse {
        // 1. Ищем клиента
        val customer = userRepository.findById(request.customerId!!).orElseThrow {
            IllegalArgumentException("Клиент с ID ${request.customerId} не найден")
        }

        // 2. Создаем сам заказ (пока без суммы)
        val order = Order(
            customer = customer,
            status = "CREATED",
            totalAmount = BigDecimal.ZERO
        )
        val savedOrder = orderRepository.save(order)
        var totalAmount = BigDecimal.ZERO

        // 3. Обрабатываем каждую позицию
        val orderItems = request.items.map { itemReq ->
            val product = productRepository.findById(itemReq.productId!!).orElseThrow {
                IllegalArgumentException("Товар с ID ${itemReq.productId} не найден")
            }

            // Ищем остатки на складе (берем первый попавшийся склад, где есть товар)
            // В реальной системе тут нужно выбирать конкретный склад, но для простоты берем любой
            val stockItems = stockItemRepository.findAll().filter { it.product.id == product.id }
            
            if (stockItems.isEmpty()) {
                throw IllegalArgumentException("Товар ${product.name} отсутствует на складах")
            }

            // Проверяем, хватает ли товара на первом складе
            val stockItem = stockItems.first()
            val available = stockItem.quantity - stockItem.reserved
            if (available < itemReq.quantity!!) {
                throw IllegalArgumentException("Товара ${product.name} недостаточно. Доступно: $available, нужно: ${itemReq.quantity}")
            }

            // Резервируем товар
            stockItem.reserved += itemReq.quantity!!
            stockItemRepository.save(stockItem)

            // Создаем позицию заказа
            val price = product.price
            totalAmount = totalAmount.add(price.multiply(BigDecimal(itemReq.quantity)))

            OrderItem(
                order = savedOrder,
                product = product,
                quantity = itemReq.quantity!!,
                priceAtOrder = price
            )
        }

        // 4. Сохраняем позиции
        val savedItems = orderItemRepository.saveAll(orderItems)

        // 5. Обновляем общую сумму заказа
        savedOrder.totalAmount = totalAmount
        savedOrder.items = savedItems.toMutableList()
        orderRepository.save(savedOrder)

        // 6. Формируем ответ
        return OrderResponse(
            id = savedOrder.id,
            customerId = customer.id,
            status = savedOrder.status,
            totalAmount = savedOrder.totalAmount,
            createdAt = savedOrder.createdAt,
            items = savedItems.map { item ->
                OrderItemResponse(
                    id = item.id,
                    productId = item.product.id,
                    productName = item.product.name,
                    quantity = item.quantity,
                    priceAtOrder = item.priceAtOrder
                )
            }
        )
    }
}