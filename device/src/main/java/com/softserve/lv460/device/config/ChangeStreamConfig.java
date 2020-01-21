package com.softserve.lv460.device.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.softserve.lv460.device.controller.DeviceDataController;
import com.softserve.lv460.device.dto.rule.RuleDto;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ChangeStreamConfig {
  private final MongoClient mongoClient;
  private final CloseableHttpClient httpClient;
  private final PropertiesConfig propertiesConfig;
  private LoadingCache<String, List<RuleDto>> cache;
  private static Logger logger = LoggerFactory.getLogger(DeviceDataController.class);

  public ChangeStreamConfig(MongoClient mongoClient, CloseableHttpClient httpClient, PropertiesConfig propertiesConfig) {
    this.httpClient = httpClient;
    this.propertiesConfig = propertiesConfig;
    this.mongoClient = mongoClient;
    this.cache = CacheBuilder
            .newBuilder()
            .expireAfterWrite(propertiesConfig.getCacheExpiration(), TimeUnit.MINUTES)
            .build(
                    new CacheLoader<String, List<RuleDto>>() {
                      @Override
                      public List<RuleDto> load(String uuId) throws IOException {
                        return getRules(uuId);
                      }
                    }
            );
  }

  @PostConstruct
  public void subscribeToChangeStream() {
    Thread thread = new Thread(() -> {
      MongoDatabase db = mongoClient.getDatabase(propertiesConfig.getDatabase());
      MongoCollection<Document> collection = db.getCollection(propertiesConfig.getCollection());
      Block<ChangeStreamDocument<Document>> printBlock = changeStreamDocument -> {
        Document fullDocument = changeStreamDocument.getFullDocument();
        List<RuleDto> rules = getCache(fullDocument.getString("uuId"));
        for (RuleDto rule : rules) {
          if (checkRuleCondition(rule, (Document) fullDocument.get("data"))) {

          }
        }
      };
      collection.watch().forEach(printBlock);
    });
    thread.start();
  }

  private List<RuleDto> getCache(String uuId) {
    try {
      return cache.get(uuId);
    } catch (Exception e) {
      logger.error(e.getLocalizedMessage());
      return Collections.emptyList();
    }
  }

  private List<RuleDto> getRules(String uuId) throws IOException {
    String deviceRuleUrl = propertiesConfig.getMainApplicationHostName() + "/rules/device/" + uuId;
    CloseableHttpResponse response = httpClient.execute(new HttpGet(deviceRuleUrl));
    return new ObjectMapper().readValue(response.getEntity().getContent(),
            new TypeReference<List<RuleDto>>() {
            });
  }

  private Boolean checkRuleCondition(RuleDto rule, Document data) {
    try {
      JSONObject conditionJson = new JSONObject(rule.getConditions());
      String operator = conditionJson.getString("operator");
      Integer dataValue = Integer.valueOf(data.getString(conditionJson.getString("field_name")));
      Integer conditionValue = conditionJson.getInt("value");
      switch (operator) {
        case ">":
          return dataValue > conditionValue;
        case "<":
          return dataValue < conditionValue;
        case ">=":
          return dataValue >= conditionValue;
        case "<=":
          return dataValue <= conditionValue;
        default:
          return false;
      }
    } catch (JSONException e) {
      logger.error(e.getLocalizedMessage());
      return false;
    }
  }

}

