package ru.itmo.warehousehub.dto

import java.time.LocalDateTime

class ShipmentResponse(
    var id: Long?,
    var orderId: Long?,
    var courierId: Long?,
    var status: String?,
    var dispatchedAt: LocalDateTime?,
    var deliveredAt: LocalDateTime?
)