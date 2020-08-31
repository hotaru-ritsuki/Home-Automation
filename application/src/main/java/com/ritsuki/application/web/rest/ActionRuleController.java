package com.ritsuki.application.web.rest;

import com.ritsuki.application.constant.HttpStatuses;
import com.ritsuki.application.dto.actionRule.ActionRuleRequestDTO;
import com.ritsuki.application.dto.actionRule.ActionRuleResponseDTO;
import com.ritsuki.application.service.ActionRuleService;
import com.ritsuki.application.entity.id.ActionRuleId;
import com.ritsuki.application.mapper.actionRule.ActionRuleRequestMapper;
import com.ritsuki.application.mapper.actionRule.ActionRuleResponseMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/actionsRules")
@CrossOrigin
@RestController
public class ActionRuleController {

  private final ActionRuleService actionRuleService;
  private final ActionRuleResponseMapper responseMapper;
  private final ActionRuleRequestMapper requestMapper;

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
  @PutMapping
  public ResponseEntity<ActionRuleResponseDTO> update(@RequestBody ActionRuleRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.OK).body(responseMapper.toDto(actionRuleService
          .update(requestMapper.toEntity(dto))));
  }

  @ApiOperation(value = "Delete action rule")
  @ApiResponses(value = {
        @ApiResponse(code = 204, message = HttpStatuses.NO_CONTENT),
        @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
  })
  @DeleteMapping
  public ResponseEntity<Void> delete(@RequestBody ActionRuleId id) {
    actionRuleService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
