package com.example.test.service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PythonScriptRunner {

    private static final String SCRIPT_PATH = "src/main/resources/scripts/springTest.py";

    @PostConstruct
    public void runScript() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python", SCRIPT_PATH);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Exited with code: " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
