package com.example.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
public class PythonController {

    @GetMapping("/runPython")
    public String runPythonScript() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python", "springTest.py");
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line).append("\n");
            }

            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error executing python script";
        }
        
    }
}