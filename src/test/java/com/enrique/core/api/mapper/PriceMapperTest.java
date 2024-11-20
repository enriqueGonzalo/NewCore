package com.enrique.core.api.mapper;

import com.enrique.core.domain.entity.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PriceMapperTest {

    private PriceMapper priceMapper;

    @BeforeEach
    void setUp() {
        priceMapper = Mappers.getMapper(PriceMapper.class);
    }

    @Test
    void toDto_handlesNullPrice() {
        Price price = null;

        com.enrique.core.openapi.api.model.GetApplicablePriceResponseDTO dto = priceMapper.toDto(price);

        assertEquals(null, dto);
    }
}