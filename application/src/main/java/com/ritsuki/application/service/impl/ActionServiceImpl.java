package com.ritsuki.application.service.impl;

import com.ritsuki.application.constant.ErrorMessage;
import com.ritsuki.application.entity.Action;
import com.ritsuki.application.exception.exceptions.NotDeletedException;
import com.ritsuki.application.repository.ActionRepository;
import com.ritsuki.application.service.ActionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class ActionServiceImpl implements ActionService {

    private ActionRepository actionRepository;

    public Action create(Action action) {
        return actionRepository.save(action);
    }

    public List<Action> findAll() {
        return actionRepository.findAll();
    }

    public Action findAction(Long id) {
        return actionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format(ErrorMessage.ACTION_NOT_FOUND_BY_ID, id)));
    }

    public Action update(Action entity) {
        Action action = findAction(entity.getId());
        action.setDescription(entity.getDescription());
        action.setType(entity.getType());
        return actionRepository.save(action);
    }

    public void delete(Long id) {
        if (!actionRepository.findById(id).isPresent()) {
            throw new NotDeletedException(String.format(ErrorMessage.ACTION_NOT_DELETED_BY_ID, id));
        }
        actionRepository.deleteById(id);
    }
}
