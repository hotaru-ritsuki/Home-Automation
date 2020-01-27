package com.softserve.lv460.device.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.lv460.device.action.Action;
import com.softserve.lv460.device.action.ActionRegistry;
import com.softserve.lv460.device.config.cache.RuleCacheConfig;
import com.softserve.lv460.device.document.DeviceData;
import com.softserve.lv460.device.dto.rule.ActionRuleDto;
import com.softserve.lv460.device.dto.rule.RuleDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ChangeStreamEvent;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.BiPredicate;

@Service
@AllArgsConstructor
@Slf4j
public class ChangeStreamConfig {
  private final PropertiesConfig propertiesConfig;
  private final ActionRegistry actionRegistry;
  private final RuleCacheConfig ruleCacheConfig;
  private final ReactiveMongoOperations reactiveTemplate;

  @PostConstruct
  public void subscribeToChangeStream() {
    Flux<ChangeStreamEvent<DeviceData>> deviceData = reactiveTemplate.changeStream(DeviceData.class)
            .watchCollection(propertiesConfig.getCollection()).listen();

    deviceData.doOnNext((event) -> {
      List<RuleDto> rules = ruleCacheConfig.getCache(event.getBody().getUuId());
      rules.stream().filter((rule) ->
              checkRuleCondition(rule, event.getBody().getData())).forEach(this::executeActions);
    }).subscribe();
  }

  private void executeActions(RuleDto rule) {
    for (ActionRuleDto actionRuleDto : rule.getActionRule()) {
      List<Action> actions = actionRegistry.getAction(actionRuleDto.getAction().getType());
      for (Action action : actions) {
        action.execute(parseToMap(actionRuleDto.getActionSpecification()));
      }
    }
  }

  private Boolean checkRuleCondition(RuleDto rule, Map<String, String> data) {
    try {
      JSONObject conditionJson = new JSONObject(rule.getConditions());
      String operator = conditionJson.getString("operator");
      String dataValue = data.get(conditionJson.getString("field_name"));
      String conditionValue = conditionJson.getString("value");
      return predicateMap().get(operator).test(dataValue, conditionValue);
    } catch (JSONException e) {
      log.error(e.getLocalizedMessage());
      return false;
    }
  }

  @Bean
  Map<String, BiPredicate<String, String>> predicateMap() {
    Map<String, BiPredicate<String, String>> map = new HashMap<>();
    map.put(">", (dataValue, condValue) -> Double.parseDouble(dataValue) > Double.parseDouble(condValue));
    map.put("<", (dataValue, condValue) -> Double.parseDouble(dataValue) < Double.parseDouble(condValue));
    map.put(">=", (dataValue, condValue) -> Double.parseDouble(dataValue) >= Double.parseDouble(condValue));
    map.put("<=", (dataValue, condValue) -> Double.parseDouble(dataValue) <= Double.parseDouble(condValue));
    map.put("=", String::equals);
    map.put("IN", (dataValue, condValue) -> Arrays.asList(condValue.substring(1, condValue.length() - 1)
            .split(",")).contains(dataValue));
    return map;
  }


  private Map<String, String> parseToMap(String toParse) {
    try {
      return new ObjectMapper().readValue(toParse, new TypeReference<Map<String, String>>() {
      });
    } catch (JsonProcessingException e) {
      log.error(e.getLocalizedMessage());
      return Collections.emptyMap();
    }
  }

}

