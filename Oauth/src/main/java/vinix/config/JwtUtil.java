package vinix.config;

import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import vinix.dto.UserDTO;

@Component
public class JwtUtil {

    @Value("${security.jwt.expiration}")
    private Long expiration;
    // define quanto tempo o token é válido no yaml: security.jwt.expiration

    @Autowired
    private SecretKey secretKey;
    // injeta a chave criada no AppConfig (não recria)

    public String generateToken(UserDTO user) {
        return Jwts.builder() // inicia a construção do token
                .subject(user.getEmail())// quem está autenticado no token
                .claim("roles", user.getRoles()
                        .stream()
                        .map(r -> r.getRoleName())
                        .collect(Collectors.toList()))
                // adiciona as roles do usuário no payload do token

                .issuedAt(new Date())
                // registra quando o token foi gerado
                .expiration(new Date(System.currentTimeMillis() + expiration))
                // define quando o token expira
                .signWith(secretKey)
                // sela o token com a chave secreta e garante que não foi alterado
                .compact();
                // gera o token final no formato: xxxxx.yyyyy.zzzzz
    }
}