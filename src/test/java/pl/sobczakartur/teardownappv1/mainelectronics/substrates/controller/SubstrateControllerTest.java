package pl.sobczakartur.teardownappv1.mainelectronics.substrates.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity.Substrate;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.sevice.SubstrateService;

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

    @Autowired
    private MockMvc mockMvc;

    @MockBean(name = "substrateServiceImpl")
    private SubstrateService substrateService;


    @Test
    void shouldReturnAllSubstrates() throws Exception {
//        List<Substrate> substrates = List.of(new Substrate(1L, "Substrate A"), new Substrate(2L, "Substrate B"));
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

        mockMvc.perform(get("/api/substrate"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void shouldReturnSubstrateById() throws Exception {

        Long substrateId = 1L;
        Substrate substrate = Substrate.builder()
                .substrateId(substrateId)
                .assemblyName("Substrate A")
                .build();

        when(substrateService.getSubstrateById(substrateId)).thenReturn(Optional.of(substrate));

        mockMvc.perform(get("/api/substrate/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.substrateId").value(1))
                .andExpect(jsonPath("$.assemblyName").value("Substrate A"));
    }

    @Test
    void shouldReturn404WhenSubstrateNotFound() throws Exception {
        when(substrateService.getSubstrateById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/substrate/999"))
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

        mockMvc.perform(post("/api/substrate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"substrateId\":1,\"assemblyName\":\"New Substrate\"}"))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.substrateId").value(1))
                        .andExpect(jsonPath("$.assemblyName").value("New Substrate"));
    }

    @Test
    void shouldReturn500WhenAddSubstrateFails() throws Exception {
        when(substrateService.addSubstrate(any())).thenReturn(Optional.empty());

        mockMvc.perform(post("/api/substrate")
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

        mockMvc.perform(put("/api/substrate/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"substrateId\":1,\"assemblyName\":\"Updated Substrate\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.assemblyName").value("Updated Substrate"));
    }

    @Test
    void shouldReturn404WhenUpdateFails() throws Exception {
        when(substrateService.updatedSubstrate(any(), eq(999L))).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/substrate/999")
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

        mockMvc.perform(patch("/api/substrate/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"assemblyName\":\"Partially Updated Substrate\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.assemblyName").value("Partially Updated Substrate"));
    }

    @Test
    void shouldReturn404WhenPartialUpdateFails() throws Exception {
        when(substrateService.partiallyUpdatedSubstrate(any(), eq(999L))).thenReturn(Optional.empty());

        mockMvc.perform(patch("/api/substrate/999")
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

        mockMvc.perform(delete("/api/substrate/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.assemblyName").value("Deleted Substrate"));
    }

    @Test
    void shouldReturn404WhenDeleteFails() throws Exception {
        when(substrateService.removeSubstrateById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/substrate/999"))
                .andExpect(status().isNotFound());
    }
}



//        1	3	S-Pen Board	SIMPLE	onsemi	0.7439024390243902	ascadewca	FOUR_LAYER_C	R4F4	4 Layer C	4	0.541	3.3	5
//        2	6.32	120Hz Display Flex	SIMPLE	Samsung	1.2523383084577115	sdvcscscsasdf	FOUR_LAYER_FV	Poly	4 Layer FwV	4	1.1035833333333334	2.7	4.63
//        3	10.96	Main Board	HIGH	Red-Board	3.2451063829787237	asckiksinmdkcxmd	FOUR_LAYER_B	R4F4	4 Layer B	4	2.0029166666666667	7.7	8.12
//        4	4.34	Auxiliary Board	LOW	Red-Board	1.2721138211382113	sdlwkjxmell,	THREE_LAYER_C	R4F4	3 Layer C	3	0.8873333333333333	5.81	3.99
//        5	3.68	Battery Board	SIMPLE	Unknown	0.8572357723577236	slkiemshgk	FOUR_LAYER_C	R4F4	4 Layer C	4	0.6543333333333334	6.55	3
//        6	3.89	Fingerprint Flex	LOW	Qualcomm	0.9468407960199006	None	TWO_LAYER_FV	Poly	2 Layer FwV	2	0.9498333333333333	2.3	2.15
//        8	1.5	2MP Front Camera Flex	LOW	Unknown	0.6977611940298507	slkkiemmxk	THREE_LAYER_FV	Poly	3 Layer FwV	3	0.45099999999999996	6.4	4.05
//        9	4.5	20MP Rear Camera Flex	SIMPLE	Apple	0.8992537313432836	askmxmka	THREE_LAYER_FV	Poly	3 Layer FwV	3	0.817	7.95	4.65
//        10	6.72	48MP Rear Camera Flex	LOW	Apple	1.5021656050955412	askmdwmmx	FOUR_LAYER_F	Poly	4 Layer F	4	1.3555	8.01	4.9