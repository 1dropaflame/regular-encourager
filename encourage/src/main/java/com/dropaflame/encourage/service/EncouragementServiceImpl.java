package com.dropaflame.encourage.service;

import com.dropaflame.encourage.data.EncouragementRepository;
import com.dropaflame.encourage.meditate.Quotations;
import com.dropaflame.encourage.model.Encouragement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 TODO.
 1) Use Hibernate and H2 db instead of Quotations using the data in the arraylist.
 */
@Service
public class EncouragementServiceImpl implements EncouragementService {

    EncouragementRepository repository;
    @Autowired
    Quotations quotations;

    @Autowired
    public EncouragementServiceImpl(EncouragementRepository repository, Quotations quotations) {
        this.repository = repository;
        this.quotations = quotations;
    }

    @Override
    public Encouragement quote(String mood) {
//        TODO.
//        1) Use Hibernate and H2 db instead of Quotations using the data in the arraylist.
        return quotations.getQuotation(mood);
    }

    @Override
    public List<Encouragement> allQuotes() {
        return repository.findAll();
    }
}
