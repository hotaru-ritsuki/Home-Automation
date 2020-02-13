package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.constant.HttpStatuses;
import com.softserve.lv460.application.dto.actionRule.ActionRuleRequestDTO;
import com.softserve.lv460.application.dto.actionRule.ActionRuleResponseDTO;
import com.softserve.lv460.application.entity.id.ActionRuleId;
import com.softserve.lv460.application.mapper.actionRule.ActionRuleRequestMapper;
import com.softserve.lv460.application.mapper.actionRule.ActionRuleResponseMapper;
import com.softserve.lv460.application.service.ActionRuleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/actionsRules")
@CrossOrigin
public class ActionRuleController {
  private ActionRuleService actionRuleService;
  private ActionRuleResponseMapper responseMapper;
  private ActionRuleRequestMapper requestMapper;

  @ApiOperation(value = "Create new action rule")
  @ApiResponses(value = {
        @ApiResponse(code = 201, message = HttpStatuses.CREATED, response = ActionRuleResponseDTO.class)
  })
  @PostMapping
  public ResponseEntity<ActionRuleResponseDTO> create(@RequestBody ActionRuleRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(responseMapper.toDto(actionRuleService
          .create(requestMapper.toEntity(dto))));
  }

  @ApiOperation(value = "Update action rule")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK, response = ActionRuleResponseDTO.class)
  })
  @PostMapping("/update")
  public ResponseEntity<ActionRuleResponseDTO> update(@RequestBody ActionRuleRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.OK).body(responseMapper.toDto(actionRuleService
          .update(requestMapper.toEntity(dto))));
  }

  @ApiOperation(value = "Delete action rule")
  @ApiResponses(value = {
        @ApiResponse(code = 204, message = HttpStatuses.NO_CONTENT),
        @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
  })
  @PostMapping("/delete")
  public ResponseEntity<Void> delete(@RequestBody ActionRuleId id) {
    actionRuleService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
