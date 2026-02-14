package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RiskController {

    @Autowired
    private RiskService riskService;

    @PostMapping("/predict")
    public int predictRisk(@RequestParam String weather,
                           @RequestParam String roadType,
                           @RequestParam int speed,
                           @RequestParam String time) {

        return riskService.calculateRisk(weather, roadType, speed, time);
    }
}
