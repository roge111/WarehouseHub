package ru.itmo.warehousehub.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

class OrderItemRequest(
    @field:NotNull(message = "ID товара обязателен")
    var productId: Long? = null,

    @field:NotNull(message = "Количество обязательно")
    @field:Positive(message = "Количество должно быть больше нуля")
    var quantity: Int? = null
)