package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.demo.security.ApplicationUserRole.ADMIN;
import static com.example.demo.security.ApplicationUserRole.STUDENT;

@Configuration
@EnableWebSecurity
public class AplicationSecurityConfig  extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder PasswordEncoder;//lier a passw de passwconf

    @Autowired
    public AplicationSecurityConfig(org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
        PasswordEncoder = passwordEncoder;
    }

    // configuration de chemin (qui S'authentifie etc)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()//for next explication
                    .authorizeHttpRequests()
                    .antMatchers("/","index","/css/*","/js/*").permitAll()//avec ces 2.dernier fonction on permet un accés au file citée s
                    .antMatchers("/api/**").hasRole(STUDENT.name())//seul le user avec role student peut avoir acces a l'api
                    .anyRequest()
                    .authenticated()//specifie username and password
                    .and()
                    .httpBasic();//using
    }
    //Ajout utilisateur A acces
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails annaSmithUser =User.builder()
                .username("annasmith")
                .roles(STUDENT.name())//ROLE_STUDENT
                .password(PasswordEncoder.encode("password"))
                .build();
        UserDetails lindaUser =User.builder()
                .username("linda")
                .roles(ADMIN.name())//ROLE_ADMIN
                .password(PasswordEncoder.encode("password1234"))
                .build();
        UserDetails tomUser =User.builder()
                .username("tom")
                .roles(ADMIN.name())//ROLE_ADMINITARINEE
                .password(PasswordEncoder.encode("password1234"))
                .build();

        return  new InMemoryUserDetailsManager(
                annaSmithUser,
                 lindaUser,
                tomUser
        );
    }

}
