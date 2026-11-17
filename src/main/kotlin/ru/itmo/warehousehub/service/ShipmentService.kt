package ru.itmo.warehousehub.service

import org.springframework.stereotype.Service
import ru.itmo.warehousehub.entity.*
import ru.itmo.warehousehub.repository.ShipmentRepository


@Service
class ShipmentService(
    private val shipmentRepository: ShipmentRepository
) {
    fun getAllShipments(): List<Shipment> {
        return shipmentRepository.findAll()
    }

    fun createShipment(shipment: Shipment): Shipment {
        if (shipment.id != null) {
            throw IllegalArgumentException("Id отгрузки должен быть пустым")
        }
        return shipmentRepository.save(shipment)
    }

    fun updateCourrier(id: Long, user: User): Shipment {
        if (user.id == null) {
            throw IllegalArgumentException("Id пользователя не должен быть пустым")
        }

        if (user.role != "COURIER") {
            throw IllegalArgumentException("Пользователь должен быть курьером")
        }

        val shipment = shipmentRepository.findById(id).orElseThrow {
            IllegalArgumentException("Отгрузка с id $id не найдена")
        }
        shipment.courier = user
        return shipmentRepository.save(shipment)
    }

    fun updateShipmentStatus(id: Long, status: String): Shipment {

        val shipment = shipmentRepository.findById(id).orElseThrow {
            IllegalArgumentException("Отгрузка с id $id не найдена")
        }
        shipment.status = status
        return shipmentRepository.save(shipment)
    }
}
   