package com.jfp.files.controller;

import static com.jfp.files.pdf.util.ApiUtil.V1_MERGES_REPORT;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jfp.files.config.TestConfig;
import com.jfp.files.pdf.dto.response.FileProcessResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class MergeReportControllerTest extends TestConfig {

  @Test
  void givenAuthorizationNotSent_thenThrows401() throws Exception {

    mockMvc
        .perform(MockMvcRequestBuilders.get(V1_MERGES_REPORT).contentType(APPLICATION_JSON_VALUE))
        .andExpect(MockMvcResultMatchers.status().isUnauthorized());
  }

  @Test
  void givenRequest_whenFindSearch_thenReturnList() throws Exception {

    var response =
        mockMvc
            .perform(
                MockMvcRequestBuilders.get(V1_MERGES_REPORT)
                    .contentType(APPLICATION_JSON_VALUE)
                    .header(AUTHORIZATION, BASIC_AUTH))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    assertNotNull(response);

    List<FileProcessResponse> responsesDto =
        objectMapper.readValue(response, new TypeReference<>() {});

    assertNotNull(responsesDto);
  }
}
