package com.ritsuki.application.service;

import com.ritsuki.application.dto.data.DataResponse;
import com.ritsuki.application.dto.deviceTemplate.DeviceTemplateFilterDTO;
import com.ritsuki.application.dto.deviceTemplate.DeviceTemplateResponseDTO;
import com.ritsuki.application.entity.DeviceTemplate;

import java.util.List;

public interface DeviceTemplateService {

    DeviceTemplate findById(Long id);

    List<DeviceTemplate> findAll();

    List<String> findAllTypes();

    List<String> findAllModels();

    List<String> findAllBrands();

    List<Integer> findAllReleaseYears();

    DeviceTemplate save(DeviceTemplate deviceTemplate);

    void delete(Long id);

    DeviceTemplate update(DeviceTemplate deviceTemplate);

    DataResponse<DeviceTemplateResponseDTO> findAllByFilter(Integer page,
                                                            DeviceTemplateFilterDTO request);
}

