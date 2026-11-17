package ru.itmo.warehousehub.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.math.BigDecimal

class ProductRequest(
    @field:NotBlank(message = "SKU не может быть пустым")
    var sku: String? = null,

    @field:NotBlank(message = "Название не может быть пустым")
    var name: String? = null,

    @field:NotNull(message = "Цена обязательна")
    @field:Positive(message = "Цена должна быть больше нуля")
    var price: BigDecimal? = null
)