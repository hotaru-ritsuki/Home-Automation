package com.softserve.lv460.application.tool.bot.chain;

import com.softserve.lv460.application.constant.BotPhrases;
import com.softserve.lv460.application.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NotCommand implements UpdateCheck {
  @Override
  public void setNext(UpdateCheck nextInChain) {
  }

  @Override
  public void process(Update request, TelegramUserService telegramUserService, SendMessage message) {
    message.setText(String.format(BotPhrases.UNKNOWN_COMMAND, request.getMessage().getText()));
  }
}
