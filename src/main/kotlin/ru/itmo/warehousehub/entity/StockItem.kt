package ru.itmo.warehousehub.entity

import jakarta.persistence.*

@Entity
@Table(name = "stock_items")
class StockItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    // Many-to-One к Product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    lateinit var product: Product

    // Many-to-One к Warehouse
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    lateinit var warehouse: Warehouse

    @Column(nullable = false)
    var quantity: Int = 0

    @Column(nullable = false)
    var reserved: Int = 0
}