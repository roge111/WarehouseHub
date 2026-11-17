package ru.itmo.warehousehub.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.itmo.warehousehub.entity.Order

interface OrderRepository : JpaRepository<Order, Long>
