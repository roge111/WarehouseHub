package ru.itmo.warehousehub.entity


import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime


@Entity
@Table(name = "suppliers")
class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false)
    var name: String? = null

    @Column(name = "contact_email", nullable = true)
    var contactEmail: String? = null
}
