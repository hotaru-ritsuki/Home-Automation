package com.softserve.lv460.application.specification;

import com.softserve.lv460.application.dto.deviceTemplate.DeviceTemplateFilterDTO;
import com.softserve.lv460.application.entity.DeviceTemplate;
import com.softserve.lv460.application.entity.Feature;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


public class DeviceTemplateSpecification implements Specification<DeviceTemplate> {
  private String model;
  private String brand;
  private String type;
  private Integer releaseYear;
  private List<Long> featuresId;

  public DeviceTemplateSpecification(DeviceTemplateFilterDTO filter) {
    if (filter != null) {
      model = filter.getModel();
      brand = filter.getBrand();
      type = filter.getType();
      releaseYear = filter.getReleaseYear();
      featuresId = filter.getFeaturesId();
    }
  }

  @Override
  public Predicate toPredicate(Root<DeviceTemplate> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    List<Predicate> predicates = new ArrayList<>();
    predicates.add(findByModel(root, builder));
    predicates.add(findByBrand(root, builder));
    predicates.add(findByType(root, builder));
    predicates.add(findByFeatures(root, builder, query));
    predicates.add(findByReleaseYear(root, builder));
    return builder.and(predicates.toArray(new Predicate[0]));
  }

  private Predicate findByModel(Root<DeviceTemplate> r, CriteriaBuilder cb) {
    Predicate predicate;
    if (model != null && !model.equals("")) {
      predicate = cb.equal(r.get("model"), model);
    } else {
      predicate = cb.conjunction();
    }
    return predicate;
  }

  private Predicate findByBrand(Root<DeviceTemplate> r, CriteriaBuilder cb) {
    Predicate predicate;
    if (brand != null && !brand.equals("")) {
      predicate = cb.equal(r.get("brand"), brand);
    } else {
      predicate = cb.conjunction();
    }
    return predicate;
  }

  private Predicate findByType(Root<DeviceTemplate> r, CriteriaBuilder cb) {
    Predicate predicate;
    if (type != null && !type.equals("")) {
      predicate = cb.equal(r.get("type"), type);
    } else {
      predicate = cb.conjunction();
    }
    return predicate;
  }

  private Predicate findByFeatures(Root<DeviceTemplate> r, CriteriaBuilder cb, CriteriaQuery query) {
    Predicate predicate;
    if (featuresId != null && !featuresId.isEmpty()) {
      Join<DeviceTemplate, Feature> feature = r.join("features");
      predicate = cb.and(cb.in(feature.get("id")).value(featuresId));
      query.distinct(true);
    } else {
      predicate = cb.conjunction();
    }
    return predicate;
  }

  private Predicate findByReleaseYear(Root<DeviceTemplate> r, CriteriaBuilder cb) {
    Predicate predicate;
    if (releaseYear != null) {
      predicate = cb.equal(r.get("releaseYear"), releaseYear);
    } else {
      predicate = cb.conjunction();
    }
    return predicate;
  }
}