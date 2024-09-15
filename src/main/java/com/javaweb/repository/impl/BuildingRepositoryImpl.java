package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.print.attribute.standard.Sides;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionJDBCUtil;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	public void joniTable(Map<String, Object> params, List<String> typecode, StringBuilder sql) {
		String value = (String) params.get("value");
		if (StringUtil.checkString(value)) {
			sql.append(" join rentarea ra on b.id = ra.buildingid ");
		}
		if (typecode != null && typecode.size() != 0) {
			sql.append(" join buildingrenttype be on be.buildingid = b.id ");
			sql.append(" join renttype re on re.id = be.renttypeid ");
		}
	}

	public void queryNormal(Map<String, Object> params, List<String> typecode, StringBuilder sql) {
		for (Map.Entry<String, Object> x : params.entrySet()) {
			if (!x.getKey().equals("value") && !x.getKey().startsWith("rentprice")
					&& !x.getKey().startsWith("typecode")) {
				String data = (String) x.getValue();
				if (StringUtil.checkString(data)) {
					if (NumberUtil.checkNumber(data)) {
						sql.append(" and b." + x.getKey() + " = " + data + " ");
					} else {
						sql.append(" and b." + x.getKey() + " like '%" + data + "%' ");
					}
				}
			}
		}
	}

	public void querySpecial(Map<String, Object> params, List<String> typecode, StringBuilder sql) {
		String rentPriceFrom = (String) params.get("rentpricefrom");
		String rentPriceTo = (String) params.get("rentpriceto");
		if (StringUtil.checkString(rentPriceFrom)) {
			sql.append(" and b.rentprice >= " + rentPriceFrom + " ");
		}
		if (StringUtil.checkString(rentPriceTo)) {
			sql.append(" and b.rentprice <= " + rentPriceTo + " ");
		}
//		if (typecode != null && typecode.size() != 0) {
//			List<String> result = new ArrayList<>();
//			for (String x : typecode) {
//				result.add("'" + x + "'");
//			}
//			sql.append(" and re.code in (" + String.join(",", result) + ") ");
//		}
		if (typecode != null && typecode.size() != 0) {
			sql.append(" AND(");
			String tmp = typecode.stream().map(item -> "re.code like" + "'%" + item + "%'").collect(Collectors.joining(" OR "));
			sql.append(tmp);
			sql.append(" ) ");
		}
	}

	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params, List<String> typecode) {
		List<BuildingEntity> buildingEntities = new ArrayList<>();
		StringBuilder sql = new StringBuilder(
				"select b.id, b.name, b.street, b.ward, b.districtid, b.numberofbasement, b.floorarea, b.rentprice, b.managername, b.managerphonenumber from building b ");
		joniTable(params, typecode, sql);
		String where = " where 1 = 1 ";
		sql.append(where);
		queryNormal(params, typecode, sql);
		querySpecial(params, typecode, sql);
		sql.append(" group by b.id ");

		try (Connection con = ConnectionJDBCUtil.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString())) {
			while (rs.next()) {
				BuildingEntity buildingEntity = new BuildingEntity();
				buildingEntity.setId(rs.getString("id"));
				buildingEntity.setName(rs.getString("name"));
				buildingEntity.setStreet(rs.getString("street"));
				buildingEntity.setWard(rs.getString("ward"));
				buildingEntity.setDistrict(rs.getLong("districtid"));
				buildingEntity.setNumberOfBasement(rs.getLong("numberofbasement"));
				buildingEntity.setFloorArea(rs.getLong("floorarea"));
				buildingEntity.setRentPrice(rs.getLong("rentprice"));
				buildingEntity.setManagerName(rs.getNString("managername"));
				buildingEntity.setManagerPhoneNumber(rs.getString("managerphonenumber"));
				buildingEntities.add(buildingEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buildingEntities;
	}
}
