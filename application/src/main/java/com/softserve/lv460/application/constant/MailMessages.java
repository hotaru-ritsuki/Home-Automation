package com.softserve.lv460.application.constant;

public class MailMessages {
  //Mischiefs
  public static final String CONGRATS = "Dear %s,\n";
  public static final String SIGN = "\nBest regards,\nBrain House";

  //Password
  public static final String PASWORD_CHANGED_SUBJECT = "Home-Automation - Your Password Changed";
  public static final String PASSWORD_CHANGED_BODY = "\nyou received this, because your password has been changed." +
          "\nIf it wasn't you can restore your password on the login page\n";

  //Account Activation
  public static final String VERIFY_EMAIL_SUBJECT = "Home-Automation — Registration Confirmation";
  public static final String RESENDING_ACTIVATION_TOKEN_SUBJECT = "Home-Automation — Resending Activation";
  public static final String VERIFY_EMAIL_BODY = "\nPlease verify your email address to complete your BrainHouse Account." +
          "\n To verify your account just follow the link: %s\n" +
          "This link will expire in 24 hours. " +
          "\nIf it has expired, try to request a new verification email by the link:\n %s";

  //Restore
  public static final String RESTORE_EMAIL_BODY = "\nPlease verify your email address to change your password." +
          "\n To verify your account just follow the link: %s\n" +
          "This link will expire in 24 hours. " ;
}
