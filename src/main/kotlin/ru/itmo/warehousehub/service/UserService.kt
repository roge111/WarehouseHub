package ru.itmo.warehousehub.service

import org.springframework.stereotype.Service
import ru.itmo.warehousehub.entity.User
import ru.itmo.warehousehub.repository.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository
) {
    // Получить всех пользователей
    fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }

    // Создать пользователя (зарегистрировать)
    fun createUser(user: User): User { 
        
        // Запрещаем создавать пользователя с уже существующим ID (это защита от подмены)
        if (user.id != null) { 
            throw IllegalArgumentException("При создании пользователя ID должен быть null")
        }
        return userRepository.save(user)
    }

    fun updateRole(id: Long, newRole: String) {
        val user = userRepository.findById(id).orElseThrow { 
            IllegalArgumentException("Пользователь с ID $id не найден") 
        }
        user.role = newRole
        userRepository.save(user)
    }

    // Удалить пользователя (в бан)
    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }
}