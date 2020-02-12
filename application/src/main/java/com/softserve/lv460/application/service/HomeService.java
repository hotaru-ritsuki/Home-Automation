package com.softserve.lv460.application.service;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.entity.Home;
import com.softserve.lv460.application.exception.exceptions.HomeAlreadyRegisterException;
import com.softserve.lv460.application.exception.exceptions.NotDeletedException;
import com.softserve.lv460.application.exception.exceptions.NotFoundException;
import com.softserve.lv460.application.repository.HomeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class HomeService {

  private HomeRepository homeRepository;
  private LocationService locationService;
  private ApplicationUserService userService;

  public Home create(Home request, Long user) {
    Optional<Home> isHome = homeRepository.findByAddressaLike(request.getAddressa());
    if (isHome.isPresent()) {
      throw new HomeAlreadyRegisterException(String.format(ErrorMessage.HOME_ALREADY_REGISTER, request.getAddressa()));
    }
    List<ApplicationUser> users = new ArrayList<>();
    users.add(userService.findById(user));
    request.setApplicationUsers(users);
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
    if (homeRepository.findById(id).isEmpty()) {
      throw new NotDeletedException(String.format(ErrorMessage.HOME_NOT_DELETED_BY_ID, id));
    } else if (!locationService.findByHome(id).isEmpty()) {
      throw new RuntimeException(String.format(ErrorMessage.HOME_NOT_DELETED_HAVE_DEPENDENCIES, id));
    }
    homeRepository.deleteById(id);
  }

  public List<Home> findAllByUser(Long userId) {
    return homeRepository.findAllByApplicationUsers(userService.findById(userId));
  }

  public List<ApplicationUser> findAllUsersByHomeIdWithTelegram(Long homeId) {
    Home home = homeRepository.findById(homeId).orElseThrow(() -> new NotFoundException(String.format(ErrorMessage.HOME_NOT_FOUND_BY_ID, homeId)));
    return home.getApplicationUsers().stream().filter(user -> user.getTelegramUser() != null && user.getTelegramUser().isEnabled()).collect(Collectors.toList());
  }
}
