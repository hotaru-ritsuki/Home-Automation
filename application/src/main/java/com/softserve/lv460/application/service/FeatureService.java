package com.softserve.lv460.application.service;

import com.softserve.lv460.application.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeatureService {
  @Autowired
  private FeatureRepository featureRepository;

  public FeatureRepository create()

}
