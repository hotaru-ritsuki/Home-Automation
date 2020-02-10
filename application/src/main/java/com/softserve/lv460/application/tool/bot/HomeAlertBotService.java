package com.softserve.lv460.application.tool.bot;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.service.TelegramUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

@Service
@Slf4j
public class HomeAlertBotService {
  public HomeAlertBotService(TelegramUserService telegramUserService) {
    ApiContextInitializer.init();
    TelegramBotsApi botsApi = new TelegramBotsApi();
    try {
      botsApi.registerBot(new HomeAlertBot(telegramUserService));
    } catch (TelegramApiException e) {
      log.debug(ErrorMessage.SOMETHING_WENT_WRONG_WITH_BOT, e);
    }
  }

  public void execute(Map<String, String> actionData) {
    try {
      CloseableHttpResponse response = httpClient.execute(new HttpGet(deviceRuleUrl));
      String chatId = new BasicResponseHandler().handleResponse(response);
      URL url = new URL(String.format(propertiesConfig.getTelegramURL(), chatId, actionData.get("text")));
      URLConnection conn = url.openConnection();
      conn.getInputStream();
    } catch (IOException e) {
      log.debug(ExceptionMassages.NOT_SEND_NOTIFICATION_BY_TELEGRAM, e);
    }
  }
}
