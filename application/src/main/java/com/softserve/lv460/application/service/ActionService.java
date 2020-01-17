package com.softserve.lv460.application.service;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.entity.Action;
import com.softserve.lv460.application.exception.exceptions.NotDeletedException;
import com.softserve.lv460.application.repository.ActionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ActionService {

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
      throw new NotDeletedException(ErrorMessage.ACTION_NOT_DELETED_BY_ID + id);
    }
    actionRepository.deleteById(id);
  }
}
