package com.ritsuki.application.service;

import com.ritsuki.application.entity.Rule;

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
