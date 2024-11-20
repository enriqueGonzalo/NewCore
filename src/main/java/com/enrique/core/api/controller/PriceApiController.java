package com.enrique.core.api.controller;

import com.enrique.core.api.mapper.PriceMapper;
import com.enrique.core.application.usecase.GetApplicablePrice;
import com.enrique.core.domain.entity.Price;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.enrique.core.openapi.api.PricesApi;
import com.enrique.core.openapi.api.model.GetApplicablePriceResponseDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Slf4j
@RestController
public class PriceApiController implements PricesApi {


    private final PriceMapper priceMapper;

    private final GetApplicablePrice getApplicablePrice;

    public PriceApiController(PriceMapper priceMapper, GetApplicablePrice getApplicablePrice) {
        this.priceMapper = priceMapper;
        this.getApplicablePrice = getApplicablePrice;
    }

    @Override
    public ResponseEntity<GetApplicablePriceResponseDTO> getApplicablePrice(String applicationDate, Integer productId, Integer brandId) {

        try {
            LocalDateTime parsedApplicationDate = LocalDateTime.parse(applicationDate);

            Optional<Price> price = getApplicablePrice.execute(parsedApplicationDate, productId.longValue(), brandId.longValue());

            return price.map(p -> ResponseEntity.ok(priceMapper.toDto(p)))
                    .orElse(ResponseEntity.status(404).body(GetApplicablePriceResponseDTO.builder().build()));

        } catch (DateTimeParseException e) {
            log.error("Error parsing date: {}", applicationDate);
            return ResponseEntity.status(400).body(GetApplicablePriceResponseDTO.builder().build());
        }
    }
}
