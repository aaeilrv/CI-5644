package com.example.demo.integration.Purchase

import com.example.demo.controller.dto.CreatePurchaseDTO
import com.example.demo.controller.dto.PurchaseDTO
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.Sql.ExecutionPhase
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import java.sql.Timestamp
import com.example.demo.controller.dto.CardOwnedByUserDTO
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JsonNode
import org.junit.jupiter.api.*
import org.mockito.Mockito
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = ["test"])
@AutoConfigureMockMvc( addFilters = false )
class PurchaseIntegrationTests {

    companion object {
        const val AFTER_MIGRATE = "classpath:db/migration/delete_tables.sql"
        
        //Mock auth parameter to bypass Spring Security authentication
        val mockPrincipalValid: JwtAuthenticationToken = Mockito.mock(JwtAuthenticationToken::class.java)

        //Create schema before running tests
        @JvmStatic
        @BeforeAll
        @Sql("classpath:db/migration/create_schemas.sql")
        fun buildSchema() {
            Mockito.`when`(mockPrincipalValid.tokenAttributes).thenReturn(mapOf("sub" to "ejemploejemploso"))
            return
        }
    }

    @Autowired
    lateinit var mockMvc: MockMvc
    @Autowired
    lateinit var objectMapper: ObjectMapper

    @BeforeEach
    @Sql(AFTER_MIGRATE)
    fun emptyTablesExceptCards() {
        return
    }

    @Test
    @Sql("classpath:db/migration/test_1_context.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(AFTER_MIGRATE, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    fun `Test Purchase - User with no cards`() {

        val purchaseRequest = CreatePurchaseDTO(
            5,
            1,
            Timestamp(0)
        )

        //Make the purchase request
        val purchaseResult = mockMvc.post("/v1/purchase") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(purchaseRequest)
            accept = MediaType.APPLICATION_JSON
            principal = mockPrincipalValid

        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
        }.andReturn().response.contentAsString.let {
            objectMapper.readValue(it, PurchaseDTO::class.java)
        }

        //Get cards owned by user to count them
        val userCardsResponse: String = mockMvc.get("/v1/user/cardsOwned?page=0&size=36") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            principal = mockPrincipalValid
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
        }.andReturn().response.contentAsString

        //Needed as Page doesn't get along well with objectMapper
        val jsonContentNode: JsonNode = objectMapper.readTree(userCardsResponse).get("content")
        println(jsonContentNode.toString())
        val userCards = objectMapper.convertValue(jsonContentNode, object : TypeReference<List<CardOwnedByUserDTO?>>() {})

        val cardList = userCards.filterNotNull()
        val cardCount = cardList.fold(0) {acc, elem -> acc + elem.numberOwned}
        Assertions.assertEquals(25, cardCount)

    }

    @Test
    @Sql("classpath:db/migration/test_2_context.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(AFTER_MIGRATE, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    fun `Should fail - Try to buy with non-existent credit card`() {
        val purchaseRequest = CreatePurchaseDTO(
            5,
            5,
            Timestamp(0)
        )

        //Make the purchase request
        mockMvc.post("/v1/purchase") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(purchaseRequest)
            content = objectMapper.writeValueAsString(purchaseRequest)
            principal = mockPrincipalValid

        }.andExpect {
            status { isNotFound() }
            content { contentType(MediaType.APPLICATION_JSON) }
        }
    }

    @Test
    @Sql("classpath:db/migration/test_3_context.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(AFTER_MIGRATE, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    fun `Test purchase - User with one of each card`() {
        val purchaseRequest = CreatePurchaseDTO(
            5,
            1,
            Timestamp(0)
        )

        //Make the purchase request
        val purchaseResult = mockMvc.post("/v1/purchase") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(purchaseRequest)
            accept = MediaType.APPLICATION_JSON
            principal = mockPrincipalValid

        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
        }.andReturn().response.contentAsString.let {
            objectMapper.readValue(it, PurchaseDTO::class.java)
        }

        //Get cards owned by user to count them
        val userCardsResponse: String = mockMvc.get("/v1/user/cardsOwned?page=0&size=36") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            principal = mockPrincipalValid
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
        }.andReturn().response.contentAsString

        //Needed as Page doesn't get along well with objectMapper
        val jsonContentNode: JsonNode = objectMapper.readTree(userCardsResponse).get("content")
        println(jsonContentNode.toString())
        val userCards = objectMapper.convertValue(jsonContentNode, object : TypeReference<List<CardOwnedByUserDTO?>>() {})

        val cardList = userCards.filterNotNull()
        val cardCount = cardList.fold(0) {acc, elem -> acc + elem.numberOwned}
        //User has one of each card at the beginning, so he should now have 61
        Assertions.assertEquals(61, cardCount)
    }

    @Test
    @Sql("classpath:db/migration/test_4_context.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(AFTER_MIGRATE, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    fun `Create many purchases and get all purchases from user`() {
        //Body of the purchase request to be made
        val purchaseRequest = CreatePurchaseDTO(
            2,
            1,
            Timestamp(0)
        )

        //Create 10 purchases
        for(i in 0 until 10) {
            mockMvc.post("/v1/purchase") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(purchaseRequest)
                accept = MediaType.APPLICATION_JSON
                principal = mockPrincipalValid
            }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
        }

        //Get all purchases from user and count
        val userPurchaseList: List<PurchaseDTO> = mockMvc.get("/v1/purchase") {
            accept = MediaType.APPLICATION_JSON
            principal = mockPrincipalValid
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
        }.andReturn().response.contentAsString.let {
            objectMapper.readValue(it, List::class.java)
        } as List<PurchaseDTO>

        Assertions.assertEquals(10, userPurchaseList.size)

        //Getting all purchases should give the same 10
        val globalPurchaseList: List<PurchaseDTO> = mockMvc.get("/v1/purchase/all") {
            accept = MediaType.APPLICATION_JSON
            principal = mockPrincipalValid
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
        }.andReturn().response.contentAsString.let {
            objectMapper.readValue(it, List::class.java)
        } as List<PurchaseDTO>

        Assertions.assertEquals(10, globalPurchaseList.size)
    }

    //Fata uno, podria ser crear tarjeta de credito
}