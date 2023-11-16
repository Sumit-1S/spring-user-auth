package com.examle.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.controller.ResApplication;
import com.example.entity.Discount;
import com.example.service.DiscountService;
import com.model.DiscountRegisterRequest;

@ExtendWith(MockitoExtension.class)
public class ResApplicationTest {

    @InjectMocks
    private ResApplication resApplication;

    @Mock
    private DiscountService discountService;



    private MockMvc mockMvc;

    @Test
    public void testRegisterValidate() throws Exception {
        // Create a sample discount request
        DiscountRegisterRequest discountRequest = new DiscountRegisterRequest(1, 10.0, true);

        // Mock the service response
        when(discountService.addDiscount(any(Discount.class))).thenReturn("Discount Added...");

        // Initialize MockMvc using standalone setup
        mockMvc = MockMvcBuilders.standaloneSetup(resApplication).build();

        // Perform the HTTP request and verify the response
        mockMvc.perform(post("/discountservice/adddiscount")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"policyId\":1,\"discount_price\":10.0,\"active\":true}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Discount Added..."));
    }

    @Test
    public void testLogin() throws Exception {
        // Create a sample list of discounts
        ArrayList<Discount> discountList = new ArrayList<>();
        discountList.add(new Discount(1, 10.0, true));
        discountList.add(new Discount(1, 15.0, true));

        // Mock the service response
        when(discountService.getAllDiscount(1)).thenReturn(discountList);

        // Initialize MockMvc using standalone setup
        mockMvc = MockMvcBuilders.standaloneSetup(resApplication).build();

        // Perform the HTTP request and verify the response
        mockMvc.perform(get("/discountservice/showdiscount/{policyId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].discountPrice").value(10.0))
                .andExpect(jsonPath("$[1].discountPrice").value(15.0));
    }
}
