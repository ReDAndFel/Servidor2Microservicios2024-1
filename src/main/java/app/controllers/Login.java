package app.controllers;


import app.JwtUtil;
import app.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Login {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody(required = false) Usuario usuario) {
        if (usuario != null && usuario.getUsuario() != null && usuario.getClave() != null) {
            String token = jwtUtil.generateToken(usuario.getUsuario());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Los atributos 'usuario' y 'clave' son obligatorios");
        }
    }
}
