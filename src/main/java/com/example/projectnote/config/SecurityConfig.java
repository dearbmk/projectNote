package com.example.projectnote.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    LoginValidator loginValidator;

    protected DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/home").permitAll()
                .anyRequest().authenticated() //to access any URI Authentication is needed
                .and()
                .formLogin()//Login form will be used
                .loginPage("/login")
                .loginProcessingUrl("/loginInfo")
                .defaultSuccessUrl("/userinfo", true)
                .permitAll()
                .and()
                .logout();

        return http.build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(loginValidator)
            .passwordEncoder(new BCryptPasswordEncoder());
    }

}
