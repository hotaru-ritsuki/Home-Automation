package com.softserve.lv460.application.service;

import com.softserve.lv460.application.entity.Feature;

import java.util.List;

public interface FeatureService {

    Feature create(Feature feature);

    Feature findFeature(Long id);

    List<Feature> findAll();

    Feature update(Feature entity);

    Long delete(Long id);
}
