package com.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.serviceImpl.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {

   @Autowired
   private SecurityCustomUserDetailService userDetailService;
   //user create and login
   //using Authentication Provider
   @Bean
   public AuthenticationProvider authenticationProvider(){
      DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
      daoAuthenticationProvider.setUserDetailsService(userDetailService);
      daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

      return daoAuthenticationProvider;
   } 

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

      httpSecurity.authorizeHttpRequests(authorize->{
         // authorize.requestMatchers("/home","/register","/login","/about").permitAll();
         authorize.requestMatchers("/user/**").authenticated();
         authorize.anyRequest().permitAll();
      });


      return httpSecurity.build();

   }

   @Bean
   public PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
   }


}
