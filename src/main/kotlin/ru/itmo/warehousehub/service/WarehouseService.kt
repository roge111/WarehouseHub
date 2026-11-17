package ru.itmo.warehousehub.service

import org.springframework.stereotype.Service
import ru.itmo.warehousehub.entity.Warehouse
import ru.itmo.warehousehub.repository.WarehouseRepository

@Service
class WarehouseService(
    private val warehouseRepository: WarehouseRepository
) {
    fun getAllWarehouses(): List<Warehouse> {
        return warehouseRepository.findAll()
    }

    fun createWarehouse(warehouse: Warehouse): Warehouse {
        if (warehouse.id != null) {
            throw IllegalArgumentException("ID склада должен быть пустым")
        }
        return warehouseRepository.save(warehouse)
    }

    fun updateWarehouse(id: Long, updatedWarehouse: Warehouse): Warehouse {
        val warehouse = warehouseRepository.findById(id).orElseThrow {
            IllegalArgumentException("Склад с ID $id не найден")
        }
        warehouse.name = updatedWarehouse.name
        warehouse.address = updatedWarehouse.address
        return warehouseRepository.save(warehouse)
    }

    fun deleteWarehouse(id: Long) {
        warehouseRepository.deleteById(id)
    }
}