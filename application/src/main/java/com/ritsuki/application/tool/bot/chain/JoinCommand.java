package com.ritsuki.application.tool.bot.chain;

import com.ritsuki.application.constant.BotPhrases;
import com.ritsuki.application.service.TelegramActivationService;
import com.ritsuki.application.service.TelegramUserService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
public class JoinCommand implements UpdateCheck {
  private UpdateCheck nextInChain;

  @Override
  public void setNext(UpdateCheck nextInChain) {
    this.nextInChain = nextInChain;
  }

  @Override
  public void process(Update request, TelegramUserService telegramUserService, TelegramActivationService telegramActivationService, SendMessage message) {
    if (request.getMessage().getText().startsWith("/join") && telegramActivationService.existsByTelegramUsername(request.getMessage().getChat().getUserName())) {
      if(telegramActivationService.validate(request.getMessage().getChat().getUserName(),
                                         request.getMessage().getText().split("[ ]")[1])){
        message.setText(BotPhrases.SUCCESSFUL_AUTHENTICATION);
      }
      else{
        message.setText(BotPhrases.NOT_VALID);
      }
    } else {
      nextInChain.process(request, telegramUserService, telegramActivationService, message);
    }
  }
}
