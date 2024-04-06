package com.example.demo.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator
import org.springframework.security.oauth2.core.OAuth2TokenValidator
import org.springframework.security.oauth2.jwt.*
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher


@EnableWebSecurity
@Profile(value = ["default"])
class SecurityConfig : WebSecurityConfiguration() {

    @Value("\${auth0.audience}")
    private val audience: String = String()

    @Value("\${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private val issuer: String = String()

    @Bean
    fun jwtDecoder(): JwtDecoder {
        val jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuer) as NimbusJwtDecoder
        val audienceValidator: OAuth2TokenValidator<Jwt> = AudienceValidator(audience)
        val withIssuer: OAuth2TokenValidator<Jwt> = JwtValidators.createDefaultWithIssuer(issuer)
        val withAudience: OAuth2TokenValidator<Jwt> = DelegatingOAuth2TokenValidator(withIssuer, audienceValidator)
        jwtDecoder.setJwtValidator(withAudience)
        println(jwtDecoder)
        return jwtDecoder
    }

    @Throws(Exception::class)
    fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .requestMatchers("v1/user/leaders/").permitAll()
            .requestMatchers("v1/user/hello-oauth").authenticated()
            .and()
            .oauth2ResourceServer().jwt()
    }

}

//@Configuration
//@EnableWebSecurity
//@Profile("test")
//open class ApplicationNoSecurity {
//
//    @Value("\${auth0.audience}")
//    private lateinit var audience: String
//
//    @Value("\${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
//    private lateinit var issuer: String
//
//    @Bean
//    @Throws(Exception::class)
//    open fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
//        http.authorizeHttpRequests {
//            it.requestMatchers("/**").permitAll()
//        }
//        return http.build()
//    }
//}