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
@RequestMapping("/location-devices")
@AllArgsConstructor
@CrossOrigin
public class LocalDeviceController {
    private LocalDeviceService localDeviceService;
    private LocationService locationService;
    private HomeService homeService;
    private LocalDeviceResponseMapper responseMapper;
    private LocalDeviceRespMapper respMapper;
    private LocalDeviceRequestMapper requestMapper;

    @ApiOperation(value = "Create new device")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = HttpStatuses.CREATED, response = LocalDeviceResponseDTO.class)
    })
    @PostMapping
    public ResponseEntity<LocalDeviceResponseDTO> save(@RequestBody LocalDeviceRequestDTO localDeviceRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseMapper.toDto(localDeviceService.save(requestMapper.toEntity(localDeviceRequestDTO))));
    }

    @ApiOperation(value = "Return list of device")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK, response = LocalDeviceResponseDTO.class)
    })
    @GetMapping
    public ResponseEntity<List<LocalDeviceResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(localDeviceService.findAll().stream().map(responseMapper::toDto).collect(Collectors.toList()));
    }

    @ApiOperation(value = "Return list of device in location")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK, response = LocalDeviceResponseDTO.class)
    })
    @GetMapping("/location/{location_id}")
    public ResponseEntity<List<LocalDeviceResponseDTO>> findByLocation(@PathVariable("location_id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(localDeviceService.findAllByLocation(locationService.findOne(id)).
                stream().map(responseMapper::toDto).collect(Collectors.toList()));
    }

    @ApiOperation(value = "Return list of device in home")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK, response = LocalDeviceResponseDTO.class)
    })
    @GetMapping("/home/{home_id}")
    public ResponseEntity<List<LocalDeviceResponseDTO>> findByHome(@PathVariable("home_id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(localDeviceService.findAllByHome(homeService.findOne(id)).
                stream().map(responseMapper::toDto).collect(Collectors.toList()));
    }

    @ApiOperation(value = "Return device by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK, response = LocalDeviceResponseDTO.class)
    })

    @GetMapping("/{uuid}")
    public ResponseEntity<LocalDeviceRespDTO> findOne(@PathVariable("uuid") String uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(respMapper.toDto(localDeviceService.findByUuid(uuid)));
    }

    @ApiOperation(value = "Update device")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK, response = LocalDeviceResponseDTO.class)
    })
    @PutMapping
    public ResponseEntity<LocalDeviceResponseDTO> update(@RequestBody LocalDeviceRequestDTO localDevice) {
        return ResponseEntity.status(HttpStatus.OK).body(
                responseMapper.toDto(localDeviceService.update(requestMapper.toEntity(localDevice))));
    }

    @ApiOperation(value = "Delete device")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = HttpStatuses.NO_CONTENT),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
    })
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable("uuid") String uuid) {
        localDeviceService.delete(uuid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
