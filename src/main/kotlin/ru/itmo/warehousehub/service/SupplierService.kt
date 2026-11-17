package ru.itmo.warehousehub.service

import org.springframework.stereotype.Service
import ru.itmo.warehousehub.entity.Supplier
import ru.itmo.warehousehub.repository.SupplierRepository

@Service
class SupplierService(
    private val supplierRepository: SupplierRepository
) {
    fun getAllSuppliers(): List<Supplier> {
        return supplierRepository.findAll()
    }

    fun createSupplier(supplier: Supplier): Supplier {
        if (supplier.id != null) {
            throw IllegalArgumentException("ID поставщика должен быть пустым")
        }
        return supplierRepository.save(supplier)
    }

    fun updateSupplier(id: Long, updatedSupplier: Supplier): Supplier {
        val supplier = supplierRepository.findById(id).orElseThrow {
            IllegalArgumentException("Поставщик с ID $id не найден")
        }
        supplier.name = updatedSupplier.name
        supplier.contactEmail = updatedSupplier.contactEmail
        return supplierRepository.save(supplier)
    }

    fun deleteSupplier(id: Long) {
        supplierRepository.deleteById(id)
    }
}