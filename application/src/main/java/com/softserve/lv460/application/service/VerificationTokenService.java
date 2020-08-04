package com.softserve.lv460.application.service;

import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.entity.VerificationToken;
import com.softserve.lv460.application.exception.exceptions.UserAlreadyActivated;

public interface VerificationTokenService {

    void confirmRegistration(ApplicationUser applicationUser, String appUrl);

    void resendToken(ApplicationUser user, String token, String appUrl);

    void restorePassword(ApplicationUser user, String appUrl);

    VerificationToken findByUserIdAndToken(Long id, String token);

    void delete(Long id);

    VerificationToken createVerificationTokenForUser(ApplicationUser user, String token);

    void validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String email) throws UserAlreadyActivated;

}
