package com.scm.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scm.repo.UserRepo;

@Service
public class SecurityCustomUserDetailService implements UserDetailsService {

   @Autowired
   private UserRepo userRepo;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     // We have to load the user

     return userRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found with this email id: " + username));

   }

}
