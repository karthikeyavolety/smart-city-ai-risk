package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class RiskService {

    public int calculateRisk(String weather, String roadType, int speed, String time) {

        int riskScore = 0;

        if(weather.equalsIgnoreCase("Rain")) riskScore += 20;
        if(weather.equalsIgnoreCase("Fog")) riskScore += 30;

        if(roadType.equalsIgnoreCase("Highway")) riskScore += 20;
        if(roadType.equalsIgnoreCase("City")) riskScore += 15;
        if(roadType.equalsIgnoreCase("Rural")) riskScore += 25;

        riskScore += speed / 2;

        if(time.equalsIgnoreCase("Night")) riskScore += 20;

        if(riskScore > 100) riskScore = 100;

        return riskScore;
    }
}
