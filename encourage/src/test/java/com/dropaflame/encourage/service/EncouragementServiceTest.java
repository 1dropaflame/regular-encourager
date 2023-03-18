package com.dropaflame.encourage.service;

import com.dropaflame.encourage.data.EncouragementRepository;
import com.dropaflame.encourage.model.Category;
import com.dropaflame.encourage.model.Encouragement;
import com.dropaflame.encourage.model.Tone;
import com.dropaflame.encourage.model.Topic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class EncouragementServiceTest {

    @InjectMocks
    EncouragementServiceImpl encouragementService;
    @Mock
    EncouragementRepository repository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void quote() {

    }

    @Test
    void allQuotes() {
        when(repository.findAll()).thenReturn(
                Arrays.asList(
                        new Encouragement(0, Category.WhoIAmInChrist, Topic.AcceptedInChrist, Tone.Balanced, "message 0"),
                        new Encouragement(1, Category.WhoIAmInChrist, Topic.AcceptedInChrist, Tone.Balanced, "message 1"),
                        new Encouragement(2, Category.WhoIAmInChrist, Topic.AcceptedInChrist, Tone.Balanced, "message 2")
                )
        );
        assertEquals(3, encouragementService.allQuotes().size());
    }
}