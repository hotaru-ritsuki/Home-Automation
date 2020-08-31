package com.ritsuki.application.service.impl;

import com.ritsuki.application.constant.ErrorMessage;
import com.ritsuki.application.entity.Feature;
import com.ritsuki.application.exception.exceptions.NotDeletedException;
import com.ritsuki.application.repository.FeatureRepository;
import com.ritsuki.application.service.FeatureService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class FeatureServiceImpl implements FeatureService {

    private final FeatureRepository featureRepository;

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
