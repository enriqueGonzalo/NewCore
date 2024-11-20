package com.enrique.core.api.mapper;

import com.enrique.core.domain.entity.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.enrique.core.openapi.api.model.GetApplicablePriceResponseDTO;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    @Mapping(source = "price", target = "finalPrice")
    @Mapping(source = "priceList", target = "applicablePriceList")
    @Mapping(source = "brandId", target = "brandId")
    @Mapping(source = "productId", target = "productId")
    @Mapping(target = "applicationDate", expression = "java(java.time.LocalDateTime.now())")
    GetApplicablePriceResponseDTO toDto(Price price);
}