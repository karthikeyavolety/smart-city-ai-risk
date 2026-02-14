package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@CrossOrigin
public class AccidentController {

    @Autowired
    private AccidentService service;

    @Autowired
    private AnalysisService analysisService;

    // Risk Prediction
    @PostMapping("/predict")
    public Map<String, Object> predict(
            @RequestBody Map<String, Object> input) {

        String weather = input.get("weather").toString();
        String roadType = input.get("roadType").toString();
        int speed = Integer.parseInt(
                input.get("speed").toString());
        String time = input.get("time").toString();

        return service.calculateRisk(
                weather, roadType, speed, time);
    }

    // Excel Upload + Analysis
    @PostMapping("/upload-analysis")
    public Map<String, Integer> uploadExcel(
            @RequestParam("file") MultipartFile file) {

        return analysisService.analyzeUploadedFile(file);
    }
}
