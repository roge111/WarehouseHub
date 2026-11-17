package ru.itmo.warehousehub.dto

import jakarta.validation.constraints.NotBlank

class WarehouseRequest(
    @field:NotBlank(message = "Название склада не может быть пустым")
    var name: String? = null,

    @field:NotBlank(message = "Адрес не может быть пустым")
    var address: String? = null
)