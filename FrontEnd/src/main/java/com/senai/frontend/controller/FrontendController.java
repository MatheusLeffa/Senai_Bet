package com.senai.frontend.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.senai.frontend.dto.NewBetDto;
import com.senai.frontend.dto.NewUserDto;
import com.senai.frontend.dto.ResponseDto;
import com.senai.frontend.entity.Bet;
import com.senai.frontend.entity.User;
import com.senai.frontend.integration.ApiGateway;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FrontendController {

    private final ApiGateway apiGateway;
    private final ObjectMapper objectMapper;

    public FrontendController(ApiGateway apiGateway, ObjectMapper objectMapper) {
        this.apiGateway = apiGateway;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("newUser", new NewUserDto());
        model.addAttribute("newBet", new NewBetDto());

        loadUsers(model);
        return "home";
    }

    @PostMapping("/create-user")
    public String createUser(@Valid @ModelAttribute("newUser") NewUserDto newUserDto,
                             BindingResult result,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            loadUsers(model); // helper to repopulate users
            model.addAttribute("newBet", new NewBetDto());
            return "home";
        }
        apiGateway.createUser(newUserDto);
        return "redirect:/";
    }

    @PostMapping("/place-bet")
    public String placeBet(@Valid @ModelAttribute("newBet") NewBetDto newBetDto,
                           BindingResult result,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            loadUsers(model);
            model.addAttribute("newUser", new NewUserDto());
            return "home";
        }

        ResponseEntity<ResponseDto> betResponse = apiGateway.runIndividualGame(newBetDto);
        if (betResponse.getBody().isSuccess()) {
            Bet bet = objectMapper.convertValue(betResponse.getBody().getResult(), Bet.class);
            redirectAttributes.addFlashAttribute("isWinner", bet.isWinner());
        }
        return "redirect:/";
    }

    private void loadUsers(Model model) {
        ResponseEntity<ResponseDto> usersResponse = apiGateway.getUsers();
        List<User> users = new ArrayList<>();
        if (usersResponse.getBody().isSuccess()) {
            users = objectMapper.convertValue(usersResponse.getBody().getResult(),
                    new TypeReference<List<User>>() {
                    });
        }
        model.addAttribute("users", users);
    }
}

