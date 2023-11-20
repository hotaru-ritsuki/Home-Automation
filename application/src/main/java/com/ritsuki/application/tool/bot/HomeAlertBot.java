package com.ritsuki.application.tool.bot;

import com.ritsuki.application.service.TelegramActivationService;
import com.ritsuki.application.service.TelegramUserService;
import com.ritsuki.application.tool.bot.chain.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@AllArgsConstructor
@Slf4j
public class HomeAlertBot extends TelegramLongPollingBot {

  private final TelegramUserService telegramUserService;
  private final TelegramActivationService telegramActivationService;


  @Override
  public void onUpdateReceived(Update update) {
    UpdateCheck check = initCheck();
    if (update.hasMessage() && update.getMessage().hasText()) {
      SendMessage message = new SendMessage().setChatId(update.getMessage().getChatId());
      check.process(update, telegramUserService, telegramActivationService, message);
      sendMessageToUser(message);
    }
  }

  @Override
  public String getBotUsername() {
    // TODO Made it in properties
    return "HomemadeAlertBot";
  }

  @Override
  public String getBotToken() {
    // TODO Made it in properties
    return "";
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
    UpdateCheck join = new JoinCommand();
    start.setNext(stop);
    stop.setNext(help);
    help.setNext(join);
    join.setNext(unknown);
    return start;
  }
}
