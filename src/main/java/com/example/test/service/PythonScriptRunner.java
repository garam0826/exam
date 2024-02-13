package com.example.test.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class PythonScriptRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        try {

            String scriptPath = "scripts/springTest.py";

            // Python 인터프리터와 스크립트를 실행하는 명령어 구성
            String[] command = new String[] {"python", scriptPath};

            // Python 스크립트 실행
            Process process = Runtime.getRuntime().exec(command);

            // 스크립트 실행 결과 로그 출력 (확인용으로)
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 프로세스 종료 대기
            int exitCode = process.waitFor();
            System.out.println("Python script executed with exit code: " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

