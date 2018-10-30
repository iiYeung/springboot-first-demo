package com.example.readinglist.configure;

import com.example.readinglist.reposity.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ReaderRepository repository;

    @Autowired
    public SecurityConfig(ReaderRepository repository) {
        this.repository = repository;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").access("hasRole('READER')")
            .antMatchers("/**").permitAll()
            .and()
            .formLogin()
            .loginPage("/login")
            .failureUrl("/login?error=true");
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(repository::findByUsername);
    }
}
