package ru.itmo.warehousehub.dto

import jakarta.validation.constraints.NotNull

class ShipmentRequest(
    @field:NotNull(message = "ID заказа обязателен")
    var orderId: Long? = null,

    var courierId: Long? = null, // Может быть null, если курьер еще не назначен

    @field:NotNull(message = "Статус не может быть пустым")
    var status: String? = null
)