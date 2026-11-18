package com.ritsuki.application.web.rest;

import com.ritsuki.application.constant.HttpStatuses;
import com.ritsuki.application.dto.action.ActionDTO;
import com.ritsuki.application.service.ActionService;
import com.ritsuki.application.mapper.action.ActionMapper;
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
@RequestMapping("/actions")
@CrossOrigin
public class ActionController {

  private final ActionService actionService;
  private final ActionMapper mapper;

  @Operation(summary = "Create new action")
  @ApiResponses({
        @ApiResponse(responseCode = "201", description = HttpStatuses.CREATED, content = @Content(schema = @Schema(implementation = ActionDTO.class))),
        @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST)
  })
  @PostMapping
  public ResponseEntity<ActionDTO> createAction(@RequestBody ActionDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(actionService.create(mapper.toEntity(dto))));
  }

  @Operation(summary = "Return list of action")
  @ApiResponses({
        @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = ActionDTO.class)))
  })
  @GetMapping
  public ResponseEntity<List<ActionDTO>> getAllActions() {
    return ResponseEntity.status(HttpStatus.OK).body(actionService.findAll().stream().map(mapper::toDto)
          .collect(Collectors.toList()));
  }

  @Operation(summary = "Update action")
  @ApiResponses({
        @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = ActionDTO.class)))
  })
  @PutMapping
  public ResponseEntity<ActionDTO> updateAction(@RequestBody ActionDTO dto) {
    return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(actionService.update(mapper.toEntity(dto))));
  }

  @Operation(summary = "Delete action")
  @ApiResponses({
        @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = Long.class))),
        @ApiResponse(responseCode = "204", description = HttpStatuses.NO_CONTENT),
        @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST)
  })
  @DeleteMapping("/{action_id}")
  public ResponseEntity<Void> deleteAction(@PathVariable("action_id") Long id) {
    actionService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Operation(summary = "Get action by id")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = Long.class))),
          @ApiResponse(responseCode = "204", description = HttpStatuses.NO_CONTENT),
          @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST)
  })
  @GetMapping("/{action_id}")
  public ResponseEntity<ActionDTO> getAction(@PathVariable("action_id") Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(actionService.findAction(id)));
  }

}
