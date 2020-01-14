package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.constant.HttpStatuses;
import com.softserve.lv460.application.dto.action.ActionDTO;
import com.softserve.lv460.application.mapper.ActionMapper;
import com.softserve.lv460.application.service.ActionService;
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
@RequestMapping("/actions")
@CrossOrigin
public class ActionController {

  private ActionService actionService;
  private ActionMapper mapper;

  @ApiOperation(value = "Create new action")
  @ApiResponses(value = {
        @ApiResponse(code = 201, message = HttpStatuses.CREATED, response = ActionDTO.class),
        @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
        @ApiResponse(code = 404, message = HttpStatuses.NOT_FOUND)
  })
  @PostMapping
  public ResponseEntity<ActionDTO> create(@RequestBody ActionDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(actionService.create(mapper.toEntity(dto))));
  }

  @ApiOperation(value = "Return list of action")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK, response = ActionDTO.class),
        @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
        @ApiResponse(code = 404, message = HttpStatuses.NOT_FOUND)
  })
  @GetMapping
  public ResponseEntity<List<ActionDTO>> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body(actionService.findAll().stream().map(mapper::toDto).collect(Collectors.toList()));
  }

  @ApiOperation(value = "Update action")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.CREATED, response = ActionDTO.class),
        @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
        @ApiResponse(code = 404, message = HttpStatuses.NOT_FOUND)
  })
  @PutMapping
  public ResponseEntity<ActionDTO> update(@RequestBody ActionDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(actionService.update(mapper.toEntity(dto))));
  }

  @ApiOperation(value = "Delete action")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK, response = Long.class),
        @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
        @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
  })
  @DeleteMapping("/{action_id}")
  public ResponseEntity<Long> delete(@PathVariable("action_id") Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(actionService.delete(id));
  }

}
