package vinix.config;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.jsonwebtoken.security.Keys;

@Configuration
public class AppConfig {

    @Value("${security.jwt.secret}")
    private String secret;
    // injeta a chave secreta do application.yml

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        // cria o bean de criptografia de senha
    }

    @Bean
   SecretKey jwtSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
        // cria e retorna a chave criptográfica como bean do Spring
        // NÃO precisa de Base64 → Spring já resolve corretamente
    }
}