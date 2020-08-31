package com.ritsuki.application.service.impl;

import com.ritsuki.application.constant.ErrorMessage;
import com.ritsuki.application.entity.ApplicationUser;
import com.ritsuki.application.entity.Home;
import com.ritsuki.application.exception.exceptions.HomeAlreadyRegisterException;
import com.ritsuki.application.exception.exceptions.NotDeletedException;
import com.ritsuki.application.exception.exceptions.NotFoundException;
import com.ritsuki.application.repository.HomeRepository;
import com.ritsuki.application.service.ApplicationUserService;
import com.ritsuki.application.service.HomeService;
import com.ritsuki.application.service.LocationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class HomeServiceImpl implements HomeService {

    private final HomeRepository homeRepository;
    private final LocationService locationService;
    private final ApplicationUserService userService;

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
        Optional<Home> isHome = homeRepository.findByAddressaLike(request.getAddressa());
        if (isHome.isPresent()) {
            throw new HomeAlreadyRegisterException(String.format(ErrorMessage.HOME_ALREADY_REGISTER, request.getAddressa()));
        }
        Home home = findOne(request.getId());
        home.setCountry(request.getCountry());
        home.setCity(request.getCity());
        home.setAddressa(request.getAddressa());
        home.setName(request.getName());
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
        return home.getApplicationUsers().stream().filter(user -> Objects.nonNull(user.getTelegramUser())).filter(user -> user.getTelegramUser().isEnabled()).collect(Collectors.toList());
    }

}
