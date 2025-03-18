package pl.sobczakartur.teardownappv1.mainelectronics.substrates.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity.Substrate;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.sevice.SubstrateService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SubstrateController.class)
public class SubstrateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(name = "substrateServiceImpl")
    private SubstrateService substrateService;


    @Test
    void shouldReturnSubstrateList() throws Exception {

        List<Substrate> substrates = List.of(new Substrate(), new Substrate());
        when(substrateService.getAllSubstrate()).thenReturn(substrates);

        mockMvc.perform(get("/api/substrate"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

    }
}