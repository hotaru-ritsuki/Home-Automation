package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.constant.HttpStatuses;
import com.softserve.lv460.application.dto.telegramUser.TelegramUserDTO;
import com.softserve.lv460.application.entity.TelegramUser;
import com.softserve.lv460.application.mapper.telegramUser.TelegramUserMapper;
import com.softserve.lv460.application.service.TelegramUserService;
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
@RequestMapping("/telegramBot")
@CrossOrigin
public class TelegramUserController {
  private TelegramUserService telegramUserService;
  private TelegramUserMapper mapper;

  @ApiOperation(value = "Add user's telegram")
  @ApiResponses(value = {
        @ApiResponse(code = 201, message = HttpStatuses.CREATED)
  })
  @PostMapping
  public ResponseEntity<Void> create(@RequestParam String username, @RequestParam String chatId) {
    TelegramUser telegramUser = new TelegramUser();
    telegramUser.setUsername(username);
    telegramUser.setChatId(chatId);
    telegramUserService.create(telegramUser);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

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
  public ResponseEntity<TelegramUserDTO> findAllByUsername(@PathVariable("username") String username) {
    return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(telegramUserService.findByUsername(username)));
  }

  @ApiOperation(value = "Return user's telegram by username")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK, response = Long.class)
  })
  @GetMapping("/user/id/{username}")
  public ResponseEntity<Long> findAIdByUsername(@PathVariable("username") String username) {
    return ResponseEntity.status(HttpStatus.OK).body(telegramUserService.findByUsername(username).getId());
  }


}
