package com.ritsuki.application.web.rest;

import com.ritsuki.application.constant.HttpStatuses;
import com.ritsuki.application.dto.localDevice.LocalDeviceRequestDTO;
import com.ritsuki.application.dto.localDevice.LocalDeviceRespDTO;
import com.ritsuki.application.dto.localDevice.LocalDeviceResponseDTO;
import com.ritsuki.application.service.HomeService;
import com.ritsuki.application.service.LocalDeviceService;
import com.ritsuki.application.service.LocationService;
import com.ritsuki.application.mapper.localDevice.LocalDeviceRequestMapper;
import com.ritsuki.application.mapper.localDevice.LocalDeviceRespMapper;
import com.ritsuki.application.mapper.localDevice.LocalDeviceResponseMapper;
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
@RequestMapping("/location-devices")
@AllArgsConstructor
@CrossOrigin
public class LocalDeviceController {
    private final LocalDeviceService localDeviceService;
    private final LocationService locationService;
    private final HomeService homeService;
    private final LocalDeviceResponseMapper responseMapper;
    private final LocalDeviceRespMapper respMapper;
    private final LocalDeviceRequestMapper requestMapper;

    @Operation(summary = "Create new device")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = HttpStatuses.CREATED, content = @Content(schema = @Schema(implementation = LocalDeviceResponseDTO.class)))
    })
    @PostMapping
    public ResponseEntity<LocalDeviceResponseDTO> save(@RequestBody LocalDeviceRequestDTO localDeviceRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseMapper.toDto(localDeviceService.save(requestMapper.toEntity(localDeviceRequestDTO))));
    }

    @Operation(summary = "Return list of device")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = LocalDeviceResponseDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<LocalDeviceResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(localDeviceService.findAll().stream().map(responseMapper::toDto).collect(Collectors.toList()));
    }

    @Operation(summary = "Return list of device in location")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = LocalDeviceResponseDTO.class)))
    })
    @GetMapping("/location/{location_id}")
    public ResponseEntity<List<LocalDeviceResponseDTO>> findByLocation(@PathVariable("location_id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(localDeviceService.findAllByLocation(locationService.findOne(id)).
                stream().map(responseMapper::toDto).collect(Collectors.toList()));
    }

    @Operation(summary = "Return list of device in home")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = LocalDeviceResponseDTO.class)))
    })
    @GetMapping("/home/{home_id}")
    public ResponseEntity<List<LocalDeviceResponseDTO>> findByHome(@PathVariable("home_id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(localDeviceService.findAllByHome(homeService.findOne(id)).
                stream().map(responseMapper::toDto).collect(Collectors.toList()));
    }

    @Operation(summary = "Return device by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = LocalDeviceResponseDTO.class)))
    })

    @GetMapping("/{uuid}")
    public ResponseEntity<LocalDeviceRespDTO> findOne(@PathVariable("uuid") String uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(respMapper.toDto(localDeviceService.findByUuid(uuid)));
    }

    @Operation(summary = "Update device")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = LocalDeviceResponseDTO.class)))
    })
    @PutMapping
    public ResponseEntity<LocalDeviceResponseDTO> update(@RequestBody LocalDeviceRequestDTO localDevice) {
        return ResponseEntity.status(HttpStatus.OK).body(
                responseMapper.toDto(localDeviceService.update(requestMapper.toEntity(localDevice))));
    }

    @Operation(summary = "Delete device")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = HttpStatuses.NO_CONTENT),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST)
    })
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable("uuid") String uuid) {
        localDeviceService.delete(uuid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
