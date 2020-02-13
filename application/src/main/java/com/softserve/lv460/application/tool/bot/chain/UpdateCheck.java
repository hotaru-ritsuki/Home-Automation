package com.softserve.lv460.application.tool.bot.chain;

import com.softserve.lv460.application.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateCheck {
  void setNext(UpdateCheck nextInChain);

  void process(Update request, TelegramUserService telegramUserService, SendMessage message);
}
