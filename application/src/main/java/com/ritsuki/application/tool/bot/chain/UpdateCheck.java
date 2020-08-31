package com.ritsuki.application.tool.bot.chain;

import com.ritsuki.application.service.TelegramActivationService;
import com.ritsuki.application.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateCheck {
  void setNext(UpdateCheck nextInChain);

  void process(Update request, TelegramUserService telegramUserService, TelegramActivationService telegramActivationService, SendMessage message);
}
