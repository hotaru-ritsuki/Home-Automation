package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.constant.HttpStatuses;
import com.softserve.lv460.application.dto.home.HomeRequestDTO;
import com.softserve.lv460.application.dto.home.HomeResponseDTO;
import com.softserve.lv460.application.mapper.home.HomeRequestMapper;
import com.softserve.lv460.application.mapper.home.HomeResponseMapper;
import com.softserve.lv460.application.mapper.user.TelegramResponseMapper;
import com.softserve.lv460.application.security.annotation.CurrentUser;
import com.softserve.lv460.application.security.dto.TelegramUserResponse;
import com.softserve.lv460.application.security.entity.UserPrincipal;
import com.softserve.lv460.application.service.HomeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/homes")
@CrossOrigin
@AllArgsConstructor
public class HomeController {

  private HomeService homeService;
  private HomeRequestMapper requestMapper;
  private HomeResponseMapper responseMapper;
  private TelegramResponseMapper telegramResponseMapper;

  @ApiOperation(value = "Create new home")
  @ApiResponses(value = {
          @ApiResponse(code = 201, message = HttpStatuses.CREATED, response = HomeResponseDTO.class)
  })
  @PostMapping
  public ResponseEntity<HomeResponseDTO> create(@RequestBody HomeRequestDTO request, @CurrentUser UserPrincipal user) {
    return ResponseEntity.status(HttpStatus.CREATED).body(responseMapper
            .toDto(homeService.create(requestMapper.toEntity(request), user.getId())));
  }

  @ApiOperation(value = "Update home")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK, response = HomeResponseDTO.class)
  })
  @PutMapping
  public ResponseEntity<HomeResponseDTO> update(@RequestBody HomeRequestDTO request) {
    return ResponseEntity.status(HttpStatus.OK)
            .body(responseMapper.toDto(homeService.update(requestMapper.toEntity(request))));
  }

  @ApiOperation(value = "Delete home")
  @ApiResponses(value = {
          @ApiResponse(code = 204, message = HttpStatuses.NO_CONTENT),
          @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
  })
  @DeleteMapping("/{home_id}")
  public ResponseEntity<Void> delete(@PathVariable("home_id") Long id) {
    homeService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @ApiOperation(value = "Return home by id")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK, response = HomeResponseDTO.class)
  })
  @GetMapping("/{home_id}")
  public ResponseEntity<HomeResponseDTO> findOne(@PathVariable("home_id") Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(responseMapper.toDto(homeService.findOne(id)));
  }

  @ApiOperation(value = "Return list of home by user")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK, response = HomeResponseDTO.class)
  })
  @GetMapping
  public ResponseEntity<List<HomeResponseDTO>> findAllByUser(@CurrentUser UserPrincipal user) {
    return ResponseEntity.status(HttpStatus.OK).body(homeService.findAllByUser(user.getId()).stream().map(responseMapper::toDto)
            .collect(Collectors.toList()));
  }

  @ApiOperation(value = "Return list of users by home with telegrams")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK, response = TelegramUserResponse.class)
  })
  @GetMapping(value = "/users/{home_id}")
  public ResponseEntity<List<TelegramUserResponse>> findAllUsersByHomeWithTelegram(@PathVariable("home_id") Long homeId) {
    return ResponseEntity.status(HttpStatus.OK).body(homeService.findAllUsersByHomeIdWithTelegram(homeId).stream().map(telegramResponseMapper::toDto).collect(Collectors.toList()));
  }

}
