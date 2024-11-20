package com.enrique.core.application.usecase;

import com.enrique.core.domain.entity.Price;
import com.enrique.core.domain.repositories.PriceRepository;
import com.enrique.core.domain.usecase.GetApplicablePriceUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Component
public class GetApplicablePrice {

    private final PriceRepository priceRepository;

    public GetApplicablePrice(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Optional<Price> execute(LocalDateTime applicationDate, Long productId, Long brandId) {
        return priceRepository.findApplicablePrice(applicationDate, productId, brandId);
    }
}
