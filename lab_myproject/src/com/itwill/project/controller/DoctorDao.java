package com.itwill.project.controller;

import static com.itwill.project.model.Doctor.Entity.*;
import static com.itwill.project.oracle.OracleJdbc.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.itwill.project.model.Doctor;
import static com.itwill.project.model.Reception.Entity.*;
import oracle.jdbc.OracleDriver;

public enum DoctorDao {
	INSTANCE;

	
	DoctorDao() {
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
	
	// TODO: 의사 테이블 시작
	private static final Doctor getDoctorFromResultSet(ResultSet rs) throws SQLException {
		int doctorId = rs.getInt(COL_DOCTOR_ID);
		String doctorName = rs.getString(COL_DOCTOR_NAME);
		String doctorSpecialty = rs.getString(COL_DOCTOR_SPECIALTY);
		String doctorPhone = rs.getString(COL_DOCTOR_PHONE_NUMBER);
		String doctorMemo = rs.getString(COL_DOCTOR_MEMO);
		Timestamp createdTime = rs.getTimestamp(COL_DOCTOR_CREATED_TIME);
		Timestamp modifiedTime = rs.getTimestamp(COL_DOCTOR_MODIFIED_TIME);
		
		return Doctor.builder()
				.id(doctorId)
				.name(doctorName)
				.specialty(doctorSpecialty)
				.phoneNumber(doctorPhone)
				.memo(doctorMemo)
				.createdTime(createdTime)
				.modifiedTime(modifiedTime)
				.build();
	}
	
	//의사 등록
	private static final String SQL_DOCTOR_INSERT = String.format(
			"insert into %s (%s, %s, %s, %s, %s) values (?, ?, ?, systimestamp, systimestamp)", 
			TBL_DOCTOR, COL_DOCTOR_NAME, COL_DOCTOR_SPECIALTY,
			COL_DOCTOR_PHONE_NUMBER,COL_DOCTOR_CREATED_TIME,COL_DOCTOR_MODIFIED_TIME);
	
	public int createDoctor(Doctor doctor) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_DOCTOR_INSERT);
			
			stmt.setString(1, doctor.getName());
			stmt.setString(2, doctor.getSpecialty());
			stmt.setString(3, doctor.getPhoneNumber());
			
			
			result = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally  {
			closeResources(conn, stmt);
		}
					
		return result;
		
	}
	
	//의사 선택 
	private static final String SQL_SELECT_DOCTOR = String.format(
			"select * from %s order by %s desc", 
			TBL_DOCTOR, COL_DOCTOR_ID);
	
	public List<Doctor> read(){
		List<Doctor> doctors = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareCall(SQL_SELECT_DOCTOR);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Doctor doctor = getDoctorFromResultSet(rs);
				doctors.add(doctor);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		return doctors;
	}
	
	private static final String SQL_DELETE_DOCTOR = String.format(
			"delete from %s where %s = ?",
			TBL_DOCTOR, COL_DOCTOR_ID);
	public int deleteDoctor(Integer id){
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_DELETE_DOCTOR);
			stmt.setInt(1, id);
			
			result = stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt);
		}
		return result;
	}
	
	
	// 의사 업데이트
	private static final String SQL_UPDATE_DOCTOR = String.format(
			"update %s set "
			+ "%s = ?, %s = ?, %s = ?, %s = ?, %s = systimestamp "
			+ "where %s = ?",
			TBL_DOCTOR, 
			COL_DOCTOR_NAME, COL_DOCTOR_SPECIALTY, COL_DOCTOR_PHONE_NUMBER, COL_DOCTOR_MEMO,COL_DOCTOR_MODIFIED_TIME,
			COL_DOCTOR_ID);
	
	public int updateDoctor(Doctor doctor) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_UPDATE_DOCTOR);
			
			stmt.setString(1, doctor.getName());
			stmt.setString(2, doctor.getSpecialty());
			stmt.setString(3, doctor.getPhoneNumber());
			stmt.setString(4, doctor.getMemo());
			stmt.setInt(5, doctor.getId());
			
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt);
		}
		
		return result;
	}
	
	private static final String SQL_SELECT_BY_DOCTOR_ID = String.format(
			"select * from %s where %s = ?", 
			TBL_DOCTOR, COL_DOCTOR_ID);
	
	public Doctor readDoctor(Integer id) {
		Doctor doctor = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_SELECT_BY_DOCTOR_ID);
			stmt.setInt(1, id);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				doctor = getDoctorFromResultSet(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		
		return doctor;
		
	}
	
	private static final Doctor getSlectFromResultSet(ResultSet rs) throws SQLException {
		String doctorName = rs.getString(COL_DOCTOR_NAME);
		String doctorSpecialty = rs.getString(COL_DOCTOR_SPECIALTY);
		
		return Doctor.builder()
				.name(doctorName)
				.specialty(doctorSpecialty)
				.build();
	}
	private static final String SQL_SELECT_DOCTOR_NAME = String.format(
			"select %s, %s from %s ", 
			COL_DOCTOR_NAME, COL_DOCTOR_SPECIALTY ,TBL_DOCTOR);
	
	public List<Doctor> readDoctorInfo() {
		List<Doctor> doctor = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_SELECT_DOCTOR_NAME);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Doctor doctorInfo = getSlectFromResultSet(rs);
				doctor.add(doctorInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		
		return doctor;
		
	}
		
	
}
