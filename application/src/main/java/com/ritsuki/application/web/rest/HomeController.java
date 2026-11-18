package com.ritsuki.application.web.rest;

import com.ritsuki.application.constant.HttpStatuses;
import com.ritsuki.application.constant.LinkConfigProperties;
import com.ritsuki.application.security.annotation.CurrentUser;
import com.ritsuki.application.security.entity.UserPrincipal;
import com.ritsuki.application.service.HomeService;
import com.ritsuki.application.service.HomeTokenService;
import com.ritsuki.application.dto.home.HomeRequestDTO;
import com.ritsuki.application.dto.home.HomeResponseDTO;
import com.ritsuki.application.dto.user.TelegramUserDTO;
import com.ritsuki.application.dto.user.UsernameDTO;
import com.ritsuki.application.mapper.home.HomeRequestMapper;
import com.ritsuki.application.mapper.home.HomeResponseMapper;
import com.ritsuki.application.mapper.user.TelegramResponseMapper;
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


@RestController
@RequestMapping("/homes")
@CrossOrigin
@AllArgsConstructor
public class HomeController {

  private final HomeService homeService;
  private final HomeTokenService homeTokenService;
  private final HomeRequestMapper requestMapper;
  private final HomeResponseMapper responseMapper;
  private final TelegramResponseMapper telegramResponseMapper;
  private final LinkConfigProperties linkConfigProperties;

  @Operation(summary = "Create new home")
  @ApiResponses({
          @ApiResponse(responseCode = "201", description = HttpStatuses.CREATED, content = @Content(schema = @Schema(implementation = HomeResponseDTO.class)))
  })
  @PostMapping
  public ResponseEntity<HomeResponseDTO> create(@RequestBody HomeRequestDTO request, @CurrentUser UserPrincipal user) {
    return ResponseEntity.status(HttpStatus.CREATED).body(responseMapper
            .toDto(homeService.create(requestMapper.toEntity(request), user.getId())));
  }

  @Operation(summary = "Update home")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = HomeResponseDTO.class)))
  })
  @PutMapping
  public ResponseEntity<HomeResponseDTO> update(@RequestBody HomeRequestDTO request) {
    return ResponseEntity.status(HttpStatus.OK)
            .body(responseMapper.toDto(homeService.update(requestMapper.toEntity(request))));
  }

  @Operation(summary = "Delete home")
  @ApiResponses({
          @ApiResponse(responseCode = "204", description = HttpStatuses.NO_CONTENT),
          @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST)
  })
  @DeleteMapping("/{home_id}")
  public ResponseEntity<Void> delete(@PathVariable("home_id") Long id) {
    homeService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Operation(summary = "Return home by id")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = HomeResponseDTO.class)))
  })
  @GetMapping("/{home_id}")
  public ResponseEntity<HomeResponseDTO> findOne(@PathVariable("home_id") Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(responseMapper.toDto(homeService.findOne(id)));
  }

  @Operation(summary = "Return list of home by user")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = HomeResponseDTO.class)))
  })
  @GetMapping
  public ResponseEntity<List<HomeResponseDTO>> findAllByUser(@CurrentUser UserPrincipal user) {
    return ResponseEntity.status(HttpStatus.OK).body(homeService.findAllByUser(user.getId())
            .stream()
            .map(responseMapper::toDto)
            .collect(Collectors.toList()));
  }

  @Operation(summary = "Return list of users by home with telegrams")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = TelegramUserDTO.class)))
  })
  @GetMapping(value = "/users/{home_id}")
  public ResponseEntity<List<TelegramUserDTO>> findAllUsersByHomeWithTelegram(@PathVariable("home_id") Long homeId) {
    return ResponseEntity.status(HttpStatus.OK).body(homeService.findAllUsersByHomeIdWithTelegram(homeId)
            .stream()
            .map(telegramResponseMapper::toDto)
            .collect(Collectors.toList()));
  }

  @Operation(summary = "Send a link to add house to user")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = UsernameDTO.class))),
          @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST)
  })
  @PostMapping(value = "/sendInvitation/{home_id}")
  public ResponseEntity<?> sendInvitationLink(@PathVariable("home_id") Long homeId, @RequestBody UsernameDTO user,@CurrentUser UserPrincipal userPrincipal) {
    homeTokenService.sendInvitationLink(homeService.findOne(homeId),user,userPrincipal.getUsername(),getViewUrl());
return ResponseEntity.ok().build();
  }

  @Operation(summary = "Add a house by link")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = UsernameDTO.class))),
          @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST)
  })
  @PostMapping(value = "/acceptInvitation/{token}")
  public ResponseEntity<?> acceptInvitationLink(@PathVariable("token") String token,@CurrentUser UserPrincipal userPrincipal){
    homeTokenService.validateHomeToken(userPrincipal.getUsername(),token);
    return ResponseEntity.ok().build();
  }

  public String getViewUrl() {
    return linkConfigProperties.getViewUrl() + "homes/";
  }
}
