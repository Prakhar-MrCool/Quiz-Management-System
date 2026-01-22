package com.quiz.Quiz_Management_System.Config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final JwtAuthEntryPoint jwtAuthEntryPoint;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()).cors(cors -> {
		}).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthEntryPoint))
				.authorizeHttpRequests(auth -> auth.requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**")
						.permitAll().requestMatchers("/auth/**", "/public/**").permitAll().requestMatchers("/dev/**")
						.permitAll().requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated())
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();

	}

	/*
	 * @Bean public CorsConfigurationSource corsConfigurationSource() {
	 * CorsConfiguration config = new CorsConfiguration();
	 * config.setAllowedOrigins(List.of("http://localhost:5173"));
	 * config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	 * config.setAllowedHeaders(List.of("*")); config.setAllowCredentials(true);
	 * 
	 * UrlBasedCorsConfigurationSource source = new
	 * UrlBasedCorsConfigurationSource(); source.registerCorsConfiguration("/**",
	 * config); return source; }
	 */

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		System.out.println(new BCryptPasswordEncoder().matches("admin123",
				"$2a$10$7QkMZQ8ZQJ6bN6qX8V5J4uZr2o6t3vFjWm7qJZpE0E5W8kYx1U6Je"));

		return new BCryptPasswordEncoder();
	}

}
