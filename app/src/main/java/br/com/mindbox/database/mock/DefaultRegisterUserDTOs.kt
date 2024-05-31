package br.com.mindbox.database.mock

import br.com.mindbox.dto.user.RegisterUserDTO
import br.com.mindbox.util.date.DateUtils

class DefaultRegisterUserDTOs {
    companion object {
        fun get(): List<RegisterUserDTO> {
            val format = DateUtils.getFormat()
            return listOf(
                RegisterUserDTO(
                    email = "gilberto@locaweb.com.br",
                    password = "Senha@123",
                    fullName = "Gilberto Mautner",
                    birthDate = format.parse("1980-02-01")!!,
                    profilePictureUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSNKLtKtXUNoblLiayb3UEqN1n5rnJFQuFqpw&s"
                ),
                RegisterUserDTO(
                    email = "claudio@locaweb.com.br",
                    password = "Senha@123",
                    fullName = "Claudio Gora",
                    birthDate = format.parse("1980-02-01")!!,
                    profilePictureUrl = "https://images.pexels.com/photos/2379004/pexels-photo-2379004.jpeg"
                ),
                RegisterUserDTO(
                    email = "gabriel@locaweb.com.br",
                    password = "Senha@123",
                    fullName = "Gabriel Tavares",
                    birthDate = format.parse("1980-02-01")!!,
                    profilePictureUrl = "https://pixahive.com/wp-content/uploads/2020/10/A-smiling-man-134569-pixahive.jpg"
                ),
                RegisterUserDTO(
                    email = "daniel@locaweb.com.br",
                    password = "Senha@123",
                    fullName = "Daniel Wilson",
                    birthDate = format.parse("1990-04-22")!!,
                    profilePictureUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQvoo5t2zI3tU-p9AkfrP3exD8PP1NuC5oY7g&s"
                ),
                RegisterUserDTO(
                    email = "alexia@locaweb.com.br",
                    password = "Senha@123",
                    fullName = "Alexia",
                    birthDate = format.parse("1978-11-30")!!,
                    profilePictureUrl = "https://i1.pickpik.com/photos/159/426/423/people-happy-people-portrait-preview.jpg"
                ),
                RegisterUserDTO(
                    email = "gui@locaweb.com.br",
                    password = "Senha@123",
                    fullName = "Ivanor Guilherme",
                    birthDate = format.parse("1978-11-30")!!,
                    profilePictureUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS1kMpkuQhu1PhADQLga7wL7zn-A5g4yoLNAQ&s"
                ),
            )
        }
    }
}