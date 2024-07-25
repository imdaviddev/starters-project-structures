package com.backend.app.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securtyFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                // Cuando solo nos loguamos con usuario y contraseña (sin token)
                .httpBasic(Customizer.withDefaults())
                // Para aplicaciones web lo ideal es trabajar sin estado (sin guardar el estado, ya que es un objeto bastante pesado)
                // En su lugar se le da un token que tiene un tiempo determinado de uso o cantidad limita de usos
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    // Configurar los endpoints publicos
                    http.requestMatchers(HttpMethod.GET, "/api/users").permitAll();

                    // Configuarar los endpoints privados
                    http.requestMatchers(HttpMethod.POST, "/api/users").hasAuthority("READ");

                    // Configurar el resto de endpoints - NO ESPECIFICADOS
                    http.anyRequest().denyAll(); 
                    //http.anyRequest().authenticated(); --> Si no especifico pero si tengo los permisos me permite acceder
                })
                .build();
    }

    @Bean 
    public AuthenticationManager authentcationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        List<UserDetails> userDetailsList = new ArrayList<>();
        
        userDetailsList.add(User.withUsername("davi")
            .password("1234")
            .roles("ADMIN")
            .authorities("READ", "CREATE")
            .build());

        userDetailsList.add(User.withUsername("user")
            .password("1234")
            .roles("USER")
            .authorities("READ")
            .build());

        return new InMemoryUserDetailsManager(userDetailsList);
    }

    @Bean // Esto es solo a modo de prueba no se debe usar en producccion
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
