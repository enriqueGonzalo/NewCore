package com.enrique.core.infrastructure.repository;

import com.enrique.core.domain.entity.Price;
import com.enrique.core.domain.repositories.PriceRepository;
import com.enrique.core.infrastructure.PriceJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PriceRepositoryImpl implements PriceRepository {
    private final PriceJpaRepository priceJpaRepository;

    @Override
    public Optional<Price> findApplicablePrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        return priceJpaRepository.findApplicablePrice(applicationDate, productId, brandId);
    }
}