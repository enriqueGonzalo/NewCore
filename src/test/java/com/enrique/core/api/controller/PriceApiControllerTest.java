package com.enrique.core.api.controller;

import com.enrique.core.api.mapper.PriceMapper;
import com.enrique.core.application.usecase.GetApplicablePrice;
import com.enrique.core.domain.entity.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import com.enrique.core.openapi.api.model.GetApplicablePriceResponseDTO;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PriceApiControllerTest {

    @Mock
    private PriceMapper priceMapper;

    @Mock
    private GetApplicablePrice getApplicablePrice;

    @InjectMocks
    private PriceApiController priceApiController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getApplicablePrice_returnsPrice_whenPriceIsFound() {
        LocalDateTime applicationDate = LocalDateTime.now();
        Price price = new Price();
        com.enrique.core.openapi.api.model.GetApplicablePriceResponseDTO responseDTO = GetApplicablePriceResponseDTO.builder().build();

        when(getApplicablePrice.execute(applicationDate, 1L, 1L)).thenReturn(Optional.of(price));
        when(priceMapper.toDto(price)).thenReturn(responseDTO);

        ResponseEntity<com.enrique.core.openapi.api.model.GetApplicablePriceResponseDTO> response = priceApiController.getApplicablePrice(applicationDate.toString(), 1, 1);

        assertEquals(ResponseEntity.ok(responseDTO), response);
    }

    @Test
    void getApplicablePrice_returnsNotFound_whenPriceIsNotFound() {
        LocalDateTime applicationDate = LocalDateTime.now();

        when(getApplicablePrice.execute(applicationDate, 1L, 1L)).thenReturn(Optional.empty());

        ResponseEntity<com.enrique.core.openapi.api.model.GetApplicablePriceResponseDTO> response = priceApiController.getApplicablePrice(applicationDate.toString(), 1, 1);

        assertEquals(ResponseEntity.status(404).body(com.enrique.core.openapi.api.model.GetApplicablePriceResponseDTO.builder().build()), response);
    }

    @Test
    void getApplicablePrice_returnsBadRequest_whenDateIsInvalid() {
        ResponseEntity<com.enrique.core.openapi.api.model.GetApplicablePriceResponseDTO> response = priceApiController.getApplicablePrice("invalid-date", 1, 1);

        assertEquals(ResponseEntity.status(400).body(com.enrique.core.openapi.api.model.GetApplicablePriceResponseDTO.builder().build()), response);
    }
}