//package net.javaproject.registration_login.config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurity {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().authenticated() // All requests require authentication
//                )
//                .formLogin(form -> form
//                        .loginPage("/login") // Custom login page
//                        .loginProcessingUrl("/login") // URL to submit the login form
//                        .defaultSuccessUrl("/users", true) // Redirect to /users on successful login
//                        .permitAll() // Allow everyone to see the login page
//                );
//
//        return http.build();
//    }
//}

package net.javaproject.registration_login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register/**").permitAll()
                        .requestMatchers("/index").permitAll()
                        .requestMatchers("/users").hasRole("ADMIN")
                )
                .formLogin(form -> form
                        .loginPage("/login") // Custom login page
                        .loginProcessingUrl("/login") // URL to submit the login form
                        .defaultSuccessUrl("/users", true) // Redirect to /users on successful login
                        .permitAll() // Allow everyone to see the login page
                ).logout(
                        logout-> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );

        return http.build();
    }
}

