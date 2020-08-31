package com.ritsuki.application.tool.bot.chain;

import com.ritsuki.application.constant.BotPhrases;
import com.ritsuki.application.service.TelegramActivationService;
import com.ritsuki.application.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NotCommand implements UpdateCheck {
  @Override
  public void setNext(UpdateCheck nextInChain) {
  }

  @Override
  public void process(Update request, TelegramUserService telegramUserService, TelegramActivationService telegramActivationService, SendMessage message) {
    message.setText(String.format(BotPhrases.UNKNOWN_COMMAND, request.getMessage().getText()));
  }
}
