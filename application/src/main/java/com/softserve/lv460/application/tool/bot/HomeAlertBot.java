package com.softserve.lv460.application.tool.bot;

import com.softserve.lv460.application.constant.UrlForBot;
import lombok.NoArgsConstructor;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

@NoArgsConstructor
public class HomeAlertBot extends TelegramLongPollingBot {
  private final OkHttpClient httpClient = new OkHttpClient();

  @Override
  public void onUpdateReceived(Update update) {
    if (update.hasMessage() && update.getMessage().hasText()) {
      SendMessage message = new SendMessage()
            .setChatId(update.getMessage().getChatId())
            .setText("I don't understand you " + update.getMessage().getText());
      if (update.getMessage().getText().equals("/start")) {
        String urlString = String.format(UrlForBot.URL_CONTROLLER_POST,
              update.getMessage().getChatId().toString(), update.getMessage().getChat().getUserName());
        FormBody formBody = new FormBody.Builder().build();
        Request request = new Request.Builder().url(urlString).post(formBody).build();
        try (Response response = httpClient.newCall(request).execute()) {
          if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
          message.setText("Victory");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (update.getMessage().getText().equals("/stop")) {
        Request requests = new Request.Builder()
              .url(String.format(UrlForBot.URL_CONTROLLER_GET_BY_USERNAME,
                    update.getMessage().getChat().getUserName())).build();
        try (Response responses = httpClient.newCall(requests).execute()) {
          if (!responses.isSuccessful()) throw new IOException("Unexpected code " + responses);
          String urlString = String.format(UrlForBot.URL_CONTROLLER_DELETE, responses.body().string());
          Request request = new Request.Builder().url(urlString).delete().build();
          try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            message.setText("Bye");
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      try {
        execute(message);
      } catch (TelegramApiException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public String getBotUsername() {
    return null;
  }

  @Override
  public String getBotToken() {
    return null;
  }
}
