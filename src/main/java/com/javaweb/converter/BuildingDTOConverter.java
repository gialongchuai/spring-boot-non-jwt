package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Component
public class BuildingDTOConverter {
	
	
	@Autowired
	private ModelMapper modelMapper;
	public BuildingDTO toBuildingDTO(BuildingEntity x) {
		BuildingDTO buildingDTO = modelMapper.map(x, BuildingDTO.class);
//		DistrictEntity districtRepositories = districtRepository.findNameById(x.getDistrict());
		buildingDTO.setAddress(x.getWard() + "," + x.getStreet() + "," + x.getDistrict().getName());
		List<RentAreaEntity> rentAreaEntities = x.getRentAreaEntities();
		String result = rentAreaEntities.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
		buildingDTO.setRentArea(result);
		return buildingDTO;
}
}
