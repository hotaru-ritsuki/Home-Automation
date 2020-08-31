package com.ritsuki.application.service;

import com.ritsuki.application.entity.ApplicationUser;
import com.ritsuki.application.entity.VerificationToken;
import com.ritsuki.application.exception.exceptions.UserAlreadyActivated;

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
