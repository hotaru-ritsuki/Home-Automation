package com.ritsuki.application.service.impl;

import com.ritsuki.application.constant.ErrorMessage;
import com.ritsuki.application.dto.data.DataResponse;
import com.ritsuki.application.dto.deviceTemplate.DeviceTemplateFilterDTO;
import com.ritsuki.application.dto.deviceTemplate.DeviceTemplateResponseDTO;
import com.ritsuki.application.entity.DeviceTemplate;
import com.ritsuki.application.exception.exceptions.NotDeletedException;
import com.ritsuki.application.exception.exceptions.NotFoundException;
import com.ritsuki.application.exception.exceptions.NotUpdatedException;
import com.ritsuki.application.repository.DeviceTemplateRepository;
import com.ritsuki.application.service.DeviceTemplateService;
import com.ritsuki.application.specification.DeviceTemplateSpecification;
import com.ritsuki.application.mapper.deviceTemplate.DeviceTemplateResponseMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class DeviceTemplateServiceImpl implements DeviceTemplateService {
    private static final int PAGE_SIZE = 10;

    private final DeviceTemplateRepository deviceTemplateRepository;
    private final DeviceTemplateResponseMapper responseMapper;

    public DeviceTemplate findById(Long id) {
        return deviceTemplateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorMessage.DEVICE_TEMPLATE_NOT_FOUND_BY_ID, id)));
    }

    public List<DeviceTemplate> findAll() {
        return deviceTemplateRepository.findAll();
    }

    public List<String> findAllTypes() {
        return deviceTemplateRepository.findAllTypes();
    }

    public List<String> findAllModels() {
        return deviceTemplateRepository.findAllModels();
    }

    public List<String> findAllBrands() {
        return deviceTemplateRepository.findAllBrands();
    }

    public List<Integer> findAllReleaseYears() {
        return deviceTemplateRepository.findAllReleaseYears();
    }

    public DeviceTemplate save(DeviceTemplate deviceTemplate) {
        return deviceTemplateRepository.save(deviceTemplate);
    }


    public void delete(Long id) {
        if (!deviceTemplateRepository.findById(id).isPresent()) {
            throw new NotDeletedException(String.format(ErrorMessage.DEVICE_TEMPLATE_NOT_DELETED_BY_ID, id));
        }
        deviceTemplateRepository.deleteById(id);
    }

    public DeviceTemplate update(DeviceTemplate deviceTemplate) {
        if (!deviceTemplateRepository.findById(deviceTemplate.getId()).isPresent()) {
            throw new NotUpdatedException(String.format(ErrorMessage.DEVICE_TEMPLATE_NOT_UPDATED_BY_ID,
                    deviceTemplate.getId()));
        }
        return deviceTemplateRepository.save(deviceTemplate);
    }

    public DataResponse<DeviceTemplateResponseDTO> findAllByFilter(Integer page,
                                                                   DeviceTemplateFilterDTO request) {
        DeviceTemplateSpecification deviceTemplateSpecifications = new DeviceTemplateSpecification(request);
        Page<DeviceTemplate> allByFilter = deviceTemplateRepository.findAll(deviceTemplateSpecifications,
                PageRequest.of(page, PAGE_SIZE));
        return new DataResponse<>(allByFilter.get().map(e -> responseMapper.toDto(e))
                .collect(Collectors.toList()), allByFilter.getTotalPages(), allByFilter.getTotalElements());
    }
}
