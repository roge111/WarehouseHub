package ru.itmo.warehousehub.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.itmo.warehousehub.entity.SupplierProduct

interface SupplierProductRepository : JpaRepository<SupplierProduct, Long>