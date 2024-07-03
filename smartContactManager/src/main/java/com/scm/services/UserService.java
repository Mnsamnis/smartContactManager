package com.scm.services;

import com.scm.entities.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
  User saveUser(User user);
  Optional<User> getUserById(String userId);
  Optional<User> updateUser(User user);
  void deleteUser(String userId);
  boolean isUserExist(String userId);
  boolean isEserExistsByEmail(String email);
  List<User> getAllUsers();  


  //add more methods for User serice();

  
}
