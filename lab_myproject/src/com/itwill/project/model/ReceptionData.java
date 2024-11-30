package com.itwill.project.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ReceptionData {

	private Integer receptionId;
	private int animalId;
	private int doctorId;
	private int ownerId;
	private String receptionAppointmentTime;
	private String receptionAppointmentDate;
	private String receptionStatus;
	private LocalDateTime receptionCreatedTime;
	private LocalDateTime receptionModifiedTime;

	private String doctorName;
	private String ownerName;

	private String animalName;
	private String animalType;
	private Integer animalAge;
	private String animalGender;
	private Double animalWeight;

	public ReceptionData() {
	}

	public ReceptionData(Integer receptionId, int animalId, int doctorId, int ownerId,
			String receptionAppointmentTime, String receptionAppointmentDate ,String receptionStatus, LocalDateTime receptionCreatedTime,
			LocalDateTime receptionModifiedTime, String doctorName, String ownerName, String animalName, String animalType,
			Integer animalAge, String animalGender, Double animalWeight) {

		this.receptionId = receptionId;
		this.animalId = animalId;
		this.doctorId = doctorId;
		this.ownerId = ownerId;
		this.receptionAppointmentTime = receptionAppointmentTime;
		this.receptionAppointmentDate = receptionAppointmentDate;
		this.receptionStatus = receptionStatus;
		this.receptionCreatedTime = receptionCreatedTime;
		this.receptionModifiedTime = receptionModifiedTime;
		this.doctorName = doctorName;
		this.ownerName = ownerName;
		this.animalName = animalName;
		this.animalType = animalType;
		this.animalAge = animalAge;
		this.animalGender = animalGender;
		this.animalWeight = animalWeight;

	}

	public String getReceptionAppointmentTime() {
		return receptionAppointmentTime;
	}

	public void setReceptionAppointmentTime(String receptionAppointmentTime) {
		this.receptionAppointmentTime = receptionAppointmentTime;
	}

	public String getReceptionAppointmentDate() {
		return receptionAppointmentDate;
	}

	public void setReceptionAppointmentDate(String receptionAppointmentDate) {
		this.receptionAppointmentDate = receptionAppointmentDate;
	}

	public String getReceptionStatus() {
		return receptionStatus;
	}

	public void setReceptionStatus(String receptionStatus) {
		this.receptionStatus = receptionStatus;
	}

	public LocalDateTime getReceptionCreatedTime() {
		return receptionCreatedTime;
	}

	public void setReceptionCreatedTime(LocalDateTime receptionCreatedTime) {
		this.receptionCreatedTime = receptionCreatedTime;
	}

	public LocalDateTime getReceptionModifiedTime() {
		return receptionModifiedTime;
	}

	public void setReceptionModifiedTime(LocalDateTime receptionModifiedTime) {
		this.receptionModifiedTime = receptionModifiedTime;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
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

	public Integer getAnimalAge() {
		return animalAge;
	}

	public void setAnimalAge(Integer animalAge) {
		this.animalAge = animalAge;
	}

	public String getAnimalGender() {
		return animalGender;
	}

	public void setAnimalGender(String animalGender) {
		this.animalGender = animalGender;
	}

	public Double getAnimalWeight() {
		return animalWeight;
	}

	public void setAnimalWeight(Double animalWeight) {
		this.animalWeight = animalWeight;
	}

	public Integer getReceptionId() {
		return receptionId;
	}

	public int getAnimalId() {
		return animalId;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public int getOwnerId() {
		return ownerId;
	}
	
	

	

	@Override
	public String toString() {
		return "ReceptionData [receptionId=" + receptionId + ", animalId=" + animalId + ", doctorId=" + doctorId
				+ ", ownerId=" + ownerId + ", receptionAppointmentTime=" + receptionAppointmentTime
				+ ", receptionAppointmentDate=" + receptionAppointmentDate + ", receptionStatus=" + receptionStatus
				+ ", receptionCreatedTime=" + receptionCreatedTime + ", receptionModifiedTime=" + receptionModifiedTime
				+ ", doctorName=" + doctorName + ", ownerName=" + ownerName + ", animalName=" + animalName
				+ ", animalType=" + animalType + ", animalAge=" + animalAge + ", animalGender=" + animalGender
				+ ", animalWeight=" + animalWeight + "]";
	}

	public static ReceptionDataBuilder builder() {
		return new ReceptionDataBuilder();
		
	}
	
	public static class ReceptionDataBuilder{
		
		private Integer receptionId;
		private int animalId;
		private int doctorId;
		private int ownerId;
		private String receptionAppointmentTime;
		private String receptionAppointmentDate;
		private String receptionStatus;
		private LocalDateTime receptionCreatedTime;
		private LocalDateTime receptionModifiedTime;

		private String doctorName;
		private String ownerName;

		private String animalName;
		private String animalType;
		private Integer animalAge;
		private String animalGender;
		private Double animalWeight;
		
		
		public ReceptionDataBuilder() {	}
		
		public ReceptionDataBuilder(Integer receptionId, int animalId, int doctorId, int ownerId,
				String receptionAppointmentTime,String receptionAppointmentDate , String receptionStatus, LocalDateTime receptionCreatedTime,
				LocalDateTime receptionModifiedTime, String doctorName, String ownerName, String animalName, String animalType,
				Integer animalAge, String animalGender, Double animalWeight) {

			this.receptionId = receptionId;
			this.animalId = animalId;
			this.doctorId = doctorId;
			this.ownerId = ownerId;
			this.receptionAppointmentTime = receptionAppointmentTime;
			this.receptionAppointmentDate = receptionAppointmentDate;
			this.receptionStatus = receptionStatus;
			this.receptionCreatedTime = receptionCreatedTime;
			this.receptionModifiedTime = receptionModifiedTime;
			this.doctorName = doctorName;
			this.ownerName = ownerName;
			this.animalName = animalName;
			this.animalType = animalType;
			this.animalAge = animalAge;
			this.animalGender = animalGender;
			this.animalWeight = animalWeight;
		}

		public ReceptionDataBuilder receptionId(Integer receptionId) {
			this.receptionId = receptionId;
			return this;
		}
		public ReceptionDataBuilder animalId(int animalId) {
			this.animalId = animalId;
			return this;
		}
		public ReceptionDataBuilder doctorId(int doctorId) {
			this.doctorId = doctorId;
			return this;
		}
		public ReceptionDataBuilder ownerId(int ownerId) {
			this.ownerId = ownerId;
			return this;
		}
		public ReceptionDataBuilder receptionAppointmentTime(String receptionAppointmentTime) {
			this.receptionAppointmentTime = receptionAppointmentTime;
			return this;
		}
		
		public ReceptionDataBuilder receptionAppointmentDate(String receptionAppointmentDate) {
			this.receptionAppointmentDate = receptionAppointmentDate;
			return this;
		}
		
		public ReceptionDataBuilder receptionStatus(String receptionStatus) {
			this.receptionStatus = receptionStatus;
			return this;
		}
		public ReceptionDataBuilder receptionCreatedTime(Timestamp receptionCreatedTime) {
			this.receptionCreatedTime = receptionCreatedTime.toLocalDateTime();
			return this;
		}
		public ReceptionDataBuilder receptionModifiedTime(Timestamp receptionModifiedTime) {
			this.receptionModifiedTime = receptionModifiedTime.toLocalDateTime();
			return this;
		}
		
		public ReceptionDataBuilder doctorName(String doctorName) {
			this.doctorName = doctorName;
			return this;
		}
		
		public ReceptionDataBuilder ownerName(String ownerName) {
			this.ownerName = ownerName;
			return this;
		}
		public ReceptionDataBuilder animalName(String animalName) {
			this.animalName = animalName;
			return this;

		}

		public ReceptionDataBuilder animalType(String animalType) {
			this.animalType = animalType;
			return this;
		}
		public ReceptionDataBuilder animalAge(Integer animalAge) {
			this.animalAge = animalAge;
			return this;
		}
		
		public ReceptionDataBuilder animalGender(String animalGender) {
			this.animalGender = animalGender;
			return this;
		}
		public ReceptionDataBuilder animalWeight(Double animalWeight) {
			this.animalWeight = animalWeight;
			return this;
		}

		public ReceptionData build() {
			return new ReceptionData(receptionId, animalId, doctorId, ownerId, receptionAppointmentTime, receptionAppointmentDate,
					receptionStatus, receptionCreatedTime, receptionModifiedTime, doctorName, ownerName, animalName,
					animalType, animalAge, animalGender, animalWeight);
		}

	}

}
