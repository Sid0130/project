package com.itwill.project.model;

public class AnimalOwner {
	
	private int animalId;
    private String animalName;
    private int animalAge;
    private String animalType;
    private String animalGender;
    private double animalWeight;
    private String ownerName;
    private String ownerAdress;
    private String ownerPhone;
	
	
	public AnimalOwner() {}

	
	public AnimalOwner(int animalId, String animalName, int animalAge, String animalType,
			String animalGender, double animalWeight, String ownerName, String ownerPhone, String ownerAdress) {
		this.animalId = animalId;
		this.animalName = animalName;
		this.animalAge = animalAge;
		this.animalType = animalType;
		this.animalGender = animalGender;
		this.animalWeight = animalWeight;
		this.ownerName = ownerName;
		this.ownerAdress = ownerAdress;
		this.ownerPhone = ownerPhone;
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



	@Override
	public String toString() {
		return "AnimalOwner [animalId=" + animalId + ", animalName=" + animalName + ", animalAge=" + animalAge
				+ ", animalType=" + animalType + ", animalGender=" + animalGender + ", animalWeight=" + animalWeight
				+ ", ownerName=" + ownerName + ", ownerAdress=" + ownerAdress + ", ownerPhone=" + ownerPhone + "]";
	}

	public static AnimalOwnerBuilder builder() {
		return new AnimalOwnerBuilder();
	}

	public static class AnimalOwnerBuilder{
		
		private int animalId;
	    private String animalName;
	    private int animalAge;
	    private String animalType;
	    private String animalGender;
	    private double animalWeight;
	    private String ownerName;
	    private String ownerAdress;
	    private String ownerPhone;		
		
		public AnimalOwnerBuilder() {}
		
		public AnimalOwnerBuilder animalId(int animalId) {
			this.animalId = animalId;
			return this;
		}
		
		public AnimalOwnerBuilder animalName(String animalName) {
			this.animalName = animalName;
			return this;
		}
		
		public AnimalOwnerBuilder animalAge(int animalAge) {
			this.animalAge = animalAge;
			return this;
		}
		
		public AnimalOwnerBuilder animalType(String animalType) {
			this.animalType = animalType;
			return this;
		}
		public AnimalOwnerBuilder animalGender(String animalGender) {
			this.animalGender = animalGender;
			return this;
		}
		public AnimalOwnerBuilder animalWeight(Double animalWeight) {
			this.animalWeight = animalWeight;
			return this;
		}
		public AnimalOwnerBuilder ownerName(String ownerName) {
			this.ownerName = ownerName;
			return this;
		}
		
		public AnimalOwnerBuilder ownerAdress(String ownerAdress) {
			this.ownerAdress = ownerAdress;
			return this;
		}
		public AnimalOwnerBuilder ownerPhone(String ownerPhone) {
			this.ownerPhone = ownerPhone;
			return this;
		}
		
		public AnimalOwner build() {
			return new AnimalOwner(animalId, animalName, animalAge, animalType, animalGender,
					animalWeight, ownerName, ownerAdress, ownerPhone);
		}
		
	}

}
