package com.softserve.lv460.application.service;

import com.softserve.lv460.application.dto.feature.FeatureRequest;
import com.softserve.lv460.application.entity.Feature;
import com.softserve.lv460.application.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.stereotype.Service;

@Service
public class FeatureService {
  @Autowired
  private FeatureRepository featureRepository;

  public Feature create(FeatureRequest fR){
    Feature feature = new Feature();
    feature.setName(fR.getName());
    feature.setDescription(fR.getDescription());
    return featureRepository.save(feature);
  }

  public Feature findFeature(Long id){
    return featureRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Feature with id " + id + "does not exists"));
  }

  public Feature update(Long id, FeatureRequest fR){
    Feature feature = findFeature(id);
    feature.setName(fR.getName());
    feature.setDescription(fR.getDescription());
    return featureRepository.save(feature);
  }

  public void delete(Long id){
    Feature feature = findFeature(id);
    featureRepository.delete(feature);
  }

}
