package com.dropaflame.encourage.service;

import com.dropaflame.encourage.data.EncouragementRepository;
import com.dropaflame.encourage.meditate.Quotations;
import com.dropaflame.encourage.model.Encouragement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger
            = LoggerFactory.getLogger(EncouragementServiceImpl.class);
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
        logger.info("sent quote based on mood: {}", mood);
        return quotations.getQuotation(mood);
    }

    @Override
    public List<Encouragement> allQuotes() {
        List<Encouragement> quotes = repository.findAll();
        logger.info("sent all quotes... {} of them!", quotes.size());
        return quotes;
    }
}
