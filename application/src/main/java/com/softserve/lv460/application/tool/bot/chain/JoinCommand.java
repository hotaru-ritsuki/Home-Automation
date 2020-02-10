package com.softserve.lv460.application.tool.bot.chain;

import com.softserve.lv460.application.entity.TelegramUser;
import com.softserve.lv460.application.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class JoinCommand implements UpdateCheck {
  private UpdateCheck nextInChain;

  @Override
  public void setNext(UpdateCheck nextInChain) {
    this.nextInChain = nextInChain;
  }

  @Override
  public void process(Update request, TelegramUserService telegramUserService, SendMessage message) {
    if (request.getMessage().getText().equals("/join")) {
      TelegramUser telegramUser = new TelegramUser();
      telegramUser.setUsername(request.getMessage().getChat().getUserName());
      telegramUser.setChatId(String.valueOf(request.getMessage().getChatId()));
      nextInChain.process(request, telegramUserService, message);
    }
  }
}
