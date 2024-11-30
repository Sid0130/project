package com.itwill.project.model;

import java.sql.Timestamp;

public class Reception {

	public static final class Entity {
		
		public static final String TBL_RECEPTION = "RECEPTION";
		public static final String COL_RECEPTION_ID = "RECEPTION_ID";
		public static final String COL_RECEPTION_ANIMAL_ID = "ANIMALS_ID";
		public static final String COL_RECEPTION_DOCTOR_ID = "DOCTORS_ID";
		public static final String COL_RECEPTION_OWNER_ID = "OWNERS_ID";
		public static final String COL_RECEPTION_APPOINTMENTTIME = "APPOINTMENT_TIME";
		public static final String COL_RECEPTION_APPOINTMENTDATE = "APPOINTMENT_DATE";
		public static final String COL_RECEPTION_STATUS = "RECEPTION_STATUS";
		public static final String COL_RECEPTION_CREATED_TIME = "CREATED_TIME";
		public static final String COL_RECEPTION_MODIFIED_TIME = "MODIFIED_TIME";
	}
	
	private Integer id;
	private Integer animalId; 
	private Integer doctorId; 
	private Integer ownerId; 
	private Timestamp appointmentTime;
	private Timestamp appointmentDate; 
	private String receptionStatus; 
	private Timestamp createdTime; 
	private Timestamp modifiedTime; 

	public Reception() {
	}

	public Reception(int id, int animalId, int doctorId, int ownerId, Timestamp appointmentTime, Timestamp appointmentDate, String receptionStatus,
			Timestamp createdTime, Timestamp modifiedTime) {
		this.id = id;
		this.animalId = animalId;
		this.doctorId = doctorId;
		this.ownerId = ownerId;
		this.appointmentTime = appointmentTime;
		this.appointmentDate = appointmentDate;
		this.receptionStatus = receptionStatus;
		this.createdTime = createdTime;
		this.modifiedTime = modifiedTime;
	}

	public Timestamp getAppointmentTime() {
		return appointmentTime;
	}

	public Timestamp getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Timestamp appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public void setAppointmentTime(Timestamp appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getReceptionStatus() {
		return receptionStatus;
	}

	public void setReceptionStatus(String receptionStatus) {
		this.receptionStatus = receptionStatus;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Timestamp getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public int getId() {
		return id;
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
		return "Reception [id=" + id + ", animalId=" + animalId + ", doctorId=" + doctorId + ", ownerId=" + ownerId
				+ ", appointmentTime=" + appointmentTime + ", appointmentDate=" + appointmentDate + ", receptionStatus="
				+ receptionStatus + ", createdTime=" + createdTime + ", modifiedTime=" + modifiedTime + "]";
	}

	public static ReceptionBuilder builder() {
		return new ReceptionBuilder();
	}

	public static class ReceptionBuilder {
		private Integer id;
		private Integer animalId;
		private Integer doctorId;
		private Integer ownerId;
		private Timestamp appointmentTime;
		private Timestamp appointmentDate; 
		private String receptionStatus;
		private Timestamp createdTime;
		private Timestamp modifiedTime;

		public ReceptionBuilder() {
		}

		public ReceptionBuilder(Integer id, Integer animalId, Integer doctorId, Integer ownerId,
				Timestamp appointmentTime,Timestamp appointmentDate ,String receptionStatus, Timestamp createdTime, Timestamp modifiedTime) {
			this.id = id;
			this.animalId = animalId;
			this.doctorId = doctorId;
			this.ownerId = ownerId;
			this.appointmentTime = appointmentTime;
			this.appointmentDate = appointmentDate;
			this.receptionStatus = receptionStatus;
			this.createdTime = createdTime;
			this.modifiedTime = modifiedTime;
		}

		public ReceptionBuilder id(Integer id) {
			this.id = id;
			return this;
		}
		
		public ReceptionBuilder animalId(Integer animalId) {
			this.animalId = animalId;
			return this;
		}
		
		public ReceptionBuilder doctorId(Integer doctorId) {
			this.doctorId = doctorId;
			return this;
		}
		
		public ReceptionBuilder ownerId(Integer ownerId) {
			this.ownerId = ownerId;
			return this;
		}
		
		public ReceptionBuilder appointmentTime(Timestamp appointmentTime) {
			this.appointmentTime = appointmentTime;
			return this;
		}
		public ReceptionBuilder appointmentDate(Timestamp appointmentDate) {
			this.appointmentDate = appointmentDate;
			return this;
		}
		
		public ReceptionBuilder receptionStatus(String receptionStatus) {
			this.receptionStatus = receptionStatus;
			return this;
		}
		
		
		public ReceptionBuilder createdTime(Timestamp createdTime) {
			this.createdTime = createdTime;
			return this;
		}
		
		public ReceptionBuilder modifiedTime(Timestamp modifiedTime) {
			this.modifiedTime = modifiedTime;
			return this;
		}
		
		public Reception build() {
			return new Reception(id, animalId, doctorId, ownerId, appointmentTime,appointmentDate ,receptionStatus, createdTime,
					modifiedTime);
		}

	}

}
