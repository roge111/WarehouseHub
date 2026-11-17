package ru.itmo.warehousehub.service

import org.springframework.stereotype.Service
import ru.itmo.warehousehub.entity.StockMovement
import ru.itmo.warehousehub.repository.StockMovementRepository

@Service
class StockMovementService(
    private val stockMovementRepository: StockMovementRepository
) {
    fun getAllStockMovements(): List<StockMovement> {
        return stockMovementRepository.findAll()
    }

    fun createStockMovement(stockMovement: StockMovement): StockMovement {
        if (stockMovement.id != null) {
            throw IllegalArgumentException("ID движения должен быть пустым")
        }
        return stockMovementRepository.save(stockMovement)
    }

    fun deleteStockMovement(id: Long) {
        stockMovementRepository.deleteById(id)
    }
}