package ru.itmo.warehousehub.service

import org.springframework.stereotype.Service
import ru.itmo.warehousehub.entity.Product
import ru.itmo.warehousehub.repository.ProductRepository

@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    // Получить все товары
    fun getAllProducts(): List<Product> {
        return productRepository.findAll()
    }

    // Создать товар
    fun createProduct(product: Product): Product {
        return productRepository.save(product)
    }

    // Удалить товар
    fun deleteProduct(id: Long) {
        productRepository.deleteById(id)
    }
}