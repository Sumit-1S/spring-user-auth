package com.DAO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.DAO.DiscountDAO;
import com.example.entity.Discount;
import com.example.service.DiscountService;
import com.example.service.DiscountServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DiscountDAOTest {

    @InjectMocks
    private DiscountServiceImpl discountService;

    @Mock
    private DiscountDAO discountRepository;
    
    public void init() {
 		MockitoAnnotations.openMocks(this);
 	}

    @Test
    public void testFindByPolicyId() {
        // Given
        Discount discount1 = new Discount(1, 0.1, true);
        Discount discount2 = new Discount(1, 0.2, true);
        Discount discount3 = new Discount(2, 0.3, true);

        when(discountRepository.findByPolicyId(1)).thenReturn(Arrays.asList(discount1, discount2));
        when(discountRepository.findByPolicyId(2)).thenReturn(Arrays.asList(discount3));

        // When
        List<Discount> discounts1 = discountRepository.findByPolicyId(1);
        List<Discount> discounts2 = discountRepository.findByPolicyId(2);

        // Then
        assertThat(discounts1).hasSize(2);
        assertThat(discounts1).containsExactlyInAnyOrder(discount1, discount2);

        assertThat(discounts2).hasSize(1);
        assertThat(discounts2).containsExactlyInAnyOrder(discount3);
    }

    // Add more test methods as needed
}


