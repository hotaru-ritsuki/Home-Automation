package com.softserve.lv460.application.tool.bot;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.service.TelegramActivationService;
import com.softserve.lv460.application.service.TelegramUserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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
      String telegramURL = "https://api.telegram.org/bot1063385637:AAF2u88hfmblJwCVvr-5Zto8Uc86IjAA3VI/sendMessage?chat_id=%s&text=%s";
      URL url = new URL(String.format(telegramURL, chatId, text));
      URLConnection conn = url.openConnection();
      conn.getInputStream();
    } catch (IOException e) {
      log.info(e.toString());
    }
  }


}
