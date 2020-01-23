package com.softserve.lv460.application.constant;

public class UrlForBot {
  public static String URL_CONTROLLER_POST = "http://localhost:8080/telegramBot?chatId=%s&username=%s";
  public static String URL_CONTROLLER_DELETE = "http://localhost:8080/telegramBot/%s";
  public static String URL_CONTROLLER_GET_BY_USERNAME = "http://localhost:8080/telegramBot/user/id/%s";
}
