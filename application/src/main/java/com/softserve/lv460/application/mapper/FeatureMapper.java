package com.softserve.lv460.application.mapper;

import com.softserve.lv460.application.dto.feature.FeatureDTO;
import com.softserve.lv460.application.entity.Feature;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class FeatureMapper implements Mapper<Feature, FeatureDTO> {
  private ModelMapper modelMapper;

  @Override
  public Feature toEntity(FeatureDTO dto) {
    Feature feature = modelMapper.map(dto, Feature.class);
    feature.setId(dto.getId());
    feature.setName(dto.getName());
    feature.setDescription(dto.getDescription());
    return feature;
  }

  @Override
  public FeatureDTO toDto(Feature feature) {
    FeatureDTO dto = modelMapper.map(feature, FeatureDTO.class);
    dto.setId(feature.getId());
    dto.setName(feature.getName());
    dto.setDescription(feature.getDescription());
    return dto;
  }
}
