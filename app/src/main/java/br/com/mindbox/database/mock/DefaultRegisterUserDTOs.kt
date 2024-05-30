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
                    birthDate = format.parse("1980-02-01")!!
                ),
                RegisterUserDTO(
                    email = "claudio@locaweb.com.br",
                    password = "Senha@123",
                    fullName = "Claudio Gora",
                    birthDate = format.parse("1980-02-01")!!
                ),
                RegisterUserDTO(
                    email = "gabriel@locaweb.com.br",
                    password = "Senha@123",
                    fullName = "Gabriel Tavares",
                    birthDate = format.parse("1980-02-01")!!
                ),
                RegisterUserDTO(
                    email = "daniel@locaweb.com.br",
                    password = "Senha@123",
                    fullName = "Daniel Wilson",
                    birthDate = format.parse("1990-04-22")!!
                ),
                RegisterUserDTO(
                    email = "alexia@locaweb.com.br",
                    password = "Senha@123",
                    fullName = "Alexia",
                    birthDate = format.parse("1978-11-30")!!
                ),
                RegisterUserDTO(
                    email = "gui@locaweb.com.br",
                    password = "Senha@123",
                    fullName = "Ivanor Guilherme",
                    birthDate = format.parse("1978-11-30")!!
                ),
            )
        }
    }
}