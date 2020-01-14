package com.softserve.lv460.application.service;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.entity.Feature;
import com.softserve.lv460.application.exception.exceptions.NotDeletedException;
import com.softserve.lv460.application.repository.FeatureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class FeatureService {

  private FeatureRepository featureRepository;


  public Feature create(Feature feature) {
    return featureRepository.save(feature);
  }

  public Feature findFeature(Long id) {
    return featureRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Feature with id " + id + "does not exists"));
  }

  public List<Feature> findAll() {
    return featureRepository.findAll();
  }

  public Feature update(Feature entity) {
    Feature feature = findFeature(entity.getId());
    feature.setName(entity.getName());
    feature.setDescription(entity.getDescription());
    return featureRepository.save(feature);
  }

  public Long delete(Long id) {
    if (!featureRepository.findById(id).isPresent()) {
      throw new NotDeletedException(ErrorMessage.FEATURE_NOT_DELETED_BY_ID + id);
    }
    featureRepository.deleteById(id);
    return id;
  }
}
