package ru.itmo.warehousehub.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PositiveOrZero

class StockItemRequest(
    @field:NotNull(message = "ID товара обязателен")
    var productId: Long? = null,

    @field:NotNull(message = "ID склада обязателен")
    var warehouseId: Long? = null,

    @field:NotNull(message = "Количество обязательно")
    @field:PositiveOrZero(message = "Количество не может быть отрицательным")
    var quantity: Int? = null,

    @field:NotNull(message = "Резерв обязателен")
    @field:PositiveOrZero(message = "Резерв не может быть отрицательным")
    var reserved: Int? = null
)