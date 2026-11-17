package ru.itmo.warehousehub.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.math.BigDecimal

class SupplierProductRequest(
    @field:NotNull(message = "ID поставщика обязателен")
    var supplierId: Long? = null,

    @field:NotNull(message = "ID товара обязателен")
    var productId: Long? = null,

    @field:NotNull(message = "Цена поставки обязательна")
    @field:Positive(message = "Цена должна быть больше нуля")
    var supplyPrice: BigDecimal? = null,

    var leadTimeDays: Int? = null
)