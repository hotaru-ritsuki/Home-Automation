package com.softserve.lv460.application.tool.bot;

import com.softserve.lv460.application.service.TelegramUserService;
import com.softserve.lv460.application.tool.bot.chain.*;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@NoArgsConstructor
@Slf4j
public class HomeAlertBot extends TelegramLongPollingBot {
  private TelegramUserService telegramUserService;

  HomeAlertBot(TelegramUserService telegramUserService) {
    this.telegramUserService = telegramUserService;

  }

  @Override
  public void onUpdateReceived(Update update) {
    UpdateCheck check = initCheck();
    if (update.hasMessage() && update.getMessage().hasText()) {
      SendMessage message = new SendMessage().setChatId(update.getMessage().getChatId());
      check.process(update, telegramUserService, message);
      sendMessageToUser(message);
    }
  }

  @Override
  public String getBotUsername() {
    return "HomemadeAlertBot";
  }

  @Override
  public String getBotToken() {
    return "1063385637:AAF2u88hfmblJwCVvr-5Zto8Uc86IjAA3VI";
  }

  private void sendMessageToUser(SendMessage message) {
    try {
      execute(message);
    } catch (TelegramApiException e) {
      log.debug(message.getChatId(), e);
    }
  }

  private UpdateCheck initCheck() {
    UpdateCheck start = new StartCommand();
    UpdateCheck stop = new StopCommand();
    UpdateCheck unknown = new NotCommand();
    UpdateCheck help = new HelpCommand();
    start.setNext(stop);
    stop.setNext(help);
    help.setNext(unknown);
    return start;
  }
}
