package pe.edu.petnimals.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Generamos una clave secreta segura para firmar los tokens
    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    // El token va a durar 10 horas (en milisegundos)
    private final long EXPIRATION_TIME = 36000000; 

    // 1. Método para generar el Token cuando el usuario se loguee con éxito
    public String generarToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    // 2. Extraer el nombre de usuario (username) de un token que nos mande Postman
    public String extraerUsername(String token) {
        return obtenerTodasLasClaims(token).getSubject();
    }

    // 3. Verificar si el token ya expiró
    public boolean esTokenExpirado(String token) {
        return obtenerTodasLasClaims(token).getExpiration().before(new Date());
    }

    // 4. Validar si el token es correcto y pertenece al usuario
    public boolean validarToken(String token, String username) {
        final String tokenUsername = extraerUsername(token);
        return (tokenUsername.equals(username) && !esTokenExpirado(token));
    }

    // Método auxiliar para leer el contenido interno del token
    private Claims obtenerTodasLasClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}