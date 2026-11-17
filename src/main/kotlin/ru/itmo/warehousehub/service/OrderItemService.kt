package ru.itmo.warehousehub.service

import org.springframework.stereotype.Service
import ru.itmo.warehousehub.entity.OrderItem
import ru.itmo.warehousehub.repository.OrderItemRepository

@Service
class OrderItemService(
    private val orderItemRepository: OrderItemRepository
) {
    fun getAllOrderItems(): List<OrderItem> {
        return orderItemRepository.findAll()
    }

    fun createOrderItem(orderItem: OrderItem): OrderItem {
        if (orderItem.id != null) {
            throw IllegalArgumentException("ID позиции заказа должен быть пустым")
        }
        return orderItemRepository.save(orderItem)
    }

    fun updateQuantity(id: Long, newQuantity: Int): OrderItem {
        val orderItem = orderItemRepository.findById(id).orElseThrow {
            IllegalArgumentException("Позиция заказа с ID $id не найдена")
        }
        orderItem.quantity = newQuantity
        return orderItemRepository.save(orderItem)
    }

    fun deleteOrderItem(id: Long) {
        orderItemRepository.deleteById(id)
    }
}