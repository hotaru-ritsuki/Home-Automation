package com.softserve.lv460.application.service;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.entity.Home;
import com.softserve.lv460.application.exception.exceptions.HomeAlreadyRegisterException;
import com.softserve.lv460.application.exception.exceptions.NotDeletedException;
import com.softserve.lv460.application.repository.HomeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class HomeService {

  private HomeRepository homeRepository;
  private LocationService locationService;

  public Home create(Home request) {
    Optional<Home> isHome = homeRepository.findByAddressaLike(request.getAddressa());
    if (isHome.isPresent()) {
      throw new HomeAlreadyRegisterException(String.format(ErrorMessage.HOME_ALREADY_REGISTER, request.getAddressa()));
    }
    return homeRepository.save(request);
  }

  public List<Home> findAll() {
    return homeRepository.findAll();
  }

  public Home findOne(Long id) {
    return homeRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException(String.format(ErrorMessage.HOME_NOT_FOUND_BY_ID, id)));
  }

  public Home update(Home request) {
    Home home = findOne(request.getId());
    home.setCountry(request.getCountry());
    home.setCity(request.getCity());
    home.setAddressa(request.getAddressa());
    return homeRepository.save(home);

  }

  public void delete(Long id) {
    if (!homeRepository.findById(id).isPresent()) {
      throw new NotDeletedException(String.format(ErrorMessage.HOME_NOT_DELETED_BY_ID, id));
    } else if (!locationService.findByHome(id).isEmpty()) {
      throw new RuntimeException(String.format(ErrorMessage.HOME_NOT_DELETED_HAVE_DEPENDENCIES, id));
    }
    homeRepository.deleteById(id);
  }

}
