package com.javaweb.builder;

import java.util.ArrayList;
import java.util.List;

public class BuildingSearchBuilder {
	private String name;
	private Long floorarea;
	private Long numberofbasement;
	private Long districtid;
	private String ward;
	private String street;
	private String managername;
	private String managerphonenumber;
	private String direction;
	private String level;
	private List<String> typecode = new ArrayList<>();
	private Long rentpricefrom;
	private Long rentpriceto;
	private Long rentareafrom;
	private Long rentareato;
	private Long staffid;

	private BuildingSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.floorarea = builder.floorarea;
		this.ward = builder.ward;
		this.street = builder.street;
		this.districtid = builder.districtid;
		this.numberofbasement = builder.numberofbasement;
		this.level = builder.level;
		this.direction = builder.direction;
		this.typecode = builder.typecode;
		this.managername = builder.managername;
		this.managerphonenumber = builder.managerphonenumber;
		this.rentpricefrom = builder.rentpricefrom;
		this.rentpriceto = builder.rentpriceto;
		this.rentareafrom = builder.rentareafrom;
		this.rentareato = builder.rentareato;
		this.staffid = builder.staffid;
	}

	public String getName() {
		return name;
	}

	public Long getFloorarea() {
		return floorarea;
	}

	public Long getNumberofbasement() {
		return numberofbasement;
	}

	public Long getDistrictid() {
		return districtid;
	}

	public String getWard() {
		return ward;
	}

	public String getStreet() {
		return street;
	}

	public String getManagername() {
		return managername;
	}

	public String getManagerphonenumber() {
		return managerphonenumber;
	}

	public String getDirection() {
		return direction;
	}

	public String getLevel() {
		return level;
	}

	public List<String> getTypecode() {
		return typecode;
	}

	public Long getRentpricefrom() {
		return rentpricefrom;
	}

	public Long getRentpriceto() {
		return rentpriceto;
	}

	public Long getRentareafrom() {
		return rentareafrom;
	}

	public Long getRentareato() {
		return rentareato;
	}

	public Long getStaffid() {
		return staffid;
	}

	public static class Builder {
		private String name;
		private Long floorarea;
		private Long numberofbasement;
		private Long districtid;
		private String ward;
		private String street;
		private String managername;
		private String managerphonenumber;
		private String direction;
		private String level;
		private List<String> typecode = new ArrayList<>();
		private Long rentpricefrom;
		private Long rentpriceto;
		private Long rentareafrom;
		private Long rentareato;
		private Long staffid;

		public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setFloorarea(Long floorarea) {
            this.floorarea = floorarea;
            return this;
        }

        public Builder setNumberofbasement(Long numberofbasement) {
            this.numberofbasement = numberofbasement;
            return this;
        }

        public Builder setDistrictid(Long districtid) {
            this.districtid = districtid;
            return this;
        }

        public Builder setWard(String ward) {
            this.ward = ward;
            return this;
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder setManagername(String managername) {
            this.managername = managername;
            return this;
        }

        public Builder setManagerphonenumber(String managerphonenumber) {
            this.managerphonenumber = managerphonenumber;
            return this;
        }

        public Builder setDirection(String direction) {
            this.direction = direction;
            return this;
        }

        public Builder setLevel(String level) {
            this.level = level;
            return this;
        }

        public Builder setTypecode(List<String> typecode) {
            this.typecode = typecode;
            return this;
        }

        public Builder setRentpricefrom(Long rentpricefrom) {
            this.rentpricefrom = rentpricefrom;
            return this;
        }

        public Builder setRentpriceto(Long rentpriceto) {
            this.rentpriceto = rentpriceto;
            return this;
        }

        public Builder setRentareato(Long rentareato) {
            this.rentareato = rentareato;
            return this;
        }
        
        public Builder setRentareafrom(Long rentareafrom) {
            this.rentareafrom = rentareafrom;
            return this;
        }

        public Builder setStaffid(Long staffid) {
            this.staffid = staffid;
            return this;
        }

        public BuildingSearchBuilder build() {
            return new BuildingSearchBuilder(this);
        }
	}
}