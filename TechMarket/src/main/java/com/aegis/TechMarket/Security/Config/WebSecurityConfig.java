package com.aegis.TechMarket.Security.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .anonymous().disable()
                .authorizeHttpRequests()
                .requestMatchers("/registration")
                .permitAll()
                .requestMatchers("/")
                .permitAll()
                .requestMatchers("/ad/details/**")
                .permitAll()
                .requestMatchers("/css/**")
                .permitAll()
                .requestMatchers("/images/{filename}")
                .permitAll()
                .requestMatchers("/search/**")
                .permitAll()
                .requestMatchers("/category/**")
                .permitAll()
                .requestMatchers("/asc")
                .permitAll()
                .requestMatchers("/desc")
                .permitAll()
                .requestMatchers("/login")
                .permitAll()
//                .requestMatchers("/error")
//                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin(form -> form.loginPage("/login"))
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
        return http.build();
    }
}
