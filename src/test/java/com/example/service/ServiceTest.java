package com.example.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.DAO.DiscountDAO;
import com.example.entity.Discount;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    private DiscountDAO discountDAO;

    @InjectMocks
    private DiscountServiceImpl discountService;
    public void init() {
		MockitoAnnotations.openMocks(this);
	}
    @Test
    public void testAddDiscount() throws Exception {
        Discount discount = new Discount(1, 10.0, true);

        // Mocking the behavior of discountDAO.save method
        when(discountDAO.save(discount)).thenReturn(discount);

        String result = discountService.addDiscount(discount);

        assertEquals("Discount Added..", result);

        // Verify that the save method was called with the correct argument
        verify(discountDAO, times(1)).save(discount);
    }

    @Test
    public void testGetAllDiscount() {
        Integer policyId = 1;
        List<Discount> expectedDiscounts = new ArrayList<>();
        expectedDiscounts.add(new Discount(1, 10.0, true));
        expectedDiscounts.add(new Discount(1, 15.0, true));

        // Mocking the behavior of discountDAO.findByPolicyId method
        when(discountDAO.findByPolicyId(policyId)).thenReturn(expectedDiscounts);

        List<Discount> result = discountService.getAllDiscount(policyId);

        // Verify that the findByPolicyId method was called with the correct argument
        verify(discountDAO, times(1)).findByPolicyId(policyId);

        // Verify that the result matches the expectedDiscounts
        assertEquals(expectedDiscounts, result);
    }
}
