package com.ritsuki.application.mapper.feature;

import com.ritsuki.application.dto.feature.FeatureDTO;
import com.ritsuki.application.entity.Feature;
import com.ritsuki.application.mapper.Mapper;
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
