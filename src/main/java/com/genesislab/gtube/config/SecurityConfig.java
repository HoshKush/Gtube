package com.genesislab.gtube.config;

import com.genesislab.gtube.entity.Role;
import com.genesislab.gtube.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

//    private final UserService userService;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/profile").permitAll()
//                .antMatchers("/api/**").hasRole(Role.USER.name())
//                .anyRequest().authenticated().and()
//                .logout().logoutSuccessUrl("/").and()
//                .oauth2Login()
//                .userInfoEndpoint()
//                .userService(userService);
//    }
}
