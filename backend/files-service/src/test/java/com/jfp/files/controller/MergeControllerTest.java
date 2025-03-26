package com.jfp.files.controller;

import static com.jfp.files.pdf.util.ApiUtil.V1_MERGE_API;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.jfp.files.config.TestConfig;
import com.jfp.files.pdf.dto.request.CreateMergeRequest;
import com.jfp.files.pdf.service.MergeService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class MergeControllerTest extends TestConfig {

  @Autowired private MergeService mergeService;

  @Test
  void givenAuthorizationNotSent_thenThrows401() throws Exception {
    final CreateMergeRequest createMergeRequest =
        new CreateMergeRequest("fileName", List.of("file1", "file2"));
    mockMvc
        .perform(
            MockMvcRequestBuilders.get(V1_MERGE_API)
                .content(objectMapper.writeValueAsString(createMergeRequest))
                .contentType(APPLICATION_JSON_VALUE))
        .andExpect(MockMvcResultMatchers.status().isUnauthorized());
  }
}
