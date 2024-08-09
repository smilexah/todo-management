package sdu.edu.kz.todo_management.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import sdu.edu.kz.todo_management.security.JwtAuthenticationEntryPoint;
import sdu.edu.kz.todo_management.security.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAuthenticationFilter authenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        httpRequest -> {
//                            httpRequest.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
//                            httpRequest.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
//                            httpRequest.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
//                            httpRequest.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER"); // or permitAll()
//                            httpRequest.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN", "USER"); // or permitAll()
                            httpRequest.requestMatchers("/api/auth/**").permitAll();
                            httpRequest
                                    .anyRequest().authenticated();
                        }
                )
                .httpBasic(Customizer.withDefaults());

        http.exceptionHandling(exception ->
                exception.authenticationEntryPoint(authenticationEntryPoint));

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    CorsConfiguration corsConfiguration() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("*");
        corsConfig.addAllowedHeader("*");
        corsConfig.addAllowedMethod("*");
        return corsConfig;
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails userDetails = User
//                .builder()
//                .username("meiirzhan")
//                .password(passwordEncoder().encode("user1234"))
//                .roles("USER")
//                .build();
//
//        UserDetails adminDetails = User
//                .builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin1234"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(userDetails, adminDetails);
//    }
}
