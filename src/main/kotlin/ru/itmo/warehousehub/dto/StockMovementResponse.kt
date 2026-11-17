package ru.itmo.warehousehub.dto

import java.time.LocalDateTime

class StockMovementResponse(
    var id: Long?,
    var stockItemId: Long?,
    var delta: Int?,
    var reason: String?,
    var createdAt: LocalDateTime?
)