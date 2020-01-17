package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.constant.HttpStatuses;
import com.softserve.lv460.application.dto.home.HomeRequestDTO;
import com.softserve.lv460.application.dto.home.HomeResponseDTO;
import com.softserve.lv460.application.mapper.home.HomeRequestMapper;
import com.softserve.lv460.application.mapper.home.HomeResponseMapper;
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

  @ApiOperation(value = "Create new home")
  @ApiResponses(value = {
        @ApiResponse(code = 201, message = HttpStatuses.CREATED, response = HomeResponseDTO.class),
        @ApiResponse(code = 401, message = HttpStatuses.UNAUTHORIZED),
        @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
  })
  @PostMapping
  public ResponseEntity<HomeResponseDTO> create(@RequestBody HomeRequestDTO request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(responseMapper.toDto(homeService.create(requestMapper.toEntity(request))));
  }

  @ApiOperation(value = "Return list of home")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK, response = HomeResponseDTO.class),
        @ApiResponse(code = 401, message = HttpStatuses.UNAUTHORIZED),
        @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
  })
  @GetMapping
  public ResponseEntity<List<HomeResponseDTO>> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body(homeService.findAll().stream().map(responseMapper::toDto).collect(Collectors.toList()));
  }

  @ApiOperation(value = "Update home")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK, response = HomeResponseDTO.class),
        @ApiResponse(code = 401, message = HttpStatuses.UNAUTHORIZED),
        @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
        @ApiResponse(code = 404, message = HttpStatuses.NOT_FOUND)
  })
  @PutMapping
  public ResponseEntity<HomeResponseDTO> update(@RequestBody HomeRequestDTO request) {
    return ResponseEntity.status(HttpStatus.OK).body(responseMapper.toDto(homeService.update(requestMapper.toEntity(request))));
  }

  @ApiOperation(value = "Delete home")
  @ApiResponses(value = {
        @ApiResponse(code = 204, message = HttpStatuses.NO_CONTENT),
        @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
        @ApiResponse(code = 401, message = HttpStatuses.UNAUTHORIZED),
        @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
        @ApiResponse(code = 404, message = HttpStatuses.NOT_FOUND)
  })
  @DeleteMapping("/{home_id}")
  public ResponseEntity<Void> delete(@PathVariable("home_id") Long id) {
    homeService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @ApiOperation(value = "Return home by id")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK, response = HomeResponseDTO.class),
        @ApiResponse(code = 401, message = HttpStatuses.UNAUTHORIZED),
        @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
        @ApiResponse(code = 404, message = HttpStatuses.NOT_FOUND)
  })
  @GetMapping("/{home_id}")
  public ResponseEntity<HomeResponseDTO> findOne(@PathVariable("home_id") Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(responseMapper.toDto(homeService.findOne(id)));
  }
}
