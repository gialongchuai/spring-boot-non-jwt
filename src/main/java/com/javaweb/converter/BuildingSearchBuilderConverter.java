package com.javaweb.converter;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class BuildingSearchBuilderConverter {
	public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params, List<String> typecode) {
		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
													.setName(MapUtil.getObject(params, "name", String.class))
													.setFloorarea(MapUtil.getObject(params, "floorarea", Long.class))
													.setNumberofbasement(MapUtil.getObject(params, "numberofbasement", Long.class))
													.setWard(MapUtil.getObject(params, "ward", String.class))
													.setDistrictid(MapUtil.getObject(params, "districtid", Long.class))
													.setStreet(MapUtil.getObject(params, "street", String.class))
													.setManagername(MapUtil.getObject(params, "managerame", String.class))
													.setManagerphonenumber(MapUtil.getObject(params, "managerphonenumber", String.class))
													.setDirection(MapUtil.getObject(params, "direction", String.class))
													.setLevel(MapUtil.getObject(params, "level", String.class))
													.setRentpricefrom(MapUtil.getObject(params, "rentpricefrom", Long.class))
													.setRentpriceto(MapUtil.getObject(params, "rentpriceto", Long.class))
													.setRentareafrom(MapUtil.getObject(params, "rentareafrom", Long.class))
													.setRentareato(MapUtil.getObject(params, "rentareato", Long.class))
													.setStaffid(MapUtil.getObject(params, "staffid", Long.class))
													.setTypecode(typecode)
													.build();
		return buildingSearchBuilder;
	}
}
