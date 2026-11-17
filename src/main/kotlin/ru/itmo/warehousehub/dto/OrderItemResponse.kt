package ru.itmo.warehousehub.dto

import java.math.BigDecimal

class OrderItemResponse(
    var id: Long?,
    var productId: Long?,
    var productName: String?, // Удобно сразу отдать имя товара
    var quantity: Int?,
    var priceAtOrder: BigDecimal?
)