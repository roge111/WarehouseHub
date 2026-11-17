package ru.itmo.warehousehub.service

import org.springframework.stereotype.Service
import ru.itmo.warehousehub.entity.StockItem
import ru.itmo.warehousehub.repository.StockItemRepository

@Service
class StockItemService(
    private val stockItemRepository: StockItemRepository
) {
    fun getAllStockItems(): List<StockItem> {
        return stockItemRepository.findAll()
    }

    fun createStockItem(stockItem: StockItem): StockItem {
        if (stockItem.id != null) {
            throw IllegalArgumentException("ID остатка должен быть пустым")
        }
        return stockItemRepository.save(stockItem)
    }

    fun updateQuantity(id: Long, newQuantity: Int): StockItem {
        val stockItem = stockItemRepository.findById(id).orElseThrow {
            IllegalArgumentException("Остаток с ID $id не найден")
        }
        stockItem.quantity = newQuantity
        return stockItemRepository.save(stockItem)
    }

    fun deleteStockItem(id: Long) {
        stockItemRepository.deleteById(id)
    }
}