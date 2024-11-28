package com.itwill.project.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;


public class AnimalOwnerUpdate {

	private int animalId;
    private String animalName;
    private int animalAge;
    private String animalType;
    private String animalGender;
    private double animalWeight;
    private String animalMemo;
    private String ownerName;
    private String ownerAdress;
    private String ownerPhone;
    private String ownerMemo;
	private LocalDateTime animalCreatedTime;
	private LocalDateTime animalModifiedTime;
	private LocalDateTime ownerCreatedTime;
	private LocalDateTime ownerModifiedTime;
	
	public AnimalOwnerUpdate() {}

	

	public AnimalOwnerUpdate(int animalId, String animalName, int animalAge, String animalType, String animalGender,
			double animalWeight, String animalMemo, String ownerName, String ownerAdress, String ownerPhone,
			String ownerMemo, LocalDateTime animalCreatedTime, LocalDateTime animalModifiedTime,
			LocalDateTime ownerCreatedTime, LocalDateTime ownerModifiedTime) {
		this.animalId = animalId;
		this.animalName = animalName;
		this.animalAge = animalAge;
		this.animalType = animalType;
		this.animalGender = animalGender;
		this.animalWeight = animalWeight;
		this.animalMemo = animalMemo;
		this.ownerName = ownerName;
		this.ownerAdress = ownerAdress;
		this.ownerPhone = ownerPhone;
		this.ownerMemo = ownerMemo;
		this.animalCreatedTime = animalCreatedTime;
		this.animalModifiedTime = animalModifiedTime;
		this.ownerCreatedTime = ownerCreatedTime;
		this.ownerModifiedTime = ownerModifiedTime;
	}



	public String getAnimalName() {
		return animalName;
	}

	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}

	public int getAnimalAge() {
		return animalAge;
	}

	public void setAnimalAge(int animalAge) {
		this.animalAge = animalAge;
	}

	public String getAnimalType() {
		return animalType;
	}

	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}

	public String getAnimalGender() {
		return animalGender;
	}

	public void setAnimalGender(String animalGender) {
		this.animalGender = animalGender;
	}

	public double getAnimalWeight() {
		return animalWeight;
	}

	public void setAnimalWeight(double animalWeight) {
		this.animalWeight = animalWeight;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public int getAnimalId() {
		return animalId;
	}
	

	public String getOwnerAdress() {
		return ownerAdress;
	}


	public void setOwnerAdress(String ownerAdress) {
		this.ownerAdress = ownerAdress;
	}


	public String getOwnerPhone() {
		return ownerPhone;
	}


	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}



	public String getAnimalMemo() {
		return animalMemo;
	}



	public void setAnimalMemo(String animalMemo) {
		this.animalMemo = animalMemo;
	}



	public String getOwnerMemo() {
		return ownerMemo;
	}



	public void setOwnerMemo(String ownerMemo) {
		this.ownerMemo = ownerMemo;
	}



	public LocalDateTime getAnimalCreatedTime() {
		return animalCreatedTime;
	}



	public void setAnimalCreatedTime(LocalDateTime animalCreatedTime) {
		this.animalCreatedTime = animalCreatedTime;
	}



	public LocalDateTime getAnimalModifiedTime() {
		return animalModifiedTime;
	}



	public void setAnimalModifiedTime(LocalDateTime animalModifiedTime) {
		this.animalModifiedTime = animalModifiedTime;
	}



	public LocalDateTime getOwnerCreatedTime() {
		return ownerCreatedTime;
	}



	public void setOwnerCreatedTime(LocalDateTime ownerCreatedTime) {
		this.ownerCreatedTime = ownerCreatedTime;
	}



	public LocalDateTime getOwnerModifiedTime() {
		return ownerModifiedTime;
	}



	public void setOwnerModifiedTime(LocalDateTime ownerModifiedTime) {
		this.ownerModifiedTime = ownerModifiedTime;
	}




	@Override
	public String toString() {
		return "AnimalOwnerUpdate [animalId=" + animalId + ", animalName=" + animalName + ", animalAge=" + animalAge
				+ ", animalType=" + animalType + ", animalGender=" + animalGender + ", animalWeight=" + animalWeight
				+ ", animalMemo=" + animalMemo + ", ownerName=" + ownerName + ", ownerAdress=" + ownerAdress
				+ ", ownerPhone=" + ownerPhone + ", ownerMemo=" + ownerMemo + ", animalCreatedTime=" + animalCreatedTime
				+ ", animalModifiedTime=" + animalModifiedTime + ", ownerCreatedTime=" + ownerCreatedTime
				+ ", ownerModifiedTime=" + ownerModifiedTime + "]";
	}



	public static AnimalOwnerUpdateBuilder builder() {
		return new AnimalOwnerUpdateBuilder();
	}

	public static class AnimalOwnerUpdateBuilder{
		
		private int animalId;
	    private String animalName;
	    private int animalAge;
	    private String animalType;
	    private String animalGender;
	    private double animalWeight;
	    private String animalMemo;
	    private String ownerName;
	    private String ownerAdress;
	    private String ownerPhone;
	    private String ownerMemo;
		private LocalDateTime animalCreatedTime;
		private LocalDateTime animalModifiedTime;
		private LocalDateTime ownerCreatedTime;
		private LocalDateTime ownerModifiedTime;
		
		public AnimalOwnerUpdateBuilder() {}
		
		public AnimalOwnerUpdateBuilder animalId(int animalId) {
			this.animalId = animalId;
			return this;
		}
		
		public AnimalOwnerUpdateBuilder animalName(String animalName) {
			this.animalName = animalName;
			return this;
		}
		
		public AnimalOwnerUpdateBuilder animalAge(int animalAge) {
			this.animalAge = animalAge;
			return this;
		}
		
		public AnimalOwnerUpdateBuilder animalType(String animalType) {
			this.animalType = animalType;
			return this;
		}
		public AnimalOwnerUpdateBuilder animalGender(String animalGender) {
			this.animalGender = animalGender;
			return this;
		}
		public AnimalOwnerUpdateBuilder animalWeight(Double animalWeight) {
			this.animalWeight = animalWeight;
			return this;
		}
		public AnimalOwnerUpdateBuilder animalMemo(String animalMemo) {
			this.animalMemo = animalMemo;
			return this;
		}
		
		public AnimalOwnerUpdateBuilder ownerName(String ownerName) {
			this.ownerName = ownerName;
			return this;
		}
		
		public AnimalOwnerUpdateBuilder ownerAdress(String ownerAdress) {
			this.ownerAdress = ownerAdress;
			return this;
		}
		public AnimalOwnerUpdateBuilder ownerPhone(String ownerPhone) {
			this.ownerPhone = ownerPhone;
			return this;
		}
		
		public AnimalOwnerUpdateBuilder ownerMemo(String ownerMemo) {
			this.ownerMemo = ownerMemo;
			return this;
		}
		
		public AnimalOwnerUpdateBuilder animalCreatedTime(LocalDateTime animalCreatedTime) {
			this.animalCreatedTime = animalCreatedTime;
			return this;
		}
		public AnimalOwnerUpdateBuilder animalCreatedTime(Timestamp animalCreatedTime) { 
			if (animalCreatedTime != null) {
				this.animalCreatedTime = animalCreatedTime.toLocalDateTime();
			}
			return this;
		}
		
		
		public AnimalOwnerUpdateBuilder animalModifiedTime(LocalDateTime animalModifiedTime) {
			this.animalModifiedTime = animalModifiedTime;
			return this;
		}
		
		public AnimalOwnerUpdateBuilder animalModifiedTime(Timestamp animalModifiedTime) { 
			if (animalModifiedTime != null) {
				this.animalModifiedTime = animalModifiedTime.toLocalDateTime();
			}
			return this;
		}
		
		
		public AnimalOwnerUpdateBuilder ownerCreatedTime(LocalDateTime ownerCreatedTime) {
			this.ownerCreatedTime = ownerCreatedTime;
			return this;
		}
		
		public AnimalOwnerUpdateBuilder ownerCreatedTime(Timestamp ownerCreatedTime) { 
			if (ownerCreatedTime != null) {
				this.ownerCreatedTime = ownerCreatedTime.toLocalDateTime();
			}
			return this;
		}
		
		
		public AnimalOwnerUpdateBuilder ownerModifiedTime(LocalDateTime ownerModifiedTime) {
			this.ownerModifiedTime = ownerModifiedTime;
			return this;
		}
		
		public AnimalOwnerUpdateBuilder ownerModifiedTime(Timestamp ownerModifiedTime) { 
			if (ownerModifiedTime != null) {
				this.animalModifiedTime = ownerModifiedTime.toLocalDateTime();
			}
			return this;
		}
		
		
		
		public AnimalOwnerUpdate builder() {
			return new AnimalOwnerUpdate (animalId, animalName, animalAge, animalType, animalGender, 
					animalWeight, animalMemo, ownerName, ownerAdress, ownerPhone, ownerMemo, 
					animalCreatedTime, animalModifiedTime, ownerCreatedTime, ownerModifiedTime);
		}
		
	}

}
