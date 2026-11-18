package com.ritsuki.application.web.rest;

import com.ritsuki.application.constant.HttpStatuses;
import com.ritsuki.application.service.RuleService;
import com.ritsuki.application.dto.rule.RuleRequestDTO;
import com.ritsuki.application.dto.rule.RuleResponseDTO;
import com.ritsuki.application.mapper.rule.RuleRequestMapper;
import com.ritsuki.application.mapper.rule.RuleResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/rules")
@CrossOrigin
public class RuleController {
  private final RuleService ruleService;
  private final RuleRequestMapper requestMapper;
  private final RuleResponseMapper responseMapper;

  @Operation(summary = "Create new rule")
  @ApiResponses({
          @ApiResponse(responseCode = "201", description = HttpStatuses.CREATED, content = @Content(schema = @Schema(implementation = RuleResponseDTO.class)))
  })
  @PostMapping
  public ResponseEntity<RuleResponseDTO> create(@RequestBody RuleRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(responseMapper.toDto(ruleService
            .create(requestMapper.toEntity(dto))));
  }

  @Operation(summary = "Return list of rule")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = RuleResponseDTO.class)))
  })
  @GetMapping
  public ResponseEntity<List<RuleResponseDTO>> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body(ruleService.findAll().stream().map(responseMapper::toDto)
            .collect(Collectors.toList()));
  }

  @Operation(summary = "Update rule")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = RuleResponseDTO.class)))
  })
  @PutMapping
  public ResponseEntity<RuleResponseDTO> update(@RequestBody RuleRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.OK).body(responseMapper.toDto(ruleService
            .update(requestMapper.toEntity(dto))));
  }

  @Operation(summary = "Delete rule")
  @ApiResponses({
          @ApiResponse(responseCode = "204", description = HttpStatuses.NO_CONTENT),
          @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
  })
  @DeleteMapping("/{rule_id}")
  public ResponseEntity<Void> delete(@PathVariable("rule_id") Long id) {
    ruleService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Operation(summary = "Return list of rule by local device")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = RuleResponseDTO.class)))
  })
  @GetMapping("/device/{local_device_id}")
  public ResponseEntity<List<RuleResponseDTO>> findAllByUuid(@PathVariable("local_device_id") String uuid) {
    return ResponseEntity.status(HttpStatus.OK).body(ruleService.findAllByLocalDevice(uuid).stream()
            .map(responseMapper::toDto).collect(Collectors.toList()));
  }

  @Operation(summary = "Return list of rule by home")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = RuleResponseDTO.class)))
  })
  @GetMapping("/home/{home_id}")
  public ResponseEntity<List<RuleResponseDTO>> findAllByHome(@PathVariable("home_id") Long home) {
    return ResponseEntity.status(HttpStatus.OK).body(ruleService.findByHome(home).stream()
            .map(responseMapper::toDto).collect(Collectors.toList()));
  }
}
