package com.ritsuki.application.service;

import com.ritsuki.application.entity.Action;

import java.util.List;

public interface ActionService {

    Action create(Action action);

    List<Action> findAll();

    Action findAction(Long id);

    Action update(Action entity);

    void delete(Long id);
}
