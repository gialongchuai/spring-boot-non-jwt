package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	private BuildingRepository buildingRepository;

	@Override
	public List<BuildingDTO> findAll(Map<String, Object> params, List<String> typecode) {
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(params, typecode);
		List<BuildingDTO> buildingDTOs = new ArrayList<>();
		for (BuildingEntity x : buildingEntities) {
			BuildingDTO buildingDTO = new BuildingDTO();
			buildingDTO.setName(x.getName());
			buildingDTO.setStreet(x.getStreet());
			buildingDTO.setWard(x.getWard());
			buildingDTO.setDistrictid(x.getDistrictid());
			buildingDTOs.add(buildingDTO);
		}
		return buildingDTOs;
	}

}
