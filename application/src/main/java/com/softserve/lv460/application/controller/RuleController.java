package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.constant.HttpStatuses;
import com.softserve.lv460.application.dto.rule.RuleRequestDTO;
import com.softserve.lv460.application.dto.rule.RuleResponseDTO;
import com.softserve.lv460.application.mapper.rule.RuleRequestMapper;
import com.softserve.lv460.application.mapper.rule.RuleResponseMapper;
import com.softserve.lv460.application.service.RuleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
  private RuleService ruleService;
  private RuleRequestMapper requestMapper;
  private RuleResponseMapper responseMapper;

  @ApiOperation(value = "Create new rule")
  @ApiResponses(value = {
        @ApiResponse(code = 201, message = HttpStatuses.CREATED, response = RuleResponseDTO.class),
        @ApiResponse(code = 401, message = HttpStatuses.UNAUTHORIZED),
        @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
  })
  @PostMapping
  public ResponseEntity<RuleResponseDTO> create(@RequestBody RuleRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(responseMapper.toDto(ruleService.create(requestMapper.toEntity(dto))));
  }

  @ApiOperation(value = "Return list of rule")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK, response = RuleResponseDTO.class),
        @ApiResponse(code = 401, message = HttpStatuses.UNAUTHORIZED),
        @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
  })
  @GetMapping
  public ResponseEntity<List<RuleResponseDTO>> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body(ruleService.findAll().stream().map(responseMapper::toDto).collect(Collectors.toList()));
  }

  @ApiOperation(value = "Update rule")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK, response = RuleResponseDTO.class),
        @ApiResponse(code = 401, message = HttpStatuses.UNAUTHORIZED),
        @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
        @ApiResponse(code = 404, message = HttpStatuses.NOT_FOUND)
  })
  @PutMapping
  public ResponseEntity<RuleResponseDTO> update(@RequestBody RuleRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.OK).body(responseMapper.toDto(ruleService.update(requestMapper.toEntity(dto))));
  }

  @ApiOperation(value = "Delete rule")
  @ApiResponses(value = {
        @ApiResponse(code = 204, message = HttpStatuses.NO_CONTENT),
        @ApiResponse(code = 401, message = HttpStatuses.UNAUTHORIZED),
        @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
        @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
        @ApiResponse(code = 404, message = HttpStatuses.NOT_FOUND)
  })
  @DeleteMapping("/{rule_id}")
  public ResponseEntity<Void> delete(@PathVariable("rule_id") Long id) {
    ruleService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "Return list of rule by local device")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK, response = RuleResponseDTO.class),
        @ApiResponse(code = 401, message = HttpStatuses.UNAUTHORIZED),
        @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
  })
  @GetMapping("/device/{local_device_id}")
  public ResponseEntity<List<RuleResponseDTO>> findAllByUuid(@PathVariable("local_device_id") String uuid) {
    return ResponseEntity.status(HttpStatus.OK).body(ruleService.findAllByLocalDevice(uuid).stream().map(responseMapper::toDto).collect(Collectors.toList()));
  }
}
