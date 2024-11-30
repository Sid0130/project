package com.itwill.project.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Doctor {

	public static final class Entity {

		public static final String TBL_DOCTOR = "DOCTOR";
		public static final String COL_DOCTOR_ID = "DOCTOR_ID";
		public static final String COL_DOCTOR_NAME = "DOCTOR_NAME";
		public static final String COL_DOCTOR_SPECIALTY = "SPECIALTY";
		public static final String COL_DOCTOR_PHONE_NUMBER = "PHONE_NUMBER";
		public static final String COL_DOCTOR_MEMO = "MEMO";
		public static final String COL_DOCTOR_CREATED_TIME = "CREATED_TIME";
		public static final String COL_DOCTOR_MODIFIED_TIME = "MODIFIED_TIME";
	}

	private Integer id;
	private String name;
	private String specialty;
	private String phoneNumber;
	private String memo;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;

	public Doctor() {
	}


	public Doctor(Integer id, String name, String specialty, String phoneNumber, String memo, LocalDateTime createdTime,
			LocalDateTime modifiedTime) {
		this.id = id;
		this.name = name;
		this.specialty = specialty;
		this.phoneNumber = phoneNumber;
		this.memo = memo;
		this.createdTime = createdTime;
		this.modifiedTime = modifiedTime;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getId() {
		return id;
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


	
	
	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", specialty=" + specialty + ", phoneNumber=" + phoneNumber
				+ ", memo=" + memo + ", createdTime=" + createdTime + ", modifiedTime=" + modifiedTime + "]";
	}


	public static DoctorBuilder builder(){
		return new DoctorBuilder();
	}
	
	public static class DoctorBuilder {
		
		private Integer id;
		private String name;
		private String specialty;
		private String phoneNumber;
		private String memo;
		private LocalDateTime createdTime;
		private LocalDateTime modifiedTime;
		
		
		public DoctorBuilder() {}
		
		public DoctorBuilder id(Integer id) {
			this.id = id;
			return this;
		}
		
		public DoctorBuilder name(String name) {
			this.name = name;
			return this;
		}
		
		public DoctorBuilder specialty(String specialty) {
			this.specialty = specialty;
			return this;
		}
		
		public DoctorBuilder phoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}
		
		public DoctorBuilder memo(String memo) {
			this.memo = memo;
			return this;
		}
		
		public DoctorBuilder createdTime(LocalDateTime createdTime) {
			this.createdTime = createdTime;
			return this;
		}
		
		public DoctorBuilder createdTime(Timestamp createdTime) {
			if(createdTime != null) {
				this.createdTime = createdTime.toLocalDateTime();
			}
			return this;
		}
		
		
		public DoctorBuilder modifiedTime(LocalDateTime modifiedTime) {
			this.modifiedTime = modifiedTime;
			return this;
		}
		
		public DoctorBuilder modifiedTime(Timestamp modifiedTime) {
			if(modifiedTime != null) {
				this.modifiedTime = modifiedTime.toLocalDateTime();
			}
			return this;
		}
		
		
		public Doctor build() {
			return new Doctor(id, name, specialty, phoneNumber, memo, createdTime ,modifiedTime);
		}
	}
}
