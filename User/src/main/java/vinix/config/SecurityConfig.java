package vinix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    AuthenticationManager autenticador(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
        // autenticação do Spring Security usado pelo hr-oauth para autenticar usuário e senha
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())// desabilita proteção CSRF
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // STATELESS → cada requisição precisa do token JWT

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll()// permite o console do H2 sem autenticação
                .anyRequest().permitAll()
                // temporário → libera todos sem autenticação
                
                // quando JWT estiver configurado muda para 
                //.authenticated() //So´autoriza com token JWT
            )
            .headers(headers -> headers
                .frameOptions(frame -> frame.disable()));
                // necessário para o H2 Console abrir no iframe do navegador

        return http.build();
    }
}