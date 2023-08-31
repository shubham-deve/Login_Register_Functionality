package com.Assignment.Controller;

import com.Assignment.Service.UserService;
import com.Assignment.modal.User;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class HomeController {


    @Autowired
    UserService userService;

    User user=null;

@GetMapping("/")
public  String home(Model model){
    user=null;
    model.addAttribute("user", new User());
    return "Register";
}

@PostMapping("/register-user")
public String registerUser(@Valid  User user, Model m, HttpSession session,BindingResult result) throws  InterruptedException{
    String msg="Invalid Details";
    boolean userIsPresent= userService.findByUseremail(user.getEmail());
    if(userIsPresent){
        m.addAttribute("msg",msg);
        return "redirect:/";
    }
        if(result.hasErrors()){
            return "redirect:/";
        }
        else{
            userService.insertUser(user);
            return "/secure";
        }
}

@GetMapping("/login")
public String Login(){
    user=null;
return "login";
}


@GetMapping("/secure")
public  String securePage(Model model){
    if(user==null){
        return "redirect:/login";
    }
    model.addAttribute("user",user);
    return "secure";
}

@PostMapping("/logging")
public String loggingPage(@RequestParam("email") String username,@RequestParam("password") String password,Model model,HttpSession session) throws InterruptedException{
    user = userService.findByEmail(username);
   if(user!=null && user.getPassword().equals(password)){
       return "redirect:/secure";
   }else{
       model.addAttribute("Msg","Invalid Details");
       return "redirect:/login";
   }
}

@GetMapping("/logout")
    public String logout(Model model,HttpSession session){
    user=null;
    session.setAttribute("logoutMsg","logoutSuccessfully");
    return "redirect:/login";
}

@ExceptionHandler(MethodArgumentNotValidException.class)
    public String Exception(BindingResult result,Model model){
    model.addAttribute("errorMsg",result.getAllErrors());
    return "redirect:/";
}
}
