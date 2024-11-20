package com.enrique.core.domain.usecase;

import com.enrique.core.domain.entity.Price;
import org.springframework.stereotype.Repository;

@Repository
public interface GetApplicablePriceUseCase {

    Price getApplicablePrice(String applicationDate, Integer productId, Integer brandId);

}