package com.example.demoq.controller;

import com.example.demoq.model.User;
import com.example.demoq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Отображение страницы регистрации
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User()); // Новый объект User для формы
        return "register"; // Возвращает шаблон register.html
    }

    // Обработка регистрации
    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        try {
            userService.registerUser(user); // Регистрация пользователя
            return "redirect:/login"; // После успешной регистрации перенаправить на страницу входа
        } catch (Exception e) {
            model.addAttribute("error", "Username already exists."); // Если пользователь существует, показать ошибку
            return "register"; // Возвращает шаблон register.html
        }
    }

    // Отображение страницы входа
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Возвращает шаблон login.html
    }

}
