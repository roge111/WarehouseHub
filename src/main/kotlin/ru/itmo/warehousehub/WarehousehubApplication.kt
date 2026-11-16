package ru.itmo.warehousehub

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WarehousehubApplication

fun main(args: Array<String>) {
	runApplication<WarehousehubApplication>(*args)
}
