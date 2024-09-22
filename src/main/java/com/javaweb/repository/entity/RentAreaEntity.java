package com.javaweb.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rentarea")
public class RentAreaEntity {
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "value")
	private String value;

	@ManyToOne
	@JoinColumn(name = "buildingid")
	private BuildingEntity building;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BuildingEntity getBuilding() {
		return building;
	}

	public void setBuilding(BuildingEntity building) {
		this.building = building;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
