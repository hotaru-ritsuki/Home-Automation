package com.ritsuki.application.tool.bot;

import com.ritsuki.application.service.TelegramActivationService;
import com.ritsuki.application.service.TelegramUserService;
import com.ritsuki.application.tool.bot.chain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@RequiredArgsConstructor
@Slf4j
public class HomeAlertBot implements LongPollingSingleThreadUpdateConsumer {

  // TODO Made it in properties
  static final String BOT_USERNAME = "HomemadeAlertBot";
  // TODO Made it in properties
  static final String BOT_TOKEN = "";

  private final TelegramClient telegramClient;

  private final TelegramUserService telegramUserService;
  private final TelegramActivationService telegramActivationService;


  @Override
  public void consume(Update update) {
    UpdateCheck check = initCheck();
    if (update.hasMessage() && update.getMessage().hasText()) {
      SendMessage message = new SendMessage(update.getMessage().getChatId().toString(), "");
      check.process(update, telegramUserService, telegramActivationService, message);
      sendMessageToUser(message);
    }
  }

  private void sendMessageToUser(SendMessage message) {
    try {
      telegramClient.execute(message);
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
