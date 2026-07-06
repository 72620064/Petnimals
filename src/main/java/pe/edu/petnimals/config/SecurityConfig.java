package pe.edu.petnimals.config;

import pe.edu.petnimals.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired; // <--- AGREGA ESTE IMPORT
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy; // <--- AGREGA ESTE IMPORT
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pe.edu.petnimals.config.JwtAuthFilter; 

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter; 

    // 1. FILTRO EXCLUSIVO PARA LA API
    @Bean
    @Order(1) 
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/api/**") 
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll() // <--- Esto DEBE estar permitido
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); 

        return http.build();
    }

    // 2. FILTRO EXCLUSIVO PARA TU WEB
    @Bean
    @Order(2)
    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/**") 
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll() // <--- AGREGA ESTA LÍNEA AQUÍ TAMBIÉN por si acaso
                .requestMatchers("/css/**", "/CSS/**","/JS/**", "/js/**", "/images/**", "/webjars/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login") 
                .permitAll()
            );

        return http.build();
    }

    // 2. Codificador de contraseñas SHA-256
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return SecurityUtils.encriptarContrasenia(rawPassword.toString());
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return SecurityUtils.encriptarContrasenia(rawPassword.toString()).equals(encodedPassword);
            }
        };
    }
}