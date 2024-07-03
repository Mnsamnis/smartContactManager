package com.scm.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.entities.User;
import com.scm.helper.AppConstants;
import com.scm.helper.ResourceNotFoundException;
import com.scm.repo.UserRepo;
import com.scm.services.UserService;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

   @Autowired
   private UserRepo userRepo;

   private Logger logger=LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRoleList(List.of(AppConstants.ROLE_USER));

        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String userId) {
        return userRepo.findById(userId);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User updatedUser=userRepo.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
        updatedUser.setName(user.getName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setAbout(user.getAbout());
        updatedUser.setPhoneNumber(user.getPhoneNumber());
        updatedUser.setEnabled(user.isEnabled());
        updatedUser.setProfilePic(user.getProfilePic());
        updatedUser.setEmailVerified(user.isEmailVerified());
        updatedUser.setPhoneVerified(user.isPhoneVerified());
        updatedUser.setProvider(user.getProvider());
        updatedUser.setProviderId(user.getProviderId());

        User save = userRepo.save(updatedUser);


        return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(String userId) {
     User deletedUser=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user Not Found"));

     userRepo.delete(deletedUser);

    }

    @Override
    public boolean isUserExist(String userId) {
        User user=userRepo.findById(userId).orElse(null);

        return user != null ? true : false;
    }

    @Override
    public boolean isEserExistsByEmail(String email) {
      User user=userRepo.findByEmail(email).orElse(null);
      return user != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
   

}
