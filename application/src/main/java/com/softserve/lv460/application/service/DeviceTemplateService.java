package com.softserve.lv460.application.service;

import com.softserve.lv460.application.dto.data.DataResponse;
import com.softserve.lv460.application.dto.deviceTemplate.DeviceTemplateFilterDTO;
import com.softserve.lv460.application.dto.deviceTemplate.DeviceTemplateResponseDTO;
import com.softserve.lv460.application.entity.DeviceTemplate;

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

