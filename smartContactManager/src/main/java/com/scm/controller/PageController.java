package com.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class PageController {

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


   // Services 
   @RequestMapping("/contact")   
   public String contact(){
      return "contact";
   }
}
