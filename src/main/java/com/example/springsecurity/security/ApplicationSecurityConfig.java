package com.example.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.springsecurity.security.ApplicationUserPermission.COURSE_WRITE;
import static com.example.springsecurity.security.ApplicationUserPermission.STUDENT_READ;
import static com.example.springsecurity.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder ;
   @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .antMatchers(HttpMethod.DELETE,"mangment/api/**").hasAuthority(COURSE_WRITE.name())
                .antMatchers(HttpMethod.POST,"mangment/api/**").hasAuthority(COURSE_WRITE.name())
                .antMatchers(HttpMethod.PUT,"mangment/api/**").hasAuthority(COURSE_WRITE.name())
                .antMatchers(HttpMethod.GET,"mangment/api/**").hasAnyRole(ADMIN.name(),ADMINTRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic() ;


    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails omar = User.builder()
                .username("omar")
                .password(passwordEncoder.encode("omar1234"))
                .authorities(STUDENT.getGrantedAuthorities())
             //   .roles(STUDENT.name()) // ROLE_STUDENT
                .build() ;
        UserDetails lindaUser = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("linda1234"))
                .authorities(ADMIN.getGrantedAuthorities())
           //     .roles(ADMIN.name())
                .build();
        UserDetails oussemaUser = User.builder()
                .username("oussema")
                .password(passwordEncoder.encode("oussema1234"))
                .authorities(ADMINTRAINEE.getGrantedAuthorities())
           //     .roles(ADMINTRAINEE.name())
                .build();
        return new InMemoryUserDetailsManager(omar,lindaUser,oussemaUser);
        }
    }

