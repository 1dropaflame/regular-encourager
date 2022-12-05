package com.dropaflame.encourage;

import com.dropaflame.config.Config;
import com.dropaflame.encourage.meditate.Quotations;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EncouragerController {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

    @GetMapping("/quote")
    public String quote(@RequestParam("mood") String mood) {
        Quotations quotations = context.getBean("quotations", Quotations.class);
        return quotations.getQuotation(mood);
    }

}
