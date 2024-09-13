package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
@Repository
public class DistrictRepositoryImpl implements DistrictRepository {

	public static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	public static final String USER = "root";
	public static final String PASSWORD = "123123";
	@Override
	public DistrictEntity findNameById(Long districtid) {
		DistrictEntity districtEntities = new DistrictEntity();
		String sql = "select name from district where id = " + districtid;
		try (Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				districtEntities.setName(rs.getNString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return districtEntities;
	}

}
