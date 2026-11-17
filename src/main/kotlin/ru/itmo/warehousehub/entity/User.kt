package ru.itmo.warehousehub.entity


import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime


@Entity
@Table(name = "users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false, unique = true)
    var email: String? = null

    @Column(name = "full_name", nullable = false)
    var fullName: String? = null

    @Column(nullable = false, length = 32)
    var role: String? = null

    @Column(name = "password_hash", nullable = false)
    var passwordHash: String? = null

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
}