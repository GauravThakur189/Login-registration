package net.javaproject.registration_login.controller;

import net.javaproject.registration_login.dto.UserDto;
import net.javaproject.registration_login.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

 private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }
    // handler method to handle user registration
    @GetMapping("/register")
    public String registrationForm(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user",userDto);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@ModelAttribute("user") UserDto userDto){
         userService.saveUser(userDto);
         return "redirect:/register?success";
    }
}
