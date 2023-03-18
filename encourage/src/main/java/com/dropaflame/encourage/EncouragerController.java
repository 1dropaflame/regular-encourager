package com.dropaflame.encourage;

import com.dropaflame.encourage.model.Encouragement;
import com.dropaflame.encourage.service.EncouragementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EncouragerController {
    @Autowired
    EncouragementService encouragementService;
    @Value("${GREETING_FROM_ENVIRONMENT_VARIABLE:default}")
    String greeting;
    @GetMapping("/quote")
    public Encouragement quote(@RequestParam("mood") String mood) {
        return encouragementService.quote(mood);
    }

    @GetMapping("/all-quotes")
    public List<Encouragement> allQuotes() {
        return encouragementService.allQuotes();
    }
    @GetMapping("")
    public String alive() {
        return greeting + " I am alive, yay!";
    }
}

