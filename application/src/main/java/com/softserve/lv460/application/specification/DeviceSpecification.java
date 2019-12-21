package com.softserve.lv460.application.specification;

import com.softserve.lv460.application.dto.device.DeviceFilterRequest;
import com.softserve.lv460.application.entity.Device;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class DeviceSpecification implements Specification<Device> {
  private DeviceFilterRequest filter;

  public DeviceSpecification(DeviceFilterRequest filter) {
    this.filter = filter;
  }

  @Override

  public Predicate toPredicate(Root<Device> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    return builder.and(findByModel(root, builder), findByBrand(root, builder));
  }

  private Predicate findByModel(Root<Device> r, CriteriaBuilder cb) {
    Predicate predicate;
    if (filter.getModel() != null) {
      predicate = cb.equal(r.get("model"), filter.getModel());
    } else {
      predicate = cb.conjunction();
    }
    return predicate;
  }

  private Predicate findByBrand(Root<Device> r, CriteriaBuilder cb) {
    Predicate predicate;
    if (filter.getBrand() != null) {
      predicate = cb.equal(r.get("brand"), filter.getBrand());
    } else {
      predicate = cb.conjunction();
    }
    return predicate;
  }


}
