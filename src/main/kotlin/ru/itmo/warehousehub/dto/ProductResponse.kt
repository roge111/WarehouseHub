package ru.itmo.warehousehub.dto

import java.math.BigDecimal

class ProductResponse(
    var id: Long?,
    var sku: String?,
    var name: String?,
    var price: BigDecimal?
)