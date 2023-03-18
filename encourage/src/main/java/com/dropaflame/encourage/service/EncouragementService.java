package com.dropaflame.encourage.service;

import com.dropaflame.encourage.model.Encouragement;
import org.springframework.stereotype.Service;

import java.util.List;
public interface EncouragementService {
    public Encouragement quote(String mood);
    List<Encouragement> allQuotes();
}
