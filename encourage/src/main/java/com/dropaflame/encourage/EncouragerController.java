package com.dropaflame.encourage;

import com.dropaflame.encourage.model.Encouragement;
import com.dropaflame.encourage.service.EncouragementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@RestController
public class EncouragerController {
    @Autowired
    EncouragementService encouragementService;
    @Value("${GREETING_FROM_ENVIRONMENT_VARIABLE:default}")
    String greeting;
    @Value("${FAREWELL_FROM_ENVIRONMENT_VARIABLE:default}")
    String farewell;
    @GetMapping("/quote")
    public Encouragement quote(@RequestParam("mood") String mood) {
        return encouragementService.quote(mood);
    }

    @GetMapping("/all-quotes")
    public List<Encouragement> allQuotes() {
        return encouragementService.allQuotes();
    }
    @GetMapping("")
    public String alive(@Value("${ENCOURAGE_PASSWORD:default-secret}") String passwordFromSecret, @RequestParam(value = "password", defaultValue = "default-input") String passwordFromUser) throws IOException {
        Resource resource = new ClassPathResource("logs/logging_date.txt");
        File file = resource.getFile();
        StringBuilder preamble = new StringBuilder();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String strCurrentLine;
            while ((strCurrentLine = bufferedReader.readLine()) != null) {
                System.out.println(strCurrentLine);
                preamble.append(strCurrentLine);
            }
        } catch(IOException ioException) {
            ioException.printStackTrace();
        }


        if(passwordFromSecret.equals(passwordFromUser)) {
            return preamble + " \n" + greeting + " Thank you Jesus for I am alive, yay!" + " but now I am busy learning, so " + farewell;
        } else {
            return preamble + " \n" + "No entry - you did not provide the password! You provided: " + passwordFromUser + " Next time :) say: " + passwordFromSecret;
        }
    }
}

