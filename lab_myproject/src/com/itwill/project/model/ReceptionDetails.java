package com.itwill.project.model;

public class ReceptionDetails {
	private Reception reception;
	private Animal animal;
	private Doctor doctor;
	private Owner owner;

	public ReceptionDetails() {
	}

	public ReceptionDetails(Reception reception, Animal animal, Doctor doctor, Owner owner) {
		this.reception = reception;
		this.animal = animal;
		this.doctor = doctor;
		this.owner = owner;
	}

	public Reception getReception() {
		return reception;
	}

	public void setReception(Reception reception) {
		this.reception = reception;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "ReceptionDetails [reception=" + reception + ", animal=" + animal + ", doctor=" + doctor + ", Owner="
				+ owner + "]";
	}
	
	
	public static ReceptionDetailsBuilder builder() {
		return new ReceptionDetailsBuilder();
	}
	
	public static class ReceptionDetailsBuilder{
		
		private Reception reception;
		private Animal animal;
		private Doctor doctor;
		private Owner owner;
		
		public ReceptionDetailsBuilder() {}
		
		public ReceptionDetailsBuilder(Reception reception, Animal animal, Doctor doctor,
				Owner owner) {
			
			this.reception = reception;
			this.animal = animal;
			this.doctor = doctor;
			this.owner = owner;
		}
		
		public ReceptionDetailsBuilder reception (Reception reception) {
			this.reception = reception;
			return this;
		}
		
		public ReceptionDetailsBuilder animal(Animal animal) {
			this.animal = animal;
			return this;
		}
		
		public ReceptionDetailsBuilder doctor(Doctor doctor) {
			this.doctor = doctor;
			return this;
		}
		
		public ReceptionDetailsBuilder owner(Owner owner) {
			this.owner = owner;
			return this;
		}
		
		public ReceptionDetails build() {
			return new ReceptionDetails(reception, animal, doctor, owner);
		}
		
	}
	
	

}
