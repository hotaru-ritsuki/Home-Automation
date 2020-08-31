package com.ritsuki.application.service;

import com.ritsuki.application.entity.ApplicationUser;
import com.ritsuki.application.entity.Home;

import java.util.List;

public interface HomeService {

    Home create(Home request, Long user);

    List<Home> findAll();

    Home findOne(Long id);

    Home update(Home request);

    void delete(Long id);

    List<Home> findAllByUser(Long userId);

    List<ApplicationUser> findAllUsersByHomeIdWithTelegram(Long homeId);

}
