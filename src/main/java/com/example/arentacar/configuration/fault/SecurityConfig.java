package com.example.arentacar.configuration.fault;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    //HANGİ AUTHENTİCATİON İLE YAPACAĞIMIZI BELİRTİYORUZ
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
    //Bu kod parçası Spring Security ile bir güvenlik filtresi zinciri (security filter chain) oluşturmayı sağlar.

    // Hangi endpointe kim ulaşabilecek bunu belirttik.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/models/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/api/models/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/models/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/models/modelId").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/cars/**").hasRole("ADMIN") //** altında ne varsa demektir
                .antMatchers(HttpMethod.DELETE, "/api/cars/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/cars/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/api/brand/**").authenticated() // kullanıcı password ve şifre girsin rolü önemli değildi
                // .antMatchers("/login").permitAll()
                // login olmasına kullanıcı adı şifresine gerek yok --->>.anyMatchers("/asassasa").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable().formLogin().disable()
                .httpBasic();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}
