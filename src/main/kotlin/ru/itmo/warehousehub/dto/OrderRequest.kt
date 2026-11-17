package ru.itmo.warehousehub.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

class OrderRequest(
    @field:NotNull(message = "ID клиента не может быть пустым")
    var customerId: Long? = null,

    @field:NotEmpty(message = "Заказ должен содержать хотя бы один товар")
    var items: List<OrderItemRequest> = mutableListOf()
)