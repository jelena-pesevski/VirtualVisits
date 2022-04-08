package org.unibl.etf.virtualvisits.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.virtualvisits.models.responses.StatisticsResponse;
import org.unibl.etf.virtualvisits.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/statistics")
    public StatisticsResponse getStatistics(){
       return userService.getStatistics();
    }
}
