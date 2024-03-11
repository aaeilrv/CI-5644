package com.example.demo.integration

import com.example.demo.DemoApplication
import com.example.demo.controller.dto.CreateCardRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import com.example.demo.controller.dto.AddCardToOwnerRequest


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension::class)
class AddCardToOwnerIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    private val token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImZUUmk2dExkY295anJFdC1mU0FlMCJ9.eyJpc3MiOiJodHRwczovL2Rldi1jcnhiMXVla3F5Y2V6MmI1LnVzLmF1dGgwLmNvbS8iLCJzdWIiOiJGT1lvelUwZERPS0dJcUxudGtONjFXOUt6RmludndITEBjbGllbnRzIiwiYXVkIjoiaHR0cHM6Ly9iYXJhaml0YXMuY29tIiwiaWF0IjoxNzEwMTYwMjMyLCJleHAiOjE3MTAyNDY2MzIsImF6cCI6IkZPWW96VTBkRE9LR0lxTG50a042MVc5S3pGaW52d0hMIiwiZ3R5IjoiY2xpZW50LWNyZWRlbnRpYWxzIn0.gAB7wLrWj6dwp0Q4Y5-bgXkI0joCdov8YJa2LCRckKvGqUNoBTq1ZUPj7_xqSdCD4H5KL4G7D9dDg8pZnDscqkVYPWFw9PUYGLKwvRYxDG66dlQX_LcPBmZej08uEbBIhKeDQ3HUK3JjlgRqJssllGVYgtOK01QrUpSRXF_D74SB0NWVFStkwlXnQdFgnwdMm_8gvFrDN3nOQhZKLYdGbTv0bAQBca7NAh7TF68EXf8JnFW2xNHevC5ClTZS4LXsqazFLEdJwktbkayL2n69frk9BL6klB1t5ZkR1ACg-dvJSzvAWM2A5xTYl6B6QXlJEXJi59Rki4nyGgsoZQb41g"

    @Test
    fun `test add card to Owner`() {
        val addCardToOwnerRequest = AddCardToOwnerRequest(ownerSub = "juanperez1", cardId = 1)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/v1/card")
                .header("Authorization", "Bearer $token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addCardToOwnerRequest))
        )
        .andExpect(MockMvcResultMatchers.status().isOk)
    }
}