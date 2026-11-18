package com.ritsuki.application.tool.bot;

import com.ritsuki.application.constant.ErrorMessage;
import com.ritsuki.application.service.TelegramActivationService;
import com.ritsuki.application.service.TelegramUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.net.URI;
import java.net.URLConnection;

@Service
@Slf4j
public class HomeAlertBotService {

  public HomeAlertBotService(TelegramUserService telegramUserService, TelegramActivationService telegramActivationService) {
    String token = HomeAlertBot.BOT_TOKEN;
    if (token.isBlank()) {
      log.warn("Telegram bot token is not configured; HomeAlertBot is not started.");
      return;
    }
    try {
      TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication();
      botsApplication.registerBot(token,
              new HomeAlertBot(new OkHttpTelegramClient(token), telegramUserService, telegramActivationService));
    } catch (TelegramApiException e) {
      log.debug(ErrorMessage.SOMETHING_WENT_WRONG_WITH_BOT, e);
    }
  }

  public void execute(String chatId, String text) {
    try {
      String telegramURL = "";
      URLConnection conn = URI.create(String.format(telegramURL, chatId, text)).toURL().openConnection();
      conn.getInputStream();
    } catch (IOException | IllegalArgumentException e) {
      log.debug(ErrorMessage.SOMETHING_WENT_WRONG_WITH_BOT, e);
    }
  }
}
