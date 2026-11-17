package ru.itmo.warehousehub.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "supplier_products")
class SupplierProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    lateinit var supplier: Supplier

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    lateinit var product: Product

    @Column(name = "supply_price", nullable = false, precision = 12, scale = 2)
    var supplyPrice: BigDecimal = BigDecimal.ZERO

    @Column(name = "lead_time_days")
    var leadTimeDays: Int? = null
}