package pl.sobczakartur.teardownappv1.mainelectronics.substrates.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity.Substrate;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.repository.SubstrateRepository;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.repository.UserRepository;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.service.SubstrateService;
import pl.sobczakartur.teardownappv1.security.jwt.JwtAuthFilter;
import pl.sobczakartur.teardownappv1.security.jwt.JwtService;
import pl.sobczakartur.teardownappv1.security.user.CustomUserDetailsService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@Import(TestSecurityConfig.class)
//@WebMvcTest(SubstrateController.class)
//@ActiveProfiles("test")
//@ImportAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
//@SpringBootTest(properties = {"debug=true"})


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SubstrateControllerTest {

    private static final String API_BASE_URL = "/api/substrate";

    @Autowired
    private MockMvc mockMvc;

    @MockBean(name="substrateServiceImpl")
    private SubstrateService substrateService;

//    @MockBean
//    private SubstrateService substrateService;

    @MockBean
    private JwtAuthFilter jwtAuthFilter;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private SubstrateRepository substrateRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;




    @Test
    @WithMockUser(username = "admin")
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
    @WithMockUser(username = "admin")
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
    @WithMockUser(username = "admin")
    void shouldReturn404WhenSubstrateNotFound() throws Exception {
        when(substrateService.getSubstrateById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get(API_BASE_URL + "/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "admin")
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
    @WithMockUser(username = "admin")
    void shouldReturn500WhenAddSubstrateFails() throws Exception {
        when(substrateService.addSubstrate(any())).thenReturn(Optional.empty());

        mockMvc.perform(post(API_BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"substrateId\":1,\"assemblyName\":\"New Substrate\"}"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(username = "admin")
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
    @WithMockUser(username = "admin")
    void shouldReturn404WhenUpdateFails() throws Exception {
        when(substrateService.updatedSubstrate(any(), eq(999L))).thenReturn(Optional.empty());

        mockMvc.perform(put(API_BASE_URL + "/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"substrateId\":999,\"assemblyName\":\"Non-existent\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "admin")
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
    @WithMockUser(username = "admin")
    void shouldReturn404WhenPartialUpdateFails() throws Exception {
        when(substrateService.partiallyUpdatedSubstrate(any(), eq(999L))).thenReturn(Optional.empty());

        mockMvc.perform(patch(API_BASE_URL + "/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"assemblyName\":\"Non-existent\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "admin")
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
    @WithMockUser(username = "admin")
    void shouldReturn404WhenDeleteFails() throws Exception {
        when(substrateService.removeSubstrateById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(delete(API_BASE_URL + "/999"))
                .andExpect(status().isNotFound());
    }

}