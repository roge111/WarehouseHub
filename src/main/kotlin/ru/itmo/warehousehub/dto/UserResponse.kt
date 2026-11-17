package ru.itmo.warehousehub.dto

import java.time.LocalDateTime

class UserResponse(
    var id: Long?,
    var email: String?,
    var fullName: String?,
    var role: String?,
    var createdAt: LocalDateTime? // <-- Пароль НЕ отдаем!
)