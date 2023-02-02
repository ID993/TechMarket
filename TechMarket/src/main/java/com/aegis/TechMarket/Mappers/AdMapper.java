package com.aegis.TechMarket.Mappers;

import com.aegis.TechMarket.DataTransferObjects.AdDto;
import com.aegis.TechMarket.Entities.Ad;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdMapper {

    Ad adDtoToAd(AdDto adDto);

    AdDto adToAdDto(Ad ad);
}
