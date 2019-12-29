package com.softserve.lv460.application.repository;

import com.softserve.lv460.application.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FeatureRepository extends JpaRepository<Feature, Long> {

}
