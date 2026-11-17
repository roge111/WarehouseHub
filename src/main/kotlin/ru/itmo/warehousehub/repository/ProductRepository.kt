package ru.itmo.warehousehub.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.itmo.warehousehub.entity.Product

interface ProductRepository : JpaRepository<Product, Long>
