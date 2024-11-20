package com.enrique.core.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.enrique.core.domain.entity.Price;
import com.enrique.core.infrastructure.PriceJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class PriceRepositoryImplTest {

    @Mock
    private PriceJpaRepository priceJpaRepository;

    @InjectMocks
    private PriceRepositoryImpl priceRepositoryImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findApplicablePrice_returnsPriceWhenFound() {
        LocalDateTime applicationDate = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 1L;
        Price price = new Price();
        when(priceJpaRepository.findApplicablePrice(applicationDate, productId, brandId)).thenReturn(Optional.of(price));

        Optional<Price> result = priceRepositoryImpl.findApplicablePrice(applicationDate, productId, brandId);

        assertTrue(result.isPresent());
        assertEquals(price, result.get());
    }

    @Test
    void findApplicablePrice_returnsEmptyWhenNotFound() {
        LocalDateTime applicationDate = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 1L;
        when(priceJpaRepository.findApplicablePrice(applicationDate, productId, brandId)).thenReturn(Optional.empty());

        Optional<Price> result = priceRepositoryImpl.findApplicablePrice(applicationDate, productId, brandId);

        assertTrue(result.isEmpty());
    }
}