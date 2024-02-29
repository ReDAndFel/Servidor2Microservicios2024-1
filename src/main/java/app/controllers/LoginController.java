package app.controllers;


import app.model.User;
import app.security.JwtInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private JwtInterface jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {

        String username = user.getFirstName();
        String password = user.getPassword();

        if (user != null && !username.isEmpty() && !password.isEmpty()) {
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Los atributos 'usuario' y 'clave' son obligatorios");
        }
    }
}
