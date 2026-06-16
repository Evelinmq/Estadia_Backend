package mx.edu.utez.JuventudxTemixco.Security;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
/**
 * Emisión y validación de JWT (HS256) usando propiedades jwt.secret y
 jwt.expiration-ms.
 */
@Service
public class JwtService {
    private static final String ROLES_CLAIM = "roles";
    private final String secret;
    private final long expirationMs;
    private volatile SecretKey signingKey;

    public JwtService(

            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration-ms}") long expirationMs) {
        this.secret = secret;
        this.expirationMs = expirationMs;
    }
    private SecretKey signingKey() {
        if (signingKey == null) {
            synchronized (this) {
                if (signingKey == null) {
                    byte[] bytes =
                            secret.getBytes(StandardCharsets.UTF_8);

                    if (bytes.length < 32) {
                        throw new IllegalStateException(
                                "jwt.secret debe medir al menos 32 bytes en UTF-8 para HS256 (JJWT)");

                    }
                    signingKey = Keys.hmacShaKeyFor(bytes);
                }
            }
        }
        return signingKey;
    }
    /**
     * @param correo sujeto del token (típicamente el nombre de usuario
    en login)
     * @param roleAuthorities nombres de rol para Spring, p. ej. "ROLE_USER",
    "ROLE_ADMIN"
     */
    public String generateAccessToken(String correo, Collection<String>
            roleAuthorities) {

        Instant now = Instant.now();
        Instant exp = now.plusMillis(expirationMs);
        return Jwts.builder()

                .subject(correo)
                .claim(ROLES_CLAIM, List.copyOf(roleAuthorities))
                .issuedAt(Date.from(now))
                .expiration(Date.from(exp))
                .signWith(signingKey(), Jwts.SIG.HS256)
                .compact();

    }
    public Claims parseClaims(String token) {
        return Jwts.parser()

                .verifyWith(signingKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }
    public String extractUsername(String token) {
        return parseClaims(token).getSubject();
    }
    /**
     * Lista de strings del claim "roles" (vacía si falta o es inválido el

     formato).
     */
    public List<String> extractRoleAuthorities(String token) {
        List<?> raw = parseClaims(token).get(ROLES_CLAIM, List.class);
        if (raw == null) {
            return List.of();
        }
        return

                raw.stream().map(Object::toString).collect(Collectors.toUnmodifiableList());
    }
    public boolean isTokenValid(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("=== ERROR VALIDANDO JWT EN BACKEND: " + e.getMessage());
            return false;
        }
    }
    public boolean isTokenValidForUser(String token, String expectedUsername) {
        if (!isTokenValid(token)) {
            return false;
        }
        String subject = extractUsername(token);
        return subject != null && subject.equals(expectedUsername);
    }
}