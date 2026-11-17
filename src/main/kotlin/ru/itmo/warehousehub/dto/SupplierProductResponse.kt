package ru.itmo.warehousehub.dto

import java.math.BigDecimal

class SupplierProductResponse(
    var id: Long?,
    var supplierId: Long?,
    var productId: Long?,
    var supplyPrice: BigDecimal?,
    var leadTimeDays: Int?
)