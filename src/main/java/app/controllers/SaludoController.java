package app.controllers;

import app.security.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludoController {

    @Value("${jwt.secret}")
    private String jwtSecret;
    private JwtUtil jwtUtil;

    @GetMapping("/saludo")
    public ResponseEntity<String> saludar(@RequestParam String nombre, HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            try {
               if (jwtUtil.isTokenValid(token,nombre)) {
                    return ResponseEntity.ok("Hola " + nombre);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El token no es válido para el nombre proporcionado");
                }
            } catch (ExpiredJwtException ex) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El token ha expirado");
            } catch (Exception ex) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El token no es válido");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Se requiere un token JWT en la cabecera Authorization");
        }
    }

    @GetMapping("/**")
    public ResponseEntity<String> handleNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso no encontrado");
    }
}
