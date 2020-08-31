package com.ritsuki.application.tool.bot.chain;

import com.ritsuki.application.constant.BotPhrases;
import com.ritsuki.application.service.TelegramActivationService;
import com.ritsuki.application.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class HelpCommand implements UpdateCheck {
  private UpdateCheck nextInChain;

  @Override
  public void setNext(UpdateCheck nextInChain) {
    this.nextInChain = nextInChain;
  }

  @Override
  public void process(Update request, TelegramUserService telegramUserService, TelegramActivationService telegramActivationService, SendMessage message) {
    if (request.getMessage().getText().equals("/help")) {
      message.setText(BotPhrases.HELP_PHRASE);
    } else {
      nextInChain.process(request, telegramUserService, telegramActivationService, message);
    }
  }
}
