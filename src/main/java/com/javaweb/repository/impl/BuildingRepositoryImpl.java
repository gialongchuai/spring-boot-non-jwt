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
import com.javaweb.utils.NumberUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	public static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	public static final String USER = "root";
	public static final String PASSWORD = "123123";

	public void joniTable(Map<String, Object> params, List<String> typecode, StringBuilder sql) {
		if (typecode.toString() != null || !typecode.toString().equals("")) {
			sql.append(" join buildingrenttype be on be.buildingid = b.id ");
			sql.append(" join renttype re on re.id = be.renttypeid ");
		}
		for (Map.Entry<String, Object> x : params.entrySet()) {
			if (x.getKey().equals("value")) {
				if (x.getValue().toString() != null || !x.getValue().toString().equals("")) {
					sql.append(" join rentarea ra on b.id = ra.buildingid ");
				}

			}
		}
	}

	public void queryNormal(Map<String, Object> params, List<String> typecode, StringBuilder sql) {
		for (Map.Entry<String, Object> x : params.entrySet()) {
			if (x.getKey().toString().equals("name")) {
				if (x.getValue() != null || !x.getValue().equals("")) {
					sql.append(" and b.name like '%" + x.getValue() + "%' ");
				}
			}
			if (x.getKey().toString().equals("floorare")) {
				if (x.getValue() != null || !x.getValue().equals("")) {
					if (NumberUtil.checkNumber(x.getValue().toString())) {
						sql.append(" and b.floorarea = " + Long.parseLong(x.getValue().toString()) + " ");
					}
				}
			}
			if (x.getKey().toString().equals("district")) {
				if (x.getValue() != null || !x.getValue().equals("")) {
					if (NumberUtil.checkNumber(x.getValue().toString())) {
						sql.append(" and b.districtid = " + Long.parseLong(x.getValue().toString()) + " ");
					}
				}
			}
			if (x.getKey().toString().equals("ward")) {
				if (x.getValue() != null || !x.getValue().equals("")) {
					sql.append(" and b.ward like '%" + x.getValue() + "%' ");
				}
			}
			if (x.getKey().toString().equals("street")) {
				if (x.getValue() != null || !x.getValue().equals("")) {
					sql.append(" and b.street like '%" + x.getValue() + "%' ");
				}
			}
			if (x.getKey().toString().equals("numberofbasement")) {
				if (x.getValue() != null || !x.getValue().equals("")) {
					if (NumberUtil.checkNumber(x.getValue().toString())) {
						sql.append(" and b.numberofbasement = " + Long.parseLong(x.getValue().toString()) + " ");
					}
				}
			}
			if (x.getKey().toString().equals("managername")) {
				if (x.getValue() != null || !x.getValue().equals("")) {
					sql.append(" and b.managername like '%" + x.getValue() + "%' ");
				}
			}
			if (x.getKey().toString().equals("managerphonenumber")) {
				if (x.getValue() != null || !x.getValue().equals("")) {
					sql.append(" and b.managerphonenumber like '%" + x.getValue() + "%' ");
				}
			}
		}
	}

	public void querySpecial(Map<String, Object> params, List<String> typecode, StringBuilder sql) {
		for (Map.Entry<String, Object> x : params.entrySet()) {
			if (x.getKey().toString().equals("value")) {
				if (x.getValue() != null || !x.getValue().equals("")) {
					sql.append(" and ra.value like '%" + x.getValue() + "%' ");
				}
			}
			if (x.getKey().toString().equals("rentpricefrom")) {
				if (NumberUtil.checkNumber(x.getValue().toString())) {
					sql.append(" and b.rentprice >= " + Long.parseLong(x.getValue().toString()) + " ");
				}
			}
			if (x.getKey().toString().equals("rentpriceto")) {
				if (NumberUtil.checkNumber(x.getValue().toString())) {
					sql.append(" and b.rentprice <= " + Long.parseLong(x.getValue().toString()) + " ");
				}
			}
		}
		if (typecode.toString() != null || typecode.toString().equals("")) {
			String x = String.join(",", typecode);
			String p[] = x.split(",");
			String h = "";
			for (String e : p) {
				String m = "'" + e + "'" + ",";
				h += m;
			}
			String result = "";
			for(int i = 0 ; i<h.length()-1; i++) {
                result += h.charAt(i);
            }
			sql.append(" and re.code in (" + result + ") ");
		}
	}

	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params, List<String> typecode) {
		List<BuildingEntity> buildingEntities = new ArrayList<>();
		StringBuilder sql = new StringBuilder(
				"select  b.name, b.street, b.ward, b.districtid, b.numberofbasement, b.floorarea, b.rentprice, b.managername, b.managerphonenumber from building b ");
		joniTable(params, typecode, sql);
		String where = " where 1 = 1 ";
		sql.append(where);
		queryNormal(params, typecode, sql);
		querySpecial(params, typecode, sql);
		sql.append(" group by b.id ");
		System.out.println(sql);

		try (Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString())) {
			while (rs.next()) {
				BuildingEntity buildingEntity = new BuildingEntity();
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
