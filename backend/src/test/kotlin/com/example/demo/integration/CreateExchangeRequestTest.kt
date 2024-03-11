package com.example.demo.integration

import com.example.demo.DemoApplication
import com.example.demo.controller.dto.CreateExchangeRequestDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(classes = [DemoApplication::class])
@AutoConfigureMockMvc
@ExtendWith(SpringExtension::class)
class CreateExchangeRequestIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    private val token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImZUUmk2dExkY295anJFdC1mU0FlMCJ9.eyJpc3MiOiJodHRwczovL2Rldi1jcnhiMXVla3F5Y2V6MmI1LnVzLmF1dGgwLmNvbS8iLCJzdWIiOiJGT1lvelUwZERPS0dJcUxudGtONjFXOUt6RmludndITEBjbGllbnRzIiwiYXVkIjoiaHR0cHM6Ly9iYXJhaml0YXMuY29tIiwiaWF0IjoxNzEwMTU3MTg1LCJleHAiOjE3MTAyNDM1ODUsImF6cCI6IkZPWW96VTBkRE9LR0lxTG50a042MVc5S3pGaW52d0hMIiwiZ3R5IjoiY2xpZW50LWNyZWRlbnRpYWxzIn0.WomRGAvYEEKdWZ-ik0BxKbPm-P_Ag70AtNAiu7m5IngvC1d6T-x51JAC4bqLNZNa2DdQrdsFxG0no0vbrKARNiFqCOVMjJ9OqspDJ2IfEWO7uwW_GlYgszmJkjPGX55xxPUFSHS0dpxGp8kfUIFeIeSIhhpY70HUhr7p0BRX2ni4D294rkgfXmemZXFh6wUj-bbthLEgNDLoz4OyCQV-DDU_GilCzIV20Jr3xyyqWl2drTebcIph5w5YK0KZ9-vLF3kTIbMyjG-o_KQIXXw5MzE9xgoHfX_jlfXOEyak1xLnNNGtobRi76twfSjhjYp7uw6gXWW30lGp6SKVa2S24A"

    @Test
    fun `test create exchange request`() {
        val createExchangeRequestDto = CreateExchangeRequestDto(
            userId = 1,
            requestedCardId = 3,
            status = "PENDING"
        )

        mockMvc.perform(post("/v1/user")
                .header("Authorization", "Bearer $token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createExchangeRequestDto)))
            .andExpect(status().isOk)
    }
}