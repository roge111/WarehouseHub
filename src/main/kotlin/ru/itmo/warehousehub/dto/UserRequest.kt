package ru.itmo.warehousehub.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

class UserRequest(
    @field:NotBlank(message = "Email не может быть пустым")
    @field:Email(message = "Некорректный формат email")
    var email: String? = null,

    @field:NotBlank(message = "Имя не может быть пустым")
    var fullName: String? = null,

    @field:NotBlank(message = "Роль не может быть пустой")
    var role: String? = null,

    @field:NotBlank(message = "Пароль не может быть пустым")
    var passwordHash: String? = null // В реальности тут был бы plain password, но пока оставим так
)