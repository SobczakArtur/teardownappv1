package pl.sobczakartur.teardownappv1.mainelectronics.substrates.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity.Substrate;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.service.SubstrateService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(SubstrateController.class)
public class SubstrateControllerTest {

    private final String API_BASE_URL = "/api/substrate";

    @Autowired
    private MockMvc mockMvc;

    @MockBean(name = "substrateServiceImpl")
    private SubstrateService substrateService;


    @Test
    void shouldReturnAllSubstrates() throws Exception {

        List<Substrate> substrates = List.of(Substrate.builder()
                                                    .substrateId(1L)
                                                    .assemblyName("Substrate A")
                                                    .substrateMarking("Description A")
                                                    .build(),
                                            Substrate.builder()
                                                    .substrateId(2L)
                                                    .assemblyName("Substrate B")
                                                    .substrateMarking("Description B")
                                                    .build());

        when(substrateService.getAllSubstrate()).thenReturn(substrates);

        mockMvc.perform(get(API_BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void shouldReturnSubstrateById() throws Exception {

        Substrate substrate = Substrate.builder()
                                .substrateId(1L)
                                .assemblyName("Substrate A")
                                .build();

        when(substrateService.getSubstrateById(1L)).thenReturn(Optional.of(substrate));

        mockMvc.perform(get(API_BASE_URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.substrateId").value(1))
                .andExpect(jsonPath("$.assemblyName").value("Substrate A"));
    }

    @Test
    void shouldReturn404WhenSubstrateNotFound() throws Exception {
        when(substrateService.getSubstrateById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get(API_BASE_URL + "/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldAddSubstrate() throws Exception {
//        Substrate substrate = new Substrate(1L, "New Substrate");
        Substrate substrate = Substrate.builder()
                                .substrateId(1L)
                                .assemblyName("New Substrate")
                                .build();
        when(substrateService.addSubstrate(any())).thenReturn(Optional.of(substrate));

        mockMvc.perform(post(API_BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"substrateId\":1,\"assemblyName\":\"New Substrate\"}"))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.substrateId").value(1))
                        .andExpect(jsonPath("$.assemblyName").value("New Substrate"));
    }

    @Test
    void shouldReturn500WhenAddSubstrateFails() throws Exception {
        when(substrateService.addSubstrate(any())).thenReturn(Optional.empty());

        mockMvc.perform(post(API_BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"substrateId\":1,\"assemblyName\":\"New Substrate\"}"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldUpdateSubstrate() throws Exception {
//        Substrate updatedSubstrate = new Substrate(1L, "Updated Substrate");
        Substrate updatedSubstrate = Substrate.builder()
                                        .substrateId(1L)
                                        .assemblyName("Updated Substrate")
                                        .build();
        when(substrateService.updatedSubstrate(any(), eq(1L))).thenReturn(Optional.of(updatedSubstrate));

        mockMvc.perform(put(API_BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"substrateId\":1,\"assemblyName\":\"Updated Substrate\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.assemblyName").value("Updated Substrate"));
    }

    @Test
    void shouldReturn404WhenUpdateFails() throws Exception {
        when(substrateService.updatedSubstrate(any(), eq(999L))).thenReturn(Optional.empty());

        mockMvc.perform(put(API_BASE_URL + "/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"substrateId\":999,\"assemblyName\":\"Non-existent\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldPartiallyUpdateSubstrate() throws Exception {
//        Substrate updatedSubstrate = new Substrate(1L, "Partially Updated Substrate");
        Substrate updatedSubstrate = Substrate.builder()
                                        .substrateId(1L)
                                        .assemblyName("Partially Updated Substrate")
                                        .build();
        when(substrateService.partiallyUpdatedSubstrate(any(), eq(1L))).thenReturn(Optional.of(updatedSubstrate));

        mockMvc.perform(patch(API_BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"assemblyName\":\"Partially Updated Substrate\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.assemblyName").value("Partially Updated Substrate"));
    }

    @Test
    void shouldReturn404WhenPartialUpdateFails() throws Exception {
        when(substrateService.partiallyUpdatedSubstrate(any(), eq(999L))).thenReturn(Optional.empty());

        mockMvc.perform(patch(API_BASE_URL + "/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"assemblyName\":\"Non-existent\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteSubstrate() throws Exception {
//        Substrate deletedSubstrate = new Substrate(1L, "Deleted Substrate");
        Substrate deletedSubstrate = Substrate.builder()
                                            .substrateId(1L)
                                            .assemblyName("Deleted Substrate")
                                            .build();
        when(substrateService.removeSubstrateById(1L)).thenReturn(Optional.of(deletedSubstrate));

        mockMvc.perform(delete(API_BASE_URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.assemblyName").value("Deleted Substrate"));
    }

    @Test
    void shouldReturn404WhenDeleteFails() throws Exception {
        when(substrateService.removeSubstrateById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(delete(API_BASE_URL + "/999"))
                .andExpect(status().isNotFound());
    }

}