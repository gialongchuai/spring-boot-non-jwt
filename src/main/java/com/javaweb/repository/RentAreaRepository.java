package com.javaweb.repository;

import java.util.List;

import com.javaweb.repository.entity.RentAreaEntity;

public interface RentAreaRepository {
	List<RentAreaEntity> findValueByBuildingId(String id);
}