package app.controllers;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.security.JwtInterface;

@RestController
public class SaludoController {
    @Autowired
    private JwtInterface jwtUtil;

    @GetMapping("/saludo")
    public ResponseEntity<String> saludar(@RequestParam String nombre, HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String tokenHeader = authorizationHeader.substring(7);
            try {
               if (jwtUtil.isTokenValid(tokenHeader,nombre)) {
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
