package com.itwill.project.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;


public class Owner {
	
	public static final class Entity{
		
		public static final String TBL_OWNER = "OWNER";
		public static final String COL_OWNER_ID = "OWNER_ID";
		public static final String COL_OWNER_NAME = "OWNER_NAME";
		public static final String COL_OWNER_PHONE_NUMBER = "PHONE_NUMBER";
		public static final String COL_OWNER_ADDRESS = "ADDRESS";
		public static final String COL_OWNER_CREATED_TIME = "CREATED_TIME";
		public static final String COL_OWNER_MODIFIED_TIME = "MODIFIED_TIME";
		public static final String COL_OWNER_MEMO = "MEMO";
	}
	
	private Integer ownerid;
	private String ownerName;
	private String phoneNumber;
	private String address;
	private String ownerMemo;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;
	
	public Owner() {}

	public Owner(Integer ownerid, String ownerName, String phoneNumber, String address, String ownerMemo, LocalDateTime createdTime,LocalDateTime modifiedTime) {
		this.ownerid = ownerid;
		this.ownerName = ownerName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.ownerMemo = ownerMemo;
		this.createdTime = createdTime;
		this.modifiedTime = modifiedTime;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getOwnerMemo() {
		return ownerMemo;
	}

	public void setOwnerMemo(String ownerMemo) {
		this.ownerMemo = ownerMemo;
	}

	public Integer getOwnerId() {
		return ownerid;
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
		return "Owner [ownerid=" + ownerid + ", ownerName=" + ownerName + ", phoneNumber=" + phoneNumber + ", address="
				+ address + ", ownerMemo=" + ownerMemo + ", createdTime=" + createdTime + ", modifiedTime="
				+ modifiedTime + "]";
	}

	public static OwnerBuilder builder() {
		return new OwnerBuilder();
	}
	
	public static class OwnerBuilder {
		
		private Integer ownerId;
		private String ownerName;
		private String phoneNumber;
		private String address;
		private String ownerMemo;
		private LocalDateTime createdTime;
		private LocalDateTime modifiedTime;
		
		public OwnerBuilder() {}
		
		public OwnerBuilder ownerId(Integer ownerId) {
			this.ownerId = ownerId;
			return this;
		}
		
		public OwnerBuilder ownerName(String ownerName) {
			this.ownerName = ownerName;
			return this;
		}
		
		public OwnerBuilder phoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}
		
		public OwnerBuilder address(String address) {
			this.address = address;
			return this;
		}
		
		public OwnerBuilder ownerMemo(String ownerMemo) {
			this.ownerMemo = ownerMemo;
			return this;
		}
		
		public OwnerBuilder createdTime(LocalDateTime createdTime) {
			this.createdTime = createdTime;
			return this;
		}
		
		public OwnerBuilder createdTime(Timestamp createdTime) {
			this.createdTime = createdTime.toLocalDateTime();
			return this;
		}

		public OwnerBuilder modifiedTime(LocalDateTime modifiedTime) {
			this.modifiedTime = modifiedTime;
			return this;
		}
		
		public OwnerBuilder modifiedTime(Timestamp modifiedTime) {
			this.modifiedTime = modifiedTime.toLocalDateTime();
			return this;
		}
		
		public Owner build() {
			return new Owner(ownerId, ownerName, phoneNumber, address, ownerMemo, createdTime, modifiedTime);
		}
	}
}
