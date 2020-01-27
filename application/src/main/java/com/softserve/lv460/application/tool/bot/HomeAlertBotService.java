package com.softserve.lv460.application.tool.bot;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.service.TelegramUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
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
}
