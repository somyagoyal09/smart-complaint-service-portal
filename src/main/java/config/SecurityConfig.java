package com.miet.complaintportal.config;

import com.miet.complaintportal.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Frontend pages + register/login khule rahenge (bina login)
                        .requestMatchers("/", "/*.html", "/*.css", "/*.js", "/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/api/users/login").permitAll()
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/users").permitAll()

                        // Sirf ADMIN: user delete karna, sab users ki list dekhna
                        .requestMatchers(org.springframework.http.HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/users").hasRole("ADMIN")

                        // Agent management: sirf ADMIN add/delete kar sake
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/agents").hasRole("ADMIN")
                        .requestMatchers(org.springframework.http.HttpMethod.DELETE, "/api/agents/**").hasRole("ADMIN")

                        // Admin dashboard, user management, complaint assignment — sirf ADMIN
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")

                        // Baaki sab kuch (complaints waghera) — sirf login hona chahiye, koi bhi role chalega
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}