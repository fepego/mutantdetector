package com.meli.pruebatecnica.mutantdna.service;


import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MutantControllerTest {


    @Mock
    DnaMutantValidatorWrapper mutantValidatorWrapperService;
    @Mock
    JsonParserUtils jsonParserService;
    @Autowired
    MockMvc mockMvc;

    final String[] ValidMutantDnaChain = {"TTGCGA","CAATAC","TTATGT","AGAAGG","ACCCTA","TCACTG"};
    final String bodyMockParameter = "{\"dna\": [\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCATA\",\"TCACTG\"]}";
    final String ValidBodyMockParameter = "{\"dna\": [\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}";
    @Test
    public void WhenCallingMutantServiceWithValidDnaChainThenReturnHttpOk() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(ValidBodyMockParameter);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }
    @Test
    public void WhenCallingMutantServiceWithNonValidChainThenReturnForbidden() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(bodyMockParameter);

        mockMvc.perform(mockRequest)
                .andExpect(status().isForbidden());
    }

    @Test
    public void WhenCallingMutantServiceWithEmptyBodyThenReturnBadRequest() throws Exception {
       MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest());
    }
}
