package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	public static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	public static final String USER = "root";
	public static final String PASSWORD = "123123";

	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params, List<String> typecode) {
		List<BuildingEntity> buildingEntities = new ArrayList<>();
		StringBuilder sql = new StringBuilder("select * from building b where 1 = 1 ");
		try (Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString())) {
			while (rs.next()) {
				BuildingEntity buildingEntity = new BuildingEntity();
				buildingEntity.setName(rs.getString("name"));
				buildingEntity.setStreet(rs.getString("street"));
				buildingEntity.setWard(rs.getString("ward"));
				buildingEntity.setDistrictid(rs.getInt("districtid"));
				buildingEntities.add(buildingEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buildingEntities;
	}
}
