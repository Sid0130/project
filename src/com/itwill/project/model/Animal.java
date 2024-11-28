package com.itwill.project.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Animal {

	public static final class Entity {

		public static final String TBL_ANIMAL = "ANIMAL";
		public static final String COL_ANIMAL_ID = "ANIMAL_ID";
		public static final String COL_ANIMAL_NAME = "ANIMAL_NAME";
		public static final String COL_ANIMAL_TYPE = "ANIMAL_TYPE";
		public static final String COL_ANIMAL_AGE = "AGE";
		public static final String COL_ANIMAL_GENDER = "GENDER";
		public static final String COL_ANIMAL_WEIGHT = "WEIGHT";
		public static final String COL_ANIMAL_MEMO = "MEMO";
		public static final String COL_ANIMAL_CREATED_TIME = "CREATED_TIME";
		public static final String COL_ANIMAL_MODIFIED_TIME = "MODIFIED_TIME";
		public static final String COL_ANIMAL_OWNERID = "OWNER_ID";
	}
	
	private Integer animalId;
	private String animalName;
	private String animalType;
	private Integer age;
	private String gender;
	private Double weight;
	private String memo;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;
	private Integer owenrId;
	
	public Animal()	{}

	public Animal(Integer animalId, String animalName, String animalType, Integer age, String gender, Double weight,
			String memo, LocalDateTime createdTime, LocalDateTime modifiedTime, Integer owenrId) {
		this.animalId = animalId;
		this.animalName = animalName;
		this.animalType = animalType;
		this.age = age;
		this.gender = gender;
		this.weight = weight;
		this.memo = memo;
		this.createdTime = createdTime;
		this.modifiedTime = modifiedTime;
		this.owenrId = owenrId;
	}
	
	public Integer getId() {
		return animalId;
	}

	public String getAnimalName() {
		return animalName;
	}

	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}

	public String getAnimalType() {
		return animalType;
	}

	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}
	

	public LocalDateTime getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(LocalDateTime modifiedTime) {
		this.modifiedTime = modifiedTime;
	}


	public Integer getOwenrId() {
		return owenrId;
	}
	
	

	
	@Override
	public String toString() {
		return "Animal [animalId=" + animalId + ", animalName=" + animalName + ", animalType=" + animalType + ", age="
				+ age + ", gender=" + gender + ", weight=" + weight + ", memo=" + memo + ", createdTime=" + createdTime
				+ ", modifiedTime=" + modifiedTime + ", owenrId=" + owenrId + "]";
	}

	public static AnimalBuilder builder() {
		return new  AnimalBuilder();
	}
	
	public static class AnimalBuilder {
		
		private Integer animalId;
		private String animalName;
		private String animalType;
		private Integer age;
		private String gender;
		private Double weight;
		private String memo;
		private LocalDateTime createdTime;
		private LocalDateTime modifiedTime;
		private Integer owenrId;
		
		public AnimalBuilder() {}
		public AnimalBuilder animalId(Integer animalId) {

			this.animalId = animalId;
			return this;
		}

		public AnimalBuilder animalName(String animalName) {
			this.animalName = animalName;
			return this;
		}

		public AnimalBuilder animalType(String animalType) {
			this.animalType = animalType;
			return this;
		}


		public AnimalBuilder age(Integer age) {
			this.age = age;
			return this;
		}


		public AnimalBuilder gender(String gender) {
			this.gender = gender;
			return this;
		}


		public AnimalBuilder weight(Double weight) {
			this.weight = weight;
			return this;
		}


		public AnimalBuilder memo(String memo) {
			this.memo = memo;
			return this;
		}


		public AnimalBuilder createdTime(LocalDateTime createdTime) {
			this.createdTime = createdTime;
			return this;
		}
		
		public AnimalBuilder createdTime(Timestamp createdTime) {
			this.createdTime = createdTime.toLocalDateTime();
			return this;
		}

		public AnimalBuilder modifiedTime(LocalDateTime modifiedTime) {
			this.modifiedTime = modifiedTime;
			return this;
		}
		
		public AnimalBuilder modifiedTime(Timestamp modifiedTime) {
			this.modifiedTime = modifiedTime.toLocalDateTime();
			return this;
		}
		
		public AnimalBuilder ownerId(Integer ownerId) {

			this.owenrId = ownerId;
			return this;
		}

		
		public Animal build() {
			return new Animal(animalId, animalName, animalType, age, gender, weight, memo, createdTime, modifiedTime, owenrId);
		}
	}
	
}
