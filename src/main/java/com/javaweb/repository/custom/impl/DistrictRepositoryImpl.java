package com.javaweb.repository.custom.impl;
//package com.javaweb.repository.impl;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//import org.springframework.stereotype.Repository;
//
//import com.javaweb.repository.DistrictRepository;
//import com.javaweb.repository.entity.DistrictEntity;
//import com.javaweb.utils.ConnectionJDBCUtil;
//@Repository
//public class DistrictRepositoryImpl implements DistrictRepository {
//	@Override
//	public DistrictEntity findNameById(Long districtid) {
//		DistrictEntity districtEntities = new DistrictEntity();
//		String sql = "select name from district where id = " + districtid;
//		try (Connection con = ConnectionJDBCUtil.getConnection();
//				Statement stmt = con.createStatement();
//				ResultSet rs = stmt.executeQuery(sql)) {
//			while (rs.next()) {
//				districtEntities.setName(rs.getNString("name"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return districtEntities;
//	}
//
//}
