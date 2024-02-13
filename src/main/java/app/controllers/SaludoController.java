package app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludoController {

    @GetMapping("/saludo")
    public ResponseEntity<String> saludar(@RequestParam(required = false) String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            return ResponseEntity.ok("Hola " + nombre);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Solicitud no válida: El nombre es obligatorio");
        }
    }

    @GetMapping("/**")
    public ResponseEntity<String> handleNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso no encontrado");
    }

}
