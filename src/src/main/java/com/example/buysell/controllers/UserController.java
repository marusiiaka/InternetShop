package com.example.buysell.controllers;

import com.example.buysell.models.User;
import com.example.buysell.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller //указывает, что класс является котроллером веб-приложения
@RequiredArgsConstructor //генерирует конструктор с аргументами для зависимостей класса
public class UserController {
    private final UserService userService; //объявление зависимости

    @GetMapping("/login")
    public String login(){

        return "login";
    }
    @GetMapping("/registration")
    public String registration() {

        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model){ //метод для создания пользователя
        if(!userService.createUser(user)){ //если пользователь не может быть создан
            model.addAttribute("errorMessage", "Пользователь с email: "+ user.getEmail()+" уже существует"); //сообщение об ошибке
            return "/registration"; //возвр представление "/registration", чтобы пользователь мог исправить ошибки
        }
        return "/redirect:/login"; //если пользователь создан - перенаправление на страницу входа
    }
    @GetMapping("/hello")
    public String securityUrl(){

        return "hello";
    }
}
