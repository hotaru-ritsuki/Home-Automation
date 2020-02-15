package com.softserve.lv460.application.tool.bot.chain;

import com.softserve.lv460.application.constant.BotPhrases;
import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.entity.TelegramUser;
import com.softserve.lv460.application.service.TelegramActivationService;
import com.softserve.lv460.application.service.TelegramUserService;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
public class StopCommand implements UpdateCheck {
  private UpdateCheck nextInChain;

  @Override
  public void setNext(UpdateCheck nextInChain) {
    this.nextInChain = nextInChain;
  }

  @Override
  public void process(Update request, TelegramUserService telegramUserService, TelegramActivationService telegramActivationService, SendMessage message) {
    if (request.getMessage().getText().equals("/stop")) {
      try {
        TelegramUser byUsername = telegramUserService.findByUsername(request.getMessage().getChat().getUserName());
        telegramUserService.delete(byUsername.getId());
        message.setText(BotPhrases.STOP_PHRASE);
      } catch (IllegalArgumentException e) {
        log.debug(String.format(ErrorMessage.TELEGRAM_NOT_DELETED_BY_USERNAME, request.getMessage().getChat().getUserName()), e);
        message.setText(BotPhrases.ALREADY_DELETE);
      }
    } else {
      nextInChain.process(request, telegramUserService, telegramActivationService, message);
    }
  }
}
