package com.softserve.lv460.application.service;

import com.softserve.lv460.application.entity.Action;

import java.util.List;

public interface ActionService {

    Action create(Action action);

    List<Action> findAll();

    Action findAction(Long id);

    Action update(Action entity);

    void delete(Long id);
}
