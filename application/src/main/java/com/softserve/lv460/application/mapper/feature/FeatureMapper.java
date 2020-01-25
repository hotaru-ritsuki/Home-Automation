package com.softserve.lv460.application.mapper.feature;

import com.softserve.lv460.application.dto.feature.FeatureDTO;
import com.softserve.lv460.application.entity.Feature;
import com.softserve.lv460.application.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class FeatureMapper implements Mapper<Feature, FeatureDTO> {
  private ModelMapper modelMapper;

  @Override
  public Feature toEntity(FeatureDTO dto) {
    return modelMapper.map(dto, Feature.class);
  }

  @Override
  public FeatureDTO toDto(Feature feature) {
    return modelMapper.map(feature, FeatureDTO.class);
  }
}
