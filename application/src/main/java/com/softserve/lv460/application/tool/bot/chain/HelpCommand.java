package com.softserve.lv460.application.tool.bot.chain;

import com.softserve.lv460.application.constant.BotPhrases;
import com.softserve.lv460.application.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class HelpCommand implements UpdateCheck {
  private UpdateCheck nextInChain;

  @Override
  public void setNext(UpdateCheck nextInChain) {
    this.nextInChain = nextInChain;
  }

  @Override
  public void process(Update request, TelegramUserService telegramUserService, SendMessage message) {
    if (request.getMessage().getText().equals("/help")) {
      message.setText(BotPhrases.HELP_PHRASE);
    } else {
      nextInChain.process(request, telegramUserService, message);
    }
  }
}
