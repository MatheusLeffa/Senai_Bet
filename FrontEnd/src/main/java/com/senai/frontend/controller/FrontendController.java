package com.senai.frontend.controller;

import com.senai.frontend.dto.NewBetDto;
import com.senai.frontend.dto.NewUserDto;
import com.senai.frontend.dto.ResponseDto;
import com.senai.frontend.integration.ApiGateway;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/frontend")
public class FrontendController {

    private final ApiGateway apiGateway;

    public FrontendController(ApiGateway apiGateway) {
        this.apiGateway = apiGateway;
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        ResponseDto response = apiGateway.getUsers().getBody();
        model.addAttribute("users", response.getResult());
        return "users"; // Thymeleaf template name
    }

    @GetMapping("/users/create")
    public String showCreateUserForm(Model model) {
        model.addAttribute("newUser", new NewUserDto());
        return "create-user";
    }

    @PostMapping("/users/create")
    public String createUser(@ModelAttribute NewUserDto newUserDto, Model model) {
        apiGateway.createUser(newUserDto);
        return "redirect:/frontend/users";
    }

    @GetMapping("/bets/create")
    public String showBetForm(Model model) {
        model.addAttribute("newBet", new NewBetDto());
        return "create-bet";
    }

    @PostMapping("/bets/create")
    public String createBet(@ModelAttribute NewBetDto newBetDto, Model model) {
        apiGateway.runIndividualGame(newBetDto);
        return "redirect:/frontend/users"; // or redirect to another summary page
    }
}
