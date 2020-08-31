package com.ritsuki.device.action;

import com.ritsuki.device.constant.ExceptionMassages;
import com.ritsuki.device.config.PropertiesConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

@Data
@AllArgsConstructor
@Slf4j
public class TelegramAction implements Action {
  private final PropertiesConfig propertiesConfig;
  private final CloseableHttpClient httpClient;

  @Override
  public void execute(Map<String, String> actionData) {
    try {
      String deviceRuleUrl = propertiesConfig.getMainApplicationHostName() + "/telegram-bot/user/id/" + actionData.get("username");
      CloseableHttpResponse response = httpClient.execute(new HttpGet(deviceRuleUrl));
      String chatId = new BasicResponseHandler().handleResponse(response);
      URL url = new URL(String.format(propertiesConfig.getTelegramURL(), chatId, actionData.get("text")));
      URLConnection conn = url.openConnection();
      conn.getInputStream();
    } catch (IOException e) {
      log.debug(ExceptionMassages.NOT_SEND_NOTIFICATION_BY_TELEGRAM, e);
    }
  }

  @Override
  public String getType() {
    return "TELEGRAM";
  }
}
