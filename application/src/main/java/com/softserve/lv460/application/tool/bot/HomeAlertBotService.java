package com.softserve.lv460.application.tool.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class HomeAlertBotService {
  public HomeAlertBotService() {
    ApiContextInitializer.init();
    TelegramBotsApi botsApi = new TelegramBotsApi();
    try {
      botsApi.registerBot(new HomeAlertBot());
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }
}
