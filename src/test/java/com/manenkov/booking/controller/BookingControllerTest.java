package com.manenkov.booking.controller;

import com.manenkov.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class BookingControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
        MediaType.APPLICATION_JSON.getSubtype(),
        Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getMeetingsTest() throws Exception {
        mockMvc.perform(get("/booking").param("date", "2017-01-01"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.day", is("2017-01-01")))
            .andExpect(jsonPath("$.meetings", hasSize(0)));
    }

    @Test
    public void processBatchTest() throws Exception {
        mockMvc.perform(post("/booking").contentType(contentType).content("{\n" +
                "  \"start_office_hours\": \"0900\",\n" +
                "  \"end_office_hours\": \"1730\",\n" +
                "  \"booking_requests\": [\n" +
                "  \t{\n" +
                "    \"submission_time\": \"2011-03-17 10:17:06\",\n" +
                "    \"employee_id\": \"EMP001\",\n" +
                "    \"meeting_time\": \"2011-03-21 09:00\",\n" +
                "    \"duration\": 2\n" +
                "  },\n" +
                "  {\n" +
                "    \"submission_time\": \"2011-03-17 10:17:12\",\n" +
                "    \"employee_id\": \"EMP002\",\n" +
                "    \"meeting_time\": \"2011-03-21 14:00\",\n" +
                "    \"duration\": 2\n" +
                "  },\n" +
                "  {\n" +
                "    \"submission_time\": \"2011-03-17 10:17:13\",\n" +
                "    \"employee_id\": \"EMP002\",\n" +
                "    \"meeting_time\": \"2011-03-22 14:00\",\n" +
                "    \"duration\": 3\n" +
                "  }]\n" +
                "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.calendar", hasSize(2)))
                .andExpect(jsonPath("$.calendar[0].day", is("2011-03-22")))
                .andExpect(jsonPath("$.calendar[0].meetings", hasSize(1)));
    }

}