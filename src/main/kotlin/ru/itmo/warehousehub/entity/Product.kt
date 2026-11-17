package ru.itmo.warehousehub.entity


import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime


@Entity
@Table(name = "products")
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false, unique = true, length = 64)
    var sku: String? = null

    @Column(nullable = false, unique = true)
    var name: String? = null


    @Column(nullable = false, precision = 10, scale = 2)
    var price: BigDecimal = BigDecimal.ZERO

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    
    @OneToMany(mappedBy = "product")
    var stockItems: MutableList<StockItem> = mutableListOf()

}