package app.controllers;


import app.security.JwtUtil;
import app.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        if (usuario != null && !usuario.getUsuario().isEmpty() && !usuario.getClave().isEmpty()) {
            String token = jwtUtil.generateToken(usuario.getUsuario());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Los atributos 'usuario' y 'clave' son obligatorios");
        }
    }
}
