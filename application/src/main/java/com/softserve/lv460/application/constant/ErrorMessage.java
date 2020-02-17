package com.softserve.lv460.application.constant;

public class ErrorMessage {
  public static final String FEATURE_NOT_DELETED_BY_ID = "The feature wasn't removed by id: ";
  public static final String DEVICE_TEMPLATE_NOT_DELETED_BY_ID = "The device template wasn't removed by id: %d";
  public static final String DEVICE_TEMPLATE_NOT_UPDATED_BY_ID = "The device template wasn't updated by id: %d";
  public static final String DEVICE_TEMPLATE_NOT_FOUND_BY_ID = "The device template wasn't found by id: %d";
  public static final String DEVICE_FEATURE_NOT_FOUND_BY_ID = "The device-feature wasn't found by device id: %d and " +
        "feature id: %d";
  public static final String DEVICE_FEATURE_NOT_DELETED_BY_ID = "The device-feature wasn't removed by device id: %d " +
        "and feature id: %d";
  public static final String DEVICE_FEATURE_ALREADY_EXISTS = "Device feature with device id: %d and feature id: %d " +
        "already exists";
  public static final String LOCAL_DEVICE_NOT_FOUND = "The device is not found by id: %s";
  public static final String DEVICE_TEMPLATE_NOT_FOUND = "The device template is not found by id: %d";
  public static final String ACTION_NOT_DELETED_BY_ID = "The action wasn't deleted by id: %d";
  public static final String ACTION_NOT_FOUND_BY_ID = "Action with id %d does not exist";
  public static final String ACTION_RULE_NOT_DELETED_BY_ID = "The action rule wasn't deleted by action id: %d and rule id %d";
  public static final String ACTION_RULE_NOT_FOUND_BY_ID = "Action rule with action id %d and rule id %d does not exist";
  public static final String RULE_NOT_DELETED_BY_ID = "The rule wasn't deleted by id: %d";
  public static final String RULE_NOT_FOUND_BY_ID = "Rule with id %d does not exist";
  public static final String LOCATION_NOT_FOUND_BY_ID = "Location with id %d does not exist";
  public static final String LOCATION_NOT_DELETED_BY_ID = "The rule wasn't deleted by id: %d";
  public static final String HOME_ALREADY_REGISTER = "Home with address %s is already registered";
  public static final String HOME_NOT_FOUND_BY_ID = "Home with id %d does not exist";
  public static final String HOME_NOT_DELETED_BY_ID = "The rule wasn't deleted by id: %d";
  public static final String HOME_NOT_DELETED_HAVE_DEPENDENCIES = "Home with id %d has dependencies";
  public static final String ACTION_RULE_ALREADY_EXISTS = "Action rule with action id %d and rule id %d already exists";
  public static final String TELEGRAM_NOT_DELETED_BY_ID = "The user's telegram wasn't deleted by id: %d";
  public static final String TELEGRAM_NOT_DELETED_BY_USERNAME = "The user's telegram wasn't deleted by username: %s";
  public static final String TELEGRAM_NOT_FOUND_BY_USERNAME = "Telegram user with username %s does not exist";
  public static final String TELEGRAM_ALREADY_REGISTER = "User's telegram with username %s is already registered";
  public static final String SOMETHING_WENT_WRONG_WITH_BOT = "Error with bot";
  public static final String USER_ALREADY_ACTIVATED = "User already activated";
  public static final String USER_ALREADY_EXISTS = "User with specified email %s already exists";
  public static final String USER_NOT_FOUND_BY_EMAIL = "User with email %s not found";
  public static final String USER_NOT_FOUND_BY_ID = "User with id %d not found";
  public static final String REFRESH_TOKEN_NOT_VALID = "Refresh token is not valid";
  public static final String VERIFICATION_TOKEN_IS_EXPIRED = "Verification Token is expired";
  public static final String VERIFICATION_TOKEN_IS_NOT_VALID = "Verification token is not valid";
  public static final String TELEGRAM_CODE_NOT_DELETED_BY_ID = "Telegram's activation code wasn't deleted by id: %d";
  public static final String TELEGRAM_CODE_NOT_FOUND_BY_USERNAME = "Telegram's code for username %s does not exist";
  public static final String INCORRECT_DATA = "Bad email or password";
}
