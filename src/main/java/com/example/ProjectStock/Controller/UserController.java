package com.example.ProjectStock.Controller;
import com.example.ProjectStock.Service.StockSearchHistoryService;
import com.example.ProjectStock.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.ProjectStock.Modele.StockSearchHistory;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String handleRegistrationForm(@RequestParam String username, @RequestParam String password) {
        userService.registerUser(username, password);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String handleLoginForm(@RequestParam String username, @RequestParam String password, HttpSession session) {
        if (userService.login(username, password)) {
            session.setAttribute("username", username);
            return "redirect:/dashboard";
        } else {
            return "redirect:/login";
        }
    }
}