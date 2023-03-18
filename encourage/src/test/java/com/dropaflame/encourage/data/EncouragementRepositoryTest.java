package com.dropaflame.encourage.data;

import com.dropaflame.encourage.model.Encouragement;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@DataJpaTest
class EncouragementRepositoryTest {
@Autowired
EncouragementRepository repository;
    @BeforeEach
    void setUp() {
    }
    @Test
    public void find_all() {
        List<Encouragement> list = repository.findAll();
        assertThat(list).asList().hasSize(33);
        System.out.format("Of these %d contain God\n",list.stream().filter((Encouragement x)-> x.getMessage().contains("God")).count());
        System.out.format("Of these %d contain Christ\n",list.stream().filter((Encouragement x)-> x.getMessage().contains("Christ")).count());
        DocumentContext documentContext = JsonPath.parse(list);
//        System.out.format("$.length() = %s \n", documentContext.read("$.length()"));
        System.out.println("$.length() = " + documentContext.read("$.length()"));
        System.out.println("$..id = " + documentContext.read("$..id"));
        System.out.println("$.[1] = " + documentContext.read("$.[1]"));
        System.out.println("$.[0:2] = " + documentContext.read("$.[0:2]"));
//        System.out.println("$.[2].message = " + documentContext.read("$.[2].message"));

        System.out.println("$.[?(@.category=='WhoIAmInChrist')] = " + documentContext.read("$.[?(@.category=='WhoIAmInChrist')]"));

    }
}