package ru.itmo.warehousehub.dto

import java.math.BigDecimal
import java.time.LocalDateTime


class OrderResponse(
    var id: Long?,
    var customerId: Long?, // Отдаем только ID, а не всего пользователя
    var status: String?,
    var totalAmount: BigDecimal?,
    var createdAt: LocalDateTime?,
    var items: List<OrderItemResponse> = mutableListOf()
)