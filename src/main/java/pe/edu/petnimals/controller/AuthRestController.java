package pe.edu.petnimals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pe.edu.petnimals.util.JwtUtil; // Ajusta el paquete según dónde guardaste JwtUtil
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> loginParaPostman(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        // 1. Buscamos el usuario en tu Base de Datos
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // 2. Verificamos que la contraseña encriptada coincida
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            // 3. Si es correcto, ¡Generamos su Token JWT profesional!
            String token = jwtUtil.generarToken(userDetails.getUsername());
            
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("token", token);
            return ResponseEntity.ok(respuesta);
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }
}