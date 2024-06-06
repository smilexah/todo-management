package sdu.edu.kz.todo_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder () {
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
                            httpRequest
                                    .anyRequest().authenticated();
                        }
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User
                .builder()
                .username("meiirzhan")
                .password(passwordEncoder().encode("user1234"))
                .roles("USER")
                .build();

        UserDetails adminDetails = User
                .builder()
                .username("admin")
                .password(passwordEncoder().encode("admin1234"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(userDetails, adminDetails);
    }
}
