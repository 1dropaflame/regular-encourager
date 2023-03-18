package com.dropaflame.encourage;

import com.dropaflame.encourage.model.Category;
import com.dropaflame.encourage.model.Encouragement;
import com.dropaflame.encourage.model.Tone;
import com.dropaflame.encourage.model.Topic;
import com.dropaflame.encourage.service.EncouragementService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EncouragerController.class)
class EncouragerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    EncouragerController encouragementController;

    @AfterEach
    void tearDown() {
    }

    @Test
    void quote() throws Exception {
        when(encouragementController.quote(anyString())).thenReturn(new Encouragement(-1, Category.Default, Topic.Default, Tone.Default, "We walk by Faith, not by Sight"));
        RequestBuilder request = MockMvcRequestBuilders.get("/quote").param("mood", "depressed").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request).andExpect(status().isOk())
                .andExpect(content().json("{id:-1,category:Default,tone:Default,topic:Default,message:\"We walk by Faith, not by Sight\"}", false))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void allQuotes_jsonpath_compare() throws Exception {
        when(encouragementController.allQuotes()).thenReturn(Arrays.asList(
                new Encouragement(0, Category.WhoIAmInChrist, Topic.AcceptedInChrist, Tone.Balanced, "message 0"),
                new Encouragement(1, Category.WhoIAmInChrist, Topic.AcceptedInChrist, Tone.Balanced, "message 1"),
                new Encouragement(2, Category.WhoIAmInChrist, Topic.AcceptedInChrist, Tone.Balanced, "message 2")
        ));
        RequestBuilder request = MockMvcRequestBuilders.get("/all-quotes").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request).andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].category",CoreMatchers.is("WhoIAmInChrist")))
                .andExpect(jsonPath("$.[0].topic",CoreMatchers.is("AcceptedInChrist")))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
    @Test
    void allQuotes_json_selective_compare() throws Exception {
        when(encouragementController.allQuotes()).thenReturn(Arrays.asList(
                new Encouragement(0, Category.WhoIAmInChrist, Topic.AcceptedInChrist, Tone.Balanced, "message 0"),
                new Encouragement(1, Category.WhoIAmInChrist, Topic.AcceptedInChrist, Tone.Balanced, "message 1"),
                new Encouragement(2, Category.WhoIAmInChrist, Topic.AcceptedInChrist, Tone.Balanced, "message 2")
        ));
        RequestBuilder request = MockMvcRequestBuilders.get("/all-quotes").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request).andExpect(status().isOk())
                .andExpect(content().json(
                        "[{category:WhoIAmInChrist,tone:Balanced,topic:AcceptedInChrist,message:\"message 0\"}," +
                        "{message:\"message 1\"}," +
                        "{id:2,category:WhoIAmInChrist,tone:Balanced}]"))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}