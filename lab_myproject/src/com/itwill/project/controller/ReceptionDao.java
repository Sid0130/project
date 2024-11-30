package com.itwill.project.controller;

import static com.itwill.project.model.Animal.Entity.COL_ANIMAL_AGE;
import static com.itwill.project.model.Animal.Entity.COL_ANIMAL_GENDER;
import static com.itwill.project.model.Animal.Entity.COL_ANIMAL_ID;
import static com.itwill.project.model.Animal.Entity.COL_ANIMAL_NAME;
import static com.itwill.project.model.Animal.Entity.COL_ANIMAL_TYPE;
import static com.itwill.project.model.Animal.Entity.COL_ANIMAL_WEIGHT;
import static com.itwill.project.model.Animal.Entity.TBL_ANIMAL;
import static com.itwill.project.model.Doctor.Entity.COL_DOCTOR_ID;
import static com.itwill.project.model.Doctor.Entity.COL_DOCTOR_MODIFIED_TIME;
import static com.itwill.project.model.Doctor.Entity.COL_DOCTOR_NAME;
import static com.itwill.project.model.Doctor.Entity.TBL_DOCTOR;
import static com.itwill.project.model.Owner.Entity.COL_OWNER_ID;
import static com.itwill.project.model.Owner.Entity.COL_OWNER_NAME;
import static com.itwill.project.model.Owner.Entity.TBL_OWNER;
import static com.itwill.project.model.Reception.Entity.COL_RECEPTION_ANIMAL_ID;
import static com.itwill.project.model.Reception.Entity.COL_RECEPTION_APPOINTMENTDATE;
import static com.itwill.project.model.Reception.Entity.COL_RECEPTION_APPOINTMENTTIME;
import static com.itwill.project.model.Reception.Entity.COL_RECEPTION_CREATED_TIME;
import static com.itwill.project.model.Reception.Entity.COL_RECEPTION_DOCTOR_ID;
import static com.itwill.project.model.Reception.Entity.COL_RECEPTION_ID;
import static com.itwill.project.model.Reception.Entity.COL_RECEPTION_MODIFIED_TIME;
import static com.itwill.project.model.Reception.Entity.COL_RECEPTION_OWNER_ID;
import static com.itwill.project.model.Reception.Entity.COL_RECEPTION_STATUS;
import static com.itwill.project.model.Reception.Entity.TBL_RECEPTION;
import static com.itwill.project.oracle.OracleJdbc.PASSWORD;
import static com.itwill.project.oracle.OracleJdbc.URL;
import static com.itwill.project.oracle.OracleJdbc.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.itwill.project.model.ReceptionData;

import oracle.jdbc.OracleDriver;

public enum ReceptionDao {
	INSTANCE;

	ReceptionDao() {
		try {
			DriverManager.registerDriver(new OracleDriver()); // 오라클에 드라이버 등록

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null)	rs.close();
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void closeResources(Connection conn, Statement stmt) {
		closeResources(conn, stmt, null);
	}
	
	public ReceptionData getAppointmentFormResultSet(ResultSet rs) throws SQLException {
		// TODO: 예약시간
		int receptionId = rs.getInt(COL_RECEPTION_ID);
		String receptionAppointmentTime = rs.getString(COL_RECEPTION_APPOINTMENTTIME); // 예약 시간
		String receptionAppointmentDate = rs.getString(COL_RECEPTION_APPOINTMENTDATE);
		String receptionStatus = rs.getString(COL_RECEPTION_STATUS); // 예약 상태
		Timestamp receptionCreatedTime = rs.getTimestamp(COL_RECEPTION_CREATED_TIME);
		Timestamp receptionModifiedTime = rs.getTimestamp(COL_RECEPTION_MODIFIED_TIME);

		// 동물 정보
		String animalName = rs.getString(COL_ANIMAL_NAME);
		String animalGender = rs.getString(COL_ANIMAL_GENDER);
		String animalType = rs.getString(COL_ANIMAL_TYPE);
		Integer animalAge = rs.getInt(COL_ANIMAL_AGE);
		Double animalWeight = rs.getDouble(COL_ANIMAL_WEIGHT);
		int animalId = rs.getInt(COL_ANIMAL_ID); // 동물 ID

		// 보호자 정보
		String ownerName = rs.getString(COL_OWNER_NAME);
		int ownerId = rs.getInt(COL_OWNER_ID); // 보호자 ID

		// 의사 정보
		String doctorName = rs.getString(COL_DOCTOR_NAME);
		int doctorId = rs.getInt(COL_DOCTOR_ID); // 의사 ID

		// ReceptionData 객체 생성 및 반환
		return ReceptionData.builder().receptionId(receptionId).receptionAppointmentTime(receptionAppointmentTime)
				.receptionAppointmentDate(receptionAppointmentDate)
				.receptionStatus(receptionStatus).receptionCreatedTime(receptionCreatedTime)
				.receptionModifiedTime(receptionModifiedTime).animalName(animalName).animalGender(animalGender)
				.animalType(animalType).animalAge(animalAge).animalWeight(animalWeight).animalId(animalId) // 동물 ID 추가
				.ownerName(ownerName).ownerId(ownerId) // 보호자 ID 추가
				.doctorName(doctorName).doctorId(doctorId) // 의사 ID 추가
				.build();
	}
	
	public ReceptionData gettestDataFormResultSet(ResultSet rs) throws SQLException {
		
	int receptionId = rs.getInt(COL_RECEPTION_ID);
	String receptionAppointmentTime = rs.getString(COL_RECEPTION_APPOINTMENTTIME); //예약시간
	String receptionAppointmentDate = rs.getString(COL_RECEPTION_APPOINTMENTDATE);
	String receptionStatus = rs.getString(COL_RECEPTION_STATUS); //예약
	Timestamp receptionCreatedTime = rs.getTimestamp(COL_RECEPTION_CREATED_TIME);
	Timestamp receptionModifiedTime = rs.getTimestamp(COL_RECEPTION_MODIFIED_TIME);
	
		return ReceptionData.builder()
				.receptionId(receptionId)
				.receptionAppointmentTime(receptionAppointmentTime)
				.receptionAppointmentDate(receptionAppointmentDate)
				.receptionStatus(receptionStatus)
				.receptionCreatedTime(receptionCreatedTime)
				.receptionModifiedTime(receptionModifiedTime)
				.build();
	}
	
	
	
	public ReceptionData getReceptionDataFormResultSet(ResultSet rs) throws SQLException {
	
	int receptionId = rs.getInt(COL_RECEPTION_ID);
	String receptionAppointmentTime = rs.getString(COL_RECEPTION_APPOINTMENTTIME); //예약시간
	String receptionAppointmentDate = rs.getString(COL_RECEPTION_APPOINTMENTDATE);
	String receptionStatus = rs.getString(COL_RECEPTION_STATUS); //예약
	Timestamp receptionCreatedTime = rs.getTimestamp(COL_RECEPTION_CREATED_TIME);
	Timestamp receptionModifiedTime = rs.getTimestamp(COL_RECEPTION_MODIFIED_TIME);
	
	String animalName = rs.getString(COL_ANIMAL_NAME);
	String animalType = rs.getString(COL_ANIMAL_TYPE);
	
	String ownerName = rs.getString(COL_OWNER_NAME);
	String doctorName = rs.getString(COL_DOCTOR_NAME);
	
		return ReceptionData.builder()
				.receptionId(receptionId)
				.receptionAppointmentDate(receptionAppointmentTime)
				.receptionAppointmentTime(receptionAppointmentDate)
				.receptionStatus(receptionStatus)
				.receptionCreatedTime(receptionCreatedTime)
				.receptionModifiedTime(receptionModifiedTime)
				.animalName(animalName)
				.animalType(animalType)
				.ownerName(ownerName)
				.doctorName(doctorName)
				.build();
	}
	
	private static final String SQL_JOIN_ALL = String.format(
			"select r.%s, r.%s, r.%s,r.%s, r.%s, r.%s, a.%s, a.%s, o.%s, d.%s "
			+ "from %s r "
			+ "join %s a on r.%s = a.%s "
			+ "join %s o on r.%s = o.%s "
			+ "join %s d on r.%s = d.%s "
			+ "order by r.%s desc", 
			COL_RECEPTION_ID,COL_RECEPTION_APPOINTMENTTIME, COL_RECEPTION_APPOINTMENTDATE,COL_RECEPTION_STATUS,
			COL_RECEPTION_CREATED_TIME, COL_DOCTOR_MODIFIED_TIME,
			COL_ANIMAL_NAME, COL_ANIMAL_TYPE, COL_OWNER_NAME, COL_DOCTOR_NAME,
			
			TBL_RECEPTION,
			
			TBL_ANIMAL, COL_RECEPTION_ANIMAL_ID, COL_ANIMAL_ID,
			TBL_OWNER, COL_RECEPTION_OWNER_ID, COL_OWNER_ID,
			TBL_DOCTOR, COL_RECEPTION_DOCTOR_ID, COL_DOCTOR_ID,
			
			COL_RECEPTION_ID);
	
	public List<ReceptionData> read(){
		List<ReceptionData> receptiondata = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_JOIN_ALL);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				ReceptionData rd = getReceptionDataFormResultSet(rs);
				receptiondata.add(rd);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		
		return receptiondata;
	
	}
	
	
	
	//아이디 조회
	private static final String SQL_ANIMAL_ID = String.format(
			"select %s from %s where %s = ? ", 
			COL_ANIMAL_ID, TBL_ANIMAL, COL_ANIMAL_NAME);
			
	public int getAnimalIdByName(String animalName) {
		int result = -1 ;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_ANIMAL_ID);
			stmt.setString(1, animalName);
			rs = stmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(COL_ANIMAL_ID);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		
		return result;
	}
		
	
	private static final String SQL_OWNER_ID = String.format(
	        "select %s from %s where %s = ? ",
	        COL_OWNER_ID, TBL_OWNER, COL_OWNER_NAME);

	public int getOwnerIdByName(String ownerName) {
	    int result = -1; // 기본값 -1 설정
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        conn = DriverManager.getConnection(URL, USER, PASSWORD);
	        stmt = conn.prepareStatement(SQL_OWNER_ID);
	        stmt.setString(1, ownerName); // 
	        rs = stmt.executeQuery();
	        if (rs.next()) {
	            result = rs.getInt(COL_OWNER_ID); 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        closeResources(conn, stmt, rs);
	    }

	    return result;
	}
	
	private static final String SQL_DOCTOR_ID = String.format(
	        "select %s from %s where %s = ? ",
	        COL_DOCTOR_ID, TBL_DOCTOR, COL_DOCTOR_NAME);

	public int getDoctorIdByName(String doctorName) {
	    int result = -1; // 기본값 -1 설정
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        conn = DriverManager.getConnection(URL, USER, PASSWORD);
	        stmt = conn.prepareStatement(SQL_DOCTOR_ID);
	        stmt.setString(1, doctorName.trim()); 
	        rs = stmt.executeQuery();
	        if (rs.next()) {
	            result = rs.getInt(COL_DOCTOR_ID); 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        closeResources(conn, stmt, rs);
	    }

	    return result;
	}

	
	private static final String SQL_INSERT_RECEPTION = String.format(
	        "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s) " +
	        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
	        TBL_RECEPTION,
	        COL_RECEPTION_ANIMAL_ID, COL_RECEPTION_DOCTOR_ID, COL_RECEPTION_OWNER_ID, 
	        COL_RECEPTION_APPOINTMENTTIME, COL_RECEPTION_APPOINTMENTDATE, COL_RECEPTION_STATUS,  
	        COL_RECEPTION_CREATED_TIME, COL_RECEPTION_MODIFIED_TIME);

	public int createReception(ReceptionData reception) {
	    int result = 0; // 기본값 0, 성공하면 1 반환
	    Connection conn = null;
	    PreparedStatement stmt = null;

	    try {
	        conn = DriverManager.getConnection(URL, USER, PASSWORD);
	        stmt = conn.prepareStatement(SQL_INSERT_RECEPTION);

	        // 쿼리 파라미터 설정
	        stmt.setInt(1, reception.getAnimalId()); // 동물 ID
	        stmt.setInt(2, reception.getDoctorId()); // 의사 ID
	        stmt.setInt(3, reception.getOwnerId());  // 보호자 ID
	        stmt.setString(4, reception.getReceptionAppointmentTime()); // 예약 시간
	        stmt.setString(5, reception.getReceptionAppointmentDate());
	        stmt.setString(6, reception.getReceptionStatus()); // 예약 상태
	        stmt.setTimestamp(7, new Timestamp(System.currentTimeMillis())); // 생성 시간 (현재 시간)
	        stmt.setTimestamp(8, new Timestamp(System.currentTimeMillis())); // 수정 시간 (현재 시간)

	        // 쿼리 실행
	        result = stmt.executeUpdate(); // 성공하면 1 반환, 실패하면 0 반환

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        closeResources(conn, stmt, null);
	    }

	    return result;
	}
	
	
	

}
