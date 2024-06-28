package com.movies.rest;

import com.movies.service.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;
import java.util.TreeSet;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
public class ApiMoviesTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MovieService movieService;

    @Test
    public void testGetDirectorsWithResults() throws Exception {
        Set<String> mockDirectors = new TreeSet<>();
        mockDirectors.add("Martin Scorsese");
        mockDirectors.add("Woody Allen");

        when(movieService.getDirectors(any())).thenReturn(mockDirectors);

        mockMvc.perform(get("/api/directors?threshold=4")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().json("[\"Martin Scorsese\",\"Woody Allen\"]"));
    }

    @Test
    public void testGetDirectorsWithoutResults() throws Exception {
        Set<String> mockDirectors = new TreeSet<>();

        when(movieService.getDirectors(any())).thenReturn(mockDirectors);

        mockMvc.perform(get("/api/directors")
                        .param("threshold", "100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(content().string("Sorry, no results were found."));
    }
}
