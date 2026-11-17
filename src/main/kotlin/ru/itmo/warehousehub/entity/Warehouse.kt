package ru.itmo.warehousehub.entity


import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "warehouses")
class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null


    @Column(nullable = false, unique = true)
    var name: String? = null

    @Column(nullable = false, length = 512)
    var address: String? = null

    // Внутри класса Warehouse, в самом низу:
    @OneToMany(mappedBy = "warehouse")
    var stockItems: MutableList<StockItem> = mutableListOf()

}