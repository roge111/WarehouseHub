package ru.itmo.warehousehub.dto

import jakarta.validation.constraints.NotNull

class StockMovementRequest(
    @field:NotNull(message = "ID остатка обязателен")
    var stockItemId: Long? = null,

    @field:NotNull(message = "Дельта обязательна")
    var delta: Int? = null,

    @field:NotNull(message = "Причина обязательна")
    var reason: String? = null
)