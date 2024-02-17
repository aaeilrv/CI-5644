package com.example.demo.security

import com.nimbusds.jose.jwk.source.ImmutableSecret
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import javax.crypto.spec.SecretKeySpec

@Configuration
open class JwtEncodingConfig(
    @Value("MIIDHTCCAgWgAwIBAgIJTlutxzb25FNjMA0GCSqGSIb3DQEBCwUAMCwxKjAoBgNV\n" +
            "BAMTIWRldi1jcnhiMXVla3F5Y2V6MmI1LnVzLmF1dGgwLmNvbTAeFw0yNDAyMTQy\n" +
            "MjU4MTlaFw0zNzEwMjMyMjU4MTlaMCwxKjAoBgNVBAMTIWRldi1jcnhiMXVla3F5\n" +
            "Y2V6MmI1LnVzLmF1dGgwLmNvbTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoC\n" +
            "ggEBALK+2R4bjoIlv1EXfMzeweoXj8uwwaAkfYp3a3tMSSTok0LtlMphrzDUgirc\n" +
            "aRPQ2eTArkuE+44tA2SftYtDQrJh+2600RH4MNF9+6nlugXvfIrJstIiqhUF1EnF\n" +
            "+5G9gFwchu14NtPSTYb2LIC6H3ixmiPqEmOP4WzWV6DlPWxIUQwwGGYzz9qx/iPm\n" +
            "8Jq7ZzhPEsaCMBH4sZOebnjwPBQcJ1H5XM23Z8sn6QnTTJZ3LPS4ivxVshP3RHcv\n" +
            "A8vXP8V09VNc5i2hqFUUOTSzb+YTyxjjPtBGx1z5apSnu6L5kjD41uBt0I0gkJAZ\n" +
            "80S4vjCB3JjF+bisPuef/D4vg9MCAwEAAaNCMEAwDwYDVR0TAQH/BAUwAwEB/zAd\n" +
            "BgNVHQ4EFgQUEwss1uSS9uuCeAf17MXgtySyH00wDgYDVR0PAQH/BAQDAgKEMA0G\n" +
            "CSqGSIb3DQEBCwUAA4IBAQBmtsbAknhQTZgQFVB4gYjv+Nsn7nrIS12LYPeAqTOi\n" +
            "2FRLXQgEDIXqvO4Xs6psfTwz3WU7X+yloYwtVji6d577AQ9UaUkwVZOBhzWiOIXI\n" +
            "HQkn+m2wMcjQKAROKI2NCI2CvZIz/mp/hKlNxbyYst/+bZpmpX1LPIZV6+4+Dt0G\n" +
            "l81cRoXG/7OkMoUP3BHzjZaiAzWo3S8gzL/EyTogD7x6W0pOzIdcwnj3keT386Wl\n" +
            "qJYvOfqdFJkzRWt8QVKvf4L+vX9d0tNDGCIq5Tn+E7CzE6yJChJn8hgK1qK4oVPr\n" +
            "k4pNe0un0zEovhm7QipB5p4MqgUQPRXJdXoMr1LC55wl")
    private val jwtKey: String,
) {
    private val secretKey = SecretKeySpec(jwtKey.toByteArray(), "RS256")

    @Bean
    open fun jwtDecoder(): JwtDecoder {
        println(secretKey.algorithm)
        return NimbusJwtDecoder.withSecretKey(secretKey).build()
    }

    @Bean
    open fun jwtEncoder(): JwtEncoder {
        val secret = ImmutableSecret<SecurityContext>(secretKey)
        return NimbusJwtEncoder(secret)
    }
}