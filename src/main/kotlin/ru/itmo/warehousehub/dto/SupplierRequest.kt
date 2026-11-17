package ru.itmo.warehousehub.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

class SupplierRequest(
    @field:NotBlank(message = "Название поставщика не может быть пустым")
    var name: String? = null,

    @field:Email(message = "Некорректный формат email")
    var contactEmail: String? = null
)