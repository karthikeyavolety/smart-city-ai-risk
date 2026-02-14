package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AccidentService {

    public Map<String, Object> calculateRisk(String weather,
                                             String roadType,
                                             int speed,
                                             String time) {

        int score = 0;

        // Weather scoring
        if (weather.equalsIgnoreCase("Rain")) score += 30;
        else if (weather.equalsIgnoreCase("Fog")) score += 25;
        else if (weather.equalsIgnoreCase("Snow")) score += 35;
        else score += 10;

        // Road scoring
        if (roadType.equalsIgnoreCase("Highway")) score += 30;
        else if (roadType.equalsIgnoreCase("City")) score += 20;
        else score += 15;

        // Speed scoring
        if (speed > 100) score += 40;
        else if (speed > 70) score += 25;
        else score += 10;

        // Time scoring
        if (time.equalsIgnoreCase("Night")) score += 25;
        else score += 10;

        String riskLevel;

        // 🔥 NEW LOGIC (YOUR CONDITION)
        if (score <= 40) {
            riskLevel = "Green Alert (Safe)";
        } 
        else if (score > 40 && score <= 60) {
            riskLevel = "Yellow Alert (Moderate Risk)";
        } 
        else if (score >= 80) {
            riskLevel = "Red Alert (High Risk)";
        } 
        else {
            riskLevel = "Red Alert (High Risk)";
        }

        List<String> tips = new ArrayList<>();

        if (riskLevel.contains("Red")) {
            tips.add("Reduce speed immediately.");
            tips.add("Avoid overtaking.");
            tips.add("Be extremely cautious.");
        } 
        else if (riskLevel.contains("Yellow")) {
            tips.add("Maintain safe distance.");
            tips.add("Drive carefully.");
        } 
        else {
            tips.add("Drive safely and follow traffic rules.");
        }

        Map<String, Object> result = new HashMap<>();
        result.put("score", score);
        result.put("riskLevel", riskLevel);
        result.put("tips", tips);

        return result;
    }
}
