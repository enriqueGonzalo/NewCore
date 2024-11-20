package com.enrique.core.application.usecase;

import com.enrique.core.domain.entity.Price;
import com.enrique.core.domain.repositories.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GetApplicablePriceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private GetApplicablePrice getApplicablePrice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_returnsPrice_whenPriceIsFound() {
        LocalDateTime applicationDate = LocalDateTime.now();
        Price price = new Price();

        when(priceRepository.findApplicablePrice(applicationDate, 1L, 1L)).thenReturn(Optional.of(price));

        Optional<Price> result = getApplicablePrice.execute(applicationDate, 1L, 1L);

        assertEquals(Optional.of(price), result);
    }

    @Test
    void execute_returnsEmpty_whenPriceIsNotFound() {
        LocalDateTime applicationDate = LocalDateTime.now();

        when(priceRepository.findApplicablePrice(applicationDate, 1L, 1L)).thenReturn(Optional.empty());

        Optional<Price> result = getApplicablePrice.execute(applicationDate, 1L, 1L);

        assertEquals(Optional.empty(), result);
    }
}