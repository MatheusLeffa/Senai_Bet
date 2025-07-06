package com.senai.betapi.controller;

import com.senai.betapi.service.BetService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Tag(name = "Bets", description = "Bets API")
@Controller
//@CrossOrigin(origins = "http://localhost:9005")
public class BetController {

    @Autowired
    private BetService betService;


}
