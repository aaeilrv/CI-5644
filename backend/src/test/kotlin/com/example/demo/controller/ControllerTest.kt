package com.example.demo.controller

import com.example.demo.model.User
import com.example.demo.repo.Repository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType

import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest
class ControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var repository : Repository


    /*
    * Funcion calificada como un prototipo no funcional para
    * hacer unit testing de la funcion save del controlador web
    * de momento no creo que podamos hacer correctamente este test
    * porque solo sabemos hacer mock de una respuesta del repositorio
    * y dicha funcion save, realiza varios guardados llamando al repositorio
    * de Users, lo que complica el hacer el mock para verificar.
    *
    * */
    @Test
    fun checkPostUsers() {
       mockMvc.perform(MockMvcRequestBuilders.post("/webTest/save")
           .contentType(MediaType.APPLICATION_JSON)
           .accept(MediaType.APPLICATION_JSON)
           .content("")
       )
           .andExpect(MockMvcResultMatchers.status().isOk())

    }

    /*
    *
    * Funcion que tiene como objetivo realizar un unit test
    * del endpoint findAll, para hacerlo se realizo un mock de una lista mutable
    * de Users y de la respuesta del repositorio de Users, se verifica el status
    * de la peticion y el contentType.
    * */
    @Test
    fun checkGetUsers() {
        val users : MutableIterable<User> = mutableListOf(
            User(
                firstName = "Simon",
                lastName = "Bolivar",
                birthDay = "30/01/2024",
                username = "sbolivar",
                emailAddress = "sbolivar@dominio.com",
            ),
            User(
                firstName = "Simon",
                lastName = "Rodriguez",
                birthDay = "30/01/2024",
                username = "srodriguez",
                emailAddress = "srodriguez@dominio.com",
            ),
        )

        every { repository.findAll() } returns users

        mockMvc.perform(MockMvcRequestBuilders.get("/webTest/findall"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))

    }
}