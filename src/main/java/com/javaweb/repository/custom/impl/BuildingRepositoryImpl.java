package com.javaweb.repository.custom.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
@Primary
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

	public void joniTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
		Long rentAreaFrom = buildingSearchBuilder.getRentareafrom();
		Long rentAreaTo = buildingSearchBuilder.getRentareato();
		if (rentAreaFrom != null || rentAreaTo != null) {
			sql.append(" join rentarea ra on b.id = ra.buildingid ");
		}
		List<String> typecode = buildingSearchBuilder.getTypecode();
		if (typecode != null && typecode.size() != 0) {
			sql.append(" join buildingrenttype be on be.buildingid = b.id ");
			sql.append(" join renttype re on re.id = be.renttypeid ");
		}
	}

	public void queryNormal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
//		for (Map.Entry<String, Object> x : params.entrySet()) {
//			if (!x.getKey().startsWith("rentarea") && !x.getKey().startsWith("rentprice")
//					&& !x.getKey().startsWith("typecode")) {
//				String data = (String) x.getValue();
//				if (StringUtil.checkString(data)) {
//					if (NumberUtil.checkNumber(data)) {
//						sql.append(" and b." + x.getKey() + " = " + data + " ");
//					} else {
//						sql.append(" and b." + x.getKey() + " like '%" + data + "%' ");
//					}
//				}
//			}
//		}
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field item : fields) {
				item.setAccessible(true);
				String fieldName = item.getName();
				if (!fieldName.startsWith("rentarea") && !fieldName.startsWith("rentprice")
						&& !fieldName.startsWith("typecode")) {
					Object value = item.get(buildingSearchBuilder);
					if (value != null) {
						if (item.getType().getName().equals("java.lang.Long")) {
							sql.append(" and b." + fieldName + " = " + value + " ");
						} else if (item.getType().getName().equals("java.lang.String")) {
							sql.append(" and b." + fieldName + " like '%" + value + "%' ");
						}
					}
				}
			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	public void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
		Long rentPriceFrom = buildingSearchBuilder.getRentpricefrom();
		Long rentPriceTo = buildingSearchBuilder.getRentpriceto();
		if (rentPriceFrom != null) {
			sql.append(" and b.rentprice >= " + rentPriceFrom + " ");
		}
		if (rentPriceTo != null) {
			sql.append(" and b.rentprice <= " + rentPriceTo + " ");
		}

		Long rentAreaFrom = buildingSearchBuilder.getRentareafrom();
		Long rentAreaTo = buildingSearchBuilder.getRentareato();
		if (rentAreaFrom != null) {
			sql.append(" and ra.value >= " + rentAreaFrom + " ");
		}
		if (rentAreaTo != null) {
			sql.append(" and ra.value <= " + rentAreaTo + " ");
		}
//		if (typecode != null && typecode.size() != 0) {
//			List<String> result = new ArrayList<>();
//			for (String x : typecode) {
//				result.add("'" + x + "'");
//			}
//			sql.append(" and re.code in (" + String.join(",", result) + ") ");
//		}
		List<String> typecode = buildingSearchBuilder.getTypecode();
		if (typecode != null && typecode.size() != 0) {
			sql.append(" AND(");
			String tmp = typecode.stream().map(item -> "re.code like" + "'%" + item + "%'")
					.collect(Collectors.joining(" OR "));
			sql.append(tmp);
			sql.append(" ) ");
		}
	}

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		StringBuilder sql = new StringBuilder(
				"select b.id, b.name, b.street, b.ward, b.districtid, b.numberofbasement, b.floorarea, b.rentprice, b.managername, b.managerphonenumber, b.level from building b ");
		joniTable(buildingSearchBuilder, sql);
		String where = " where 1 = 1 ";
		sql.append(where);
		queryNormal(buildingSearchBuilder, sql);
		querySpecial(buildingSearchBuilder, sql);
		sql.append(" group by b.id ");
		Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
		return query.getResultList();
	}
}
