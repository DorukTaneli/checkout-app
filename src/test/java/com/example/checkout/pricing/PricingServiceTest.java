package com.example.checkout.pricing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PricingServiceTest {

    @Mock private PricingRepository pricingRepository;
    private PricingService pricingService;

    @BeforeEach
    void setUp() {
        pricingService = new PricingService(pricingRepository);
    }

    @Test
    void getAllPricings() {
        //when
        pricingService.getAllPricings();
        //then
        verify(pricingRepository).findAll();
    }

    @Test
    void canAddPricing() {
        //given
        Pricing pricing = new Pricing('A', 40);

        //when
        pricingService.addNewPricing(pricing);

        //then
        ArgumentCaptor<Pricing> pricingArgumentCaptor = ArgumentCaptor.forClass(Pricing.class);
        verify(pricingRepository).save(pricingArgumentCaptor.capture());

        Pricing capturedPricing = pricingArgumentCaptor.getValue();
        assertEquals(pricing, capturedPricing);
    }

    @Test
    void willThrowWhenSkuAlreadyExists() {
        //given
        Pricing pricing = new Pricing('A', 40);

        given(pricingRepository.existsById(pricing.getSku())).willReturn(true);

        //when-then
        assertThrows(IllegalStateException.class, () -> {
            pricingService.addNewPricing(pricing);
        });
        verify(pricingRepository, never()).save(any());
    }
}