package com.ritsuki.application.tool.bot.chain;

import com.ritsuki.application.constant.BotPhrases;
import com.ritsuki.application.constant.ErrorMessage;
import com.ritsuki.application.exception.exceptions.TelegramUserAlreadyRegisterException;
import com.ritsuki.application.service.TelegramActivationService;
import com.ritsuki.application.service.TelegramUserService;
import com.ritsuki.application.entity.TelegramUser;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
public class StartCommand implements UpdateCheck {
  private UpdateCheck nextInChain;

  @Override
  public void setNext(UpdateCheck nextInChain) {
    this.nextInChain = nextInChain;
  }

  @Override
  public void process(Update request, TelegramUserService telegramUserService, TelegramActivationService telegramActivationService, SendMessage message) {
    if (request.getMessage().getText().equals("/start")) {
      TelegramUser telegramUser = new TelegramUser();
      telegramUser.setUsername(request.getMessage().getChat().getUserName());
      telegramUser.setChatId(String.valueOf(request.getMessage().getChatId()));
      try {
        telegramUserService.create(telegramUser);
        message.setText(BotPhrases.START_PHRASE);
      } catch (TelegramUserAlreadyRegisterException e) {
        log.debug(String.format(ErrorMessage.TELEGRAM_ALREADY_REGISTER, request.getMessage().getChat().getUserName()), e);
        message.setText(BotPhrases.ALREADY_REGISTER);
      }
    } else {
      nextInChain.process(request, telegramUserService, telegramActivationService, message);
    }
  }
}
