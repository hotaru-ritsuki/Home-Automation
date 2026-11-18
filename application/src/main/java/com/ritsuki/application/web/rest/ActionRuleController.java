package com.ritsuki.application.web.rest;

import com.ritsuki.application.constant.HttpStatuses;
import com.ritsuki.application.dto.actionRule.ActionRuleRequestDTO;
import com.ritsuki.application.dto.actionRule.ActionRuleResponseDTO;
import com.ritsuki.application.service.ActionRuleService;
import com.ritsuki.application.entity.id.ActionRuleId;
import com.ritsuki.application.mapper.actionRule.ActionRuleRequestMapper;
import com.ritsuki.application.mapper.actionRule.ActionRuleResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

  @Operation(summary = "Create new action rule")
  @ApiResponses({
        @ApiResponse(responseCode = "201", description = HttpStatuses.CREATED, content = @Content(schema = @Schema(implementation = ActionRuleResponseDTO.class)))
  })
  @PostMapping
  public ResponseEntity<ActionRuleResponseDTO> create(@RequestBody ActionRuleRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(responseMapper.toDto(actionRuleService
          .create(requestMapper.toEntity(dto))));
  }

  @Operation(summary = "Update action rule")
  @ApiResponses({
        @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = ActionRuleResponseDTO.class)))
  })
  @PutMapping
  public ResponseEntity<ActionRuleResponseDTO> update(@RequestBody ActionRuleRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.OK).body(responseMapper.toDto(actionRuleService
          .update(requestMapper.toEntity(dto))));
  }

  @Operation(summary = "Delete action rule")
  @ApiResponses({
        @ApiResponse(responseCode = "204", description = HttpStatuses.NO_CONTENT),
        @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST)
  })
  @DeleteMapping
  public ResponseEntity<Void> delete(@RequestBody ActionRuleId id) {
    actionRuleService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
