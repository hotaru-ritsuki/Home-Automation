package com.softserve.lv460.application.constant;

public class BotPhrases {
  public static final String UNKNOWN_COMMAND = "I don't understand: %s";
  public static final String START_PHRASE = "Hello! Welcome to Home Alert"
        + "\nUse /help for instruction";
  public static final String STOP_PHRASE = "Bye!";
  public static final String HELP_PHRASE = "Our bot sends you notifications from Home Automation app." +
        "\nvisit our site www. to detail information"
        + "\nBot command:"
        + "\n/help - instruction"
        + "\n/stop - stop using bot";
  public static final String ALREADY_REGISTER = "Hello again. But why?";
  public static final String ALREADY_DELETE = "Have good luck.";
  public static final String CONFIRM = "Please confirm your account by sending activation code with command /join";
  public static final String MESSAGE_EXAMPLE = "In this format '/join **12345**'";
  public static final String SUCCESSFUL_AUTHENTICATION="You are successfully registered";
}
