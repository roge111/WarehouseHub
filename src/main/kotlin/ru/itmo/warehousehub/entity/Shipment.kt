package ru.itmo.warehousehub.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "shipments")
class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    lateinit var order: Order

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courier_id")
    var courier: User? = null

    @Column(nullable = false)
    var status: String? = null

    @Column(name = "dispatched_at")
    var dispatchedAt: LocalDateTime? = null

    @Column(name = "delivered_at")
    var deliveredAt: LocalDateTime? = null
}