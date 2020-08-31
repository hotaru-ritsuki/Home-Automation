package com.ritsuki.application.tool.bot;

import com.ritsuki.application.constant.ErrorMessage;
import com.ritsuki.application.service.TelegramActivationService;
import com.ritsuki.application.service.TelegramUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

@Service
@Slf4j
public class HomeAlertBotService {
  private CloseableHttpClient httpClient;

  public HomeAlertBotService(TelegramUserService telegramUserService, TelegramActivationService telegramActivationService, CloseableHttpClient httpClient) {
    ApiContextInitializer.init();
    this.httpClient = httpClient;
    TelegramBotsApi botsApi = new TelegramBotsApi();
    try {
      botsApi.registerBot(new HomeAlertBot(telegramUserService,telegramActivationService));
    } catch (TelegramApiException e) {
      log.debug(ErrorMessage.SOMETHING_WENT_WRONG_WITH_BOT, e);
    }
  }

  public void execute(String chatId, String text) {
    try {
      String telegramURL = "";
      URL url = new URL(String.format(telegramURL, chatId, text));
      URLConnection conn = url.openConnection();
      conn.getInputStream();
    } catch (IOException e) {
      log.debug(ErrorMessage.SOMETHING_WENT_WRONG_WITH_BOT,e);
    }
  }


}
