package pe.edu.petnimals.config;

import pe.edu.petnimals.util.SecurityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. Definimos las reglas de accesos y formularios
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Permitimos que cualquiera entre al Inicio, Registro, Login y recursos estáticos (CSS/JS)
                .requestMatchers("/", "/index", "/registro", "/login", "/css/**", "/js/**", "/img/**").permitAll()
                // Cualquier otra pantalla requerirá estar logueado obligatoriamente
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login") // Nuestra url personalizada de login
                .loginProcessingUrl("/login") // La url interna donde Spring Security procesará el POST
                .usernameParameter("username") // El name="" del input correo en el HTML
                .passwordParameter("password") // El name="" del input contraseña en el HTML
                .defaultSuccessUrl("/index", true) // ¡CUMPLIDO!: Si el login es correcto, regresa al inicio ("/")
                .failureUrl("/login?error") // Si falla, regresa al login con un parámetro de error
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }

    // 2. Le enseñamos a Spring Security a procesar las contraseñas con nuestro algoritmo SHA-256
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return SecurityUtils.encriptarContrasenia(rawPassword.toString());
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                // Compara la contraseña que el usuario escribe en el login con el hash guardado en MySQL
                return SecurityUtils.encriptarContrasenia(rawPassword.toString()).equals(encodedPassword);
            }
        };
    }
}