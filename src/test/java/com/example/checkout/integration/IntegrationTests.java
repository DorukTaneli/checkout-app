package com.example.checkout.integration;

import com.example.checkout.offer.Offer;
import com.example.checkout.pricing.Pricing;
import com.example.checkout.scan.Scan;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-it.properties"
)
@AutoConfigureMockMvc
public class IntegrationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void performFullCheckout() throws Exception {
        // given
        Pricing pricingA = new Pricing('A', 40);
        Pricing pricingB = new Pricing('B', 50);
        Pricing pricingC = new Pricing('C', 25);
        Pricing pricingD = new Pricing('D', 20);

        Offer offerA = new Offer('A', 3, 20);
        Offer offerB = new Offer('B', 2, 20);

        Scan scanA = new Scan('A', 3);
        Scan scanB1 = new Scan('B', 3);
        Scan scanB2 = new Scan('B', 1);
        Scan scanC = new Scan('C', 1);
        Scan scanD = new Scan('D', 1);

        mockMvc.perform(post("/api/v1/pricing")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pricingA)));
        mockMvc.perform(post("/api/v1/pricing")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pricingB)));
        mockMvc.perform(post("/api/v1/pricing")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pricingC)));
        mockMvc.perform(post("/api/v1/pricing")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pricingD)));

        mockMvc.perform(post("/api/v1/offer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(offerA)));
        mockMvc.perform(post("/api/v1/offer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(offerB)));

        mockMvc.perform(post("/api/v1/scan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(scanA)));
        mockMvc.perform(post("/api/v1/scan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(scanB1)));
        mockMvc.perform(post("/api/v1/scan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(scanB2)));
        mockMvc.perform(post("/api/v1/scan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(scanC)));
        mockMvc.perform(post("/api/v1/scan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(scanD)));

        // when
        ResultActions resultActions = mockMvc.perform(get("/api/v1/checkout"));

        // then
        resultActions.andExpect(status().isOk());
        assertEquals("305", resultActions.andReturn().getResponse().getContentAsString());
    }

}
