package ru.itmo.warehousehub.entity

import jakarta.persistence.*
import java.math.BigDecimal
import com.fasterxml.jackson.annotation.JsonIgnore

@Entity
@Table(name = "order_items")
class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @JsonIgnore
    // Связь Many-to-One: много позиций могут ссылаться на один заказ
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    lateinit var order: Order

    // Связь Many-to-One: много позиций могут ссылаться на один товар
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    lateinit var product: Product

    @Column(nullable = false)
    var quantity: Int = 0

    @Column(name = "price_at_order", nullable = false)
    var priceAtOrder: BigDecimal = BigDecimal.ZERO
}