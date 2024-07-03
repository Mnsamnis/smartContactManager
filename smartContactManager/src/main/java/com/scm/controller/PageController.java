package com.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.User;
import com.scm.forms.RegisterForm;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
public class PageController {

   @Autowired
   private UserService userService;

   @RequestMapping("/home")  
   public String home(Model m){
      m.addAttribute("name","Manish");
      m.addAttribute("department","Elements");
      return  "home";
   }
// About 
   @RequestMapping("/about")   
   public String about(){
      return "about";
   }


   // Login Controller

   @RequestMapping("/login")   
   public String login(){
      return "login";
   }

   // Registration Controller
   @RequestMapping("/register")   
   public String register(Model model){

      RegisterForm registerForm=new RegisterForm();
      // registerForm.setName("Manish");
      // registerForm.setAbout("ABout US");
      model.addAttribute("registerForm",registerForm);

      return "register";
   }

   @PostMapping("/do-register")
   public String processRegister(@Valid @ModelAttribute RegisterForm registerForm, BindingResult rBindingResult, HttpSession session){

  
      if(rBindingResult.hasErrors()){


         return "register";
      }

      User user = new User();
      user.setName(registerForm.getName());
      user.setEmail(registerForm.getEmail());
      user.setPassword(registerForm.getPassword());
      user.setAbout(registerForm.getAbout());
      user.setPhoneNumber(registerForm.getPhoneNumber());
      


      User savedUser=userService.saveUser(user);

      System.out.println("user Saved");
      Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();

      session.setAttribute("message", message);

      return "redirect:/register";
   }
}
