package com.ritsuki.device.config;

import com.ritsuki.device.action.Action;
import com.ritsuki.device.action.ActionRegistry;
import com.ritsuki.device.config.cache.RuleCacheConfig;
import com.ritsuki.device.document.DeviceData;
import com.ritsuki.device.dto.rule.ActionRuleDto;
import com.ritsuki.device.dto.rule.RuleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ChangeStreamEvent;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiPredicate;

import static com.ritsuki.device.util.Util.parseToMap;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ChangeStreamConfig {

    private final PropertiesConfig propertiesConfig;
    private final ActionRegistry actionRegistry;
    private final RuleCacheConfig ruleCacheConfig;
    private final ReactiveMongoOperations reactiveTemplate;
    private Map<String, BiPredicate<String, String>> predicateMap;

    @PostConstruct
    private void initPredicateMap() {
      predicateMap = Map.of(
              ">",  (String data, String cond) -> Double.parseDouble(data) > Double.parseDouble(cond),
              "<",  (String data, String cond) -> Double.parseDouble(data) < Double.parseDouble(cond),
              ">=", (String data, String cond) -> Double.parseDouble(data) >= Double.parseDouble(cond),
              "<=", (String data, String cond) -> Double.parseDouble(data) <= Double.parseDouble(cond),
              "IN", (String data, String cond) -> Arrays.asList(cond.substring(1, cond.length() - 1).split(",")).contains(data),
              "=",  String::equalsIgnoreCase
      );

    }

    @PostConstruct
    public void subscribeToChangeStream() {
        Flux<ChangeStreamEvent<DeviceData>> deviceData = reactiveTemplate.changeStream(DeviceData.class)
                .watchCollection(propertiesConfig.getCollection()).listen();

        deviceData.doOnNext((event) -> {
            List<RuleDto> rules = ruleCacheConfig.getCache(event.getBody().getUuId());
            rules.stream().filter((rule) -> rule.getActive() &&
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
            JSONArray conditionArray = new JSONArray(rule.getConditions());
            for (int i = 0; i < conditionArray.length(); i++) {
                JSONObject condition = conditionArray.getJSONObject(i);
                Optional<String> dataValue = Optional.ofNullable(data.get(condition.getString("field_name")));
                if (dataValue.isPresent()) {
                    String operator = condition.getString("operator");
                    String conditionValue = condition.getString("value");
                    if (predicateMap.get(operator).test(dataValue.get(), conditionValue)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (JSONException e) {
            log.error(e.getLocalizedMessage());
            return false;
        }
    }


}

