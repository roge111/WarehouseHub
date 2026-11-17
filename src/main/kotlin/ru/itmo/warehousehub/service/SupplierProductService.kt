package ru.itmo.warehousehub.service

import org.springframework.stereotype.Service
import ru.itmo.warehousehub.entity.SupplierProduct
import ru.itmo.warehousehub.repository.SupplierProductRepository

@Service
class SupplierProductService(
    private val supplierProductRepository: SupplierProductRepository
) {
    fun getAllSupplierProducts(): List<SupplierProduct> {
        return supplierProductRepository.findAll()
    }

    fun createSupplierProduct(supplierProduct: SupplierProduct): SupplierProduct {
        if (supplierProduct.id != null) {
            throw IllegalArgumentException("ID связи поставщика и товара должен быть пустым")
        }
        return supplierProductRepository.save(supplierProduct)
    }

    fun updateSupplyPrice(id: Long, newPrice: java.math.BigDecimal): SupplierProduct {
        val supplierProduct = supplierProductRepository.findById(id).orElseThrow {
            IllegalArgumentException("Связь с ID $id не найдена")
        }
        supplierProduct.supplyPrice = newPrice
        return supplierProductRepository.save(supplierProduct)
    }

    fun deleteSupplierProduct(id: Long) {
        supplierProductRepository.deleteById(id)
    }
}