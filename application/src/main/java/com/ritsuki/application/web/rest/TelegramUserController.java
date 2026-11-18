package com.ritsuki.application.web.rest;

import com.ritsuki.application.constant.HttpStatuses;
import com.ritsuki.application.dto.telegramUser.TelegramUserDTO;
import com.ritsuki.application.service.TelegramUserService;
import com.ritsuki.application.mapper.telegramUser.TelegramUserMapper;
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
@RequestMapping("/telegram-bot")
@CrossOrigin
public class TelegramUserController {
  private final TelegramUserService telegramUserService;
  private final TelegramUserMapper mapper;

  @Operation(summary = "Return list of user's telegram")
  @ApiResponses({
        @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = TelegramUserDTO.class)))
  })
  @GetMapping
  public ResponseEntity<List<TelegramUserDTO>> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body(telegramUserService.findAll().stream()
          .map(mapper::toDto).collect(Collectors.toList()));
  }

  @Operation(summary = "Delete user's telegram")
  @ApiResponses({
        @ApiResponse(responseCode = "204", description = HttpStatuses.NO_CONTENT),
        @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    telegramUserService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Operation(summary = "Return user's telegram by username")
  @ApiResponses({
        @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = TelegramUserDTO.class)))
  })
  @GetMapping("/user/{username}")
  public ResponseEntity<TelegramUserDTO> findByUsername(@PathVariable("username") String username) {
    return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(telegramUserService.findByUsername(username)));
  }

  @Operation(summary = "Return user's telegram chat id by username")
  @ApiResponses({
        @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = String.class)))
  })
  @GetMapping("/user/id/{username}")
  public ResponseEntity<String> findChatIdByUsername(@PathVariable("username") String username) {
    return ResponseEntity.status(HttpStatus.OK).body(telegramUserService.findByUsername(username).getChatId());
  }
}