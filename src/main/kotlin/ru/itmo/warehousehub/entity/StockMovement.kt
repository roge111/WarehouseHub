package ru.itmo.warehousehub.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "stock_movements")
class StockMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_item_id", nullable = false)
    lateinit var stockItem: StockItem

    @Column(nullable = false)
    var delta: Int = 0

    @Column(nullable = false, length = 64)
    var reason: String? = null

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
}