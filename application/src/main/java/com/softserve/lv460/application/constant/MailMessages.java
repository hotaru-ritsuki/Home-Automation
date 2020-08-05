package com.softserve.lv460.application.constant;

public class MailMessages {
  //Mischiefs
  public static final String CONGRATS = "Dear %s,\n";
  public static final String SIGN = """
          Best regards,
          Brain House""";

  //Password
  public static final String PASWORD_CHANGED_SUBJECT = "Home-Automation - Your Password Changed";
  public static final String PASSWORD_CHANGED_BODY = """
          you received this, because your password has been changed.
          If it wasn't you can restore your password on the login page""";

  //Account Activation
  public static final String VERIFY_EMAIL_SUBJECT = "Home-Automation — Registration Confirmation";
  public static final String RESENDING_ACTIVATION_TOKEN_SUBJECT = "Home-Automation — Resending Activation";
  public static final String VERIFY_EMAIL_BODY = """
            Please verify your email address to complete your BrainHouse Account.
          To verify your account just follow the link: %s
          This link will expire in 24 hours.  
          If it has expired, try to request a new verification email by the link: %s""";

  //Restore
  public static final String RESTORE_EMAIL_BODY = """
          Please verify your email address to change your password.
          To verify your account just follow the link: %s
          This link will expire in 24 hours.
          If it has expired, try to request a new verification email by the link: %s""";

  //Home Invitation
  public static final String INVITATION_HOME_SUBJECT = "Home-Automation — Home Invitation";
  public static final String INVITATION_HOME_CONGRATS = "Hi,\n";
  public static final String INVITATION_HOME_BODY = """
          You received this because user %s sends you invitation to his home with name %s
          by the address %s, %s, %s
          to accept invitation, please follow the link bellow: %s """;
}
