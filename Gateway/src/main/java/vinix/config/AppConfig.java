package vinix.config;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${security.jwt.secret}")
    private String secret;

    @Bean
    SecretKey jwtSecretKey() {

        //Decodifica Base64
        byte[] keyBytes = Base64.getDecoder().decode(secret);

        //Algoritmo compatível com HS256
        return new SecretKeySpec(keyBytes, "HmacSHA256");
    }
}