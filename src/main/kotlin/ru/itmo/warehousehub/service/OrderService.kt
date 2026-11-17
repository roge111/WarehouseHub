package ru.itmo.warehousehub.service

import org.springframework.stereotype.Service
import ru.itmo.warehousehub.entity.Order
import ru.itmo.warehousehub.repository.OrderRepository

@Service
class OrderService(
    private val orderRepository: OrderRepository
) {
    fun getAllOrders(): List<Order> {
        return orderRepository.findAll()
    }

    fun createOrder(order: Order): Order {
        if (order.id != null) {
            throw IllegalArgumentException("ID заказа должен быть пустым")
        }
        return orderRepository.save(order)
    }
    
    fun updateStatus(id: Long, status: String): Order {
        val order = orderRepository.findById(id).orElseThrow {
            IllegalArgumentException("Заказ с ID $id не найден")
        }
        order.status = status
        return orderRepository.save(order)
    }

    fun deleteOrder(id: Long) {
        orderRepository.deleteById(id)
    }
}