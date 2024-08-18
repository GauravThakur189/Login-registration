package net.javaproject.registration_login.controller;

import jakarta.validation.Valid;
import net.javaproject.registration_login.dto.UserDto;
import net.javaproject.registration_login.entity.User;
import net.javaproject.registration_login.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
    public String register(@Valid @ModelAttribute("user") UserDto userDto,
                           BindingResult result,
                           Model model){
        User existingUser = userService.findByEmail(userDto.getEmail());
        if(existingUser != null){
            result.rejectValue("email",null,"Email already exist");
        }
        if(result.hasErrors()){
            model.addAttribute("user",userDto);

            return "/register";
        }
         userService.saveUser(userDto);
         return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String getUsers(Model model){
        List<UserDto> userDto = userService.getUsers();
        model.addAttribute("users",userDto);
        return "/users";
    }
}
