package com.softserve.lv460.application.specification;

import com.softserve.lv460.application.dto.supportedDevice.SupportedDeviceFilterRequest;
import com.softserve.lv460.application.entity.Feature;
import com.softserve.lv460.application.entity.SupportedDevice;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

@AllArgsConstructor
public class SupportedDeviceSpecification implements Specification<SupportedDevice> {
  private SupportedDeviceFilterRequest filter;

  @Override
  public Predicate toPredicate(Root<SupportedDevice> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    return builder.and(findByModel(root, builder), findByBrand(root, builder), findByType(root, builder),
            findByFeatures(root, builder), findByReleaseYear(root, builder)
    );
  }

  private Predicate findByModel(Root<SupportedDevice> r, CriteriaBuilder cb) {
    Predicate predicate;
    if (filter.getModel() != null) {
      predicate = cb.equal(r.get("model"), filter.getModel());
    } else {
      predicate = cb.conjunction();
    }
    return predicate;
  }

  private Predicate findByBrand(Root<SupportedDevice> r, CriteriaBuilder cb) {
    Predicate predicate;
    if (filter.getBrand() != null) {
      predicate = cb.equal(r.get("brand"), filter.getBrand());
    } else {
      predicate = cb.conjunction();
    }
    return predicate;
  }

  private Predicate findByType(Root<SupportedDevice> r, CriteriaBuilder cb) {
    Predicate predicate;
    if (filter.getType() != null) {
      predicate = cb.equal(r.get("type"), filter.getType());
    } else {
      predicate = cb.conjunction();
    }
    return predicate;
  }

  private Predicate findByFeatures(Root<SupportedDevice> r, CriteriaBuilder cb) {
    Predicate predicate;
    if (filter.getFeaturesId() != null) {
      Join<SupportedDevice, Feature> feature = r.join("features");
      predicate = cb.and(cb.in(feature.get("id")).value(filter.getFeaturesId()));
    } else {
      predicate = cb.conjunction();
    }
    return predicate;
  }

  private Predicate findByReleaseYear(Root<SupportedDevice> r, CriteriaBuilder cb) {
    Predicate predicate;
    if (filter.getReleaseYear() != null) {
      predicate = cb.equal(r.get("releaseYear"), filter.getReleaseYear());
    } else {
      predicate = cb.conjunction();
    }
    return predicate;
  }


}
