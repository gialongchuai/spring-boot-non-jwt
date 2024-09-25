package com.javaweb.repository.custom.impl;
//package com.javaweb.repository.impl;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.stereotype.Repository;
//
//import com.javaweb.repository.RentAreaRepository;
//import com.javaweb.repository.entity.RentAreaEntity;
//import com.javaweb.utils.ConnectionJDBCUtil;
//
//@Repository
//public class RentAreaRepositoryImpl implements RentAreaRepository {
//
//	@Override
//	public List<RentAreaEntity> findValueByBuildingId(String id) {
//		String sql = "select * from rentarea where buildingid = " + id;
//		List<RentAreaEntity> rentAreaEntities = new ArrayList<>();
//		try (Connection con = ConnectionJDBCUtil.getConnection();
//				Statement stmt = con.createStatement();
//				ResultSet rs = stmt.executeQuery(sql)) {
//			while (rs.next()) {
//				RentAreaEntity rentAreaEntity = new RentAreaEntity();
//				rentAreaEntity.setValue(rs.getString("value"));
//				rentAreaEntities.add(rentAreaEntity);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return rentAreaEntities;
//	}
//
//}
