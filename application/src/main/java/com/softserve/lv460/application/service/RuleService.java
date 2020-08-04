package com.softserve.lv460.application.service;

import com.softserve.lv460.application.entity.Rule;

import java.util.List;

public interface RuleService {

    Rule create(Rule action);

    List<Rule> findAll();

    Rule findAction(Long id);

    Rule update(Rule entity);

    void delete(Long id);

    List<Rule> findAllByLocalDevice(String uuid);

    List<Rule> findByHome(Long homeId);

}
