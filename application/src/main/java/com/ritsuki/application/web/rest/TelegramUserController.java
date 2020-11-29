package com.ritsuki.application.web.rest;

import com.ritsuki.application.constant.HttpStatuses;
import com.ritsuki.application.dto.telegramUser.TelegramUserDTO;
import com.ritsuki.application.service.TelegramUserService;
import com.ritsuki.application.mapper.telegramUser.TelegramUserMapper;
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
@RequestMapping("/telegram-bot")
@CrossOrigin
public class TelegramUserController {
  private final TelegramUserService telegramUserService;
  private final TelegramUserMapper mapper;

  @ApiOperation(value = "Return list of user's telegram")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK, response = TelegramUserDTO.class)
  })
  @GetMapping
  public ResponseEntity<List<TelegramUserDTO>> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body(telegramUserService.findAll().stream()
          .map(mapper::toDto).collect(Collectors.toList()));
  }

  @ApiOperation(value = "Delete user's telegram")
  @ApiResponses(value = {
        @ApiResponse(code = 204, message = HttpStatuses.NO_CONTENT),
        @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    telegramUserService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @ApiOperation(value = "Return user's telegram by username")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK, response = TelegramUserDTO.class)
  })
  @GetMapping("/user/{username}")
  public ResponseEntity<TelegramUserDTO> findByUsername(@PathVariable("username") String username) {
    return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(telegramUserService.findByUsername(username)));
  }

  @ApiOperation(value = "Return user's telegram chat id by username")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK, response = String.class)
  })
  @GetMapping("/user/id/{username}")
  public ResponseEntity<String> findChatIdByUsername(@PathVariable("username") String username) {
    return ResponseEntity.status(HttpStatus.OK).body(telegramUserService.findByUsername(username).getChatId());
  }
}