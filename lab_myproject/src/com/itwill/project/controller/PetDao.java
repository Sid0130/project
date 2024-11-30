package com.itwill.project.controller;




import static com.itwill.project.model.Animal.Entity.*;
import static com.itwill.project.model.Owner.Entity.*;
import static com.itwill.project.oracle.OracleJdbc.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.itwill.project.model.Animal;
import com.itwill.project.model.AnimalOwner;
import com.itwill.project.model.AnimalOwnerUpdate;
import com.itwill.project.model.Doctor;
import com.itwill.project.model.Owner;

import oracle.jdbc.OracleDriver;

public enum PetDao {
	INSTANCE;

	PetDao() {
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
	
	private AnimalOwner getPetAnimalFromResultSet(ResultSet rs) throws SQLException {
		int id = rs.getInt(COL_ANIMAL_ID);
		String animalName = rs.getString(COL_ANIMAL_NAME);
		String animalType = rs.getString(COL_ANIMAL_TYPE);
		int animalAge = rs.getInt(COL_ANIMAL_AGE);
		String animalGender = rs.getString(COL_ANIMAL_GENDER);
		Double animalWeight = rs.getDouble(COL_ANIMAL_WEIGHT);
		String ownerName = rs.getString(COL_OWNER_NAME);
	
		return AnimalOwner.builder()
				.animalId(id)
				.animalName(animalName)
				.animalType(animalType)
				.animalAge(animalAge)
				.animalGender(animalGender)
				.animalWeight(animalWeight)
				.ownerName(ownerName)
				.build();
	}
	private AnimalOwnerUpdate getPetAoFromResultSet(ResultSet rs) throws SQLException {
		String animalName = rs.getString(COL_ANIMAL_NAME);
		String animalType = rs.getString(COL_ANIMAL_TYPE);
		int animalAge = rs.getInt(COL_ANIMAL_AGE);
		String animalGender = rs.getString(COL_ANIMAL_GENDER);
		Double animalWeight = rs.getDouble(COL_ANIMAL_WEIGHT);
		String animalMemo = rs.getString(COL_ANIMAL_MEMO);
		String ownerName = rs.getString(COL_OWNER_NAME);
		String ownerPhone = rs.getString(COL_OWNER_PHONE_NUMBER);
		String ownerAddress = rs.getString(COL_OWNER_ADDRESS);
		String ownerMemo = rs.getString(COL_OWNER_MEMO);
		Timestamp ownerCreatedTime = rs.getTimestamp(COL_OWNER_CREATED_TIME);
		Timestamp ownerModifiedTime = rs.getTimestamp(COL_OWNER_MODIFIED_TIME);
		Timestamp animalCreatedTime = rs.getTimestamp(COL_ANIMAL_CREATED_TIME);
		Timestamp animalModifiedTime = rs.getTimestamp(COL_ANIMAL_MODIFIED_TIME);
		return AnimalOwnerUpdate.builder()
				.animalName(animalName)
				.animalType(animalType)
				.animalAge(animalAge)
				.animalGender(animalGender)
				.animalWeight(animalWeight)
				.animalMemo(animalMemo)
				.ownerName(ownerName)
				.ownerAdress(ownerAddress)
				.ownerPhone(ownerPhone)
				.ownerMemo(ownerMemo)
				.animalCreatedTime(animalCreatedTime)
				.animalModifiedTime(animalModifiedTime)
				.ownerCreatedTime(ownerCreatedTime)
				.ownerModifiedTime(ownerModifiedTime)
				.build();
	}	
	
	// 동물 사람 조인.
	private static final String SQL_JOIN_ANIMAL = String.format(
			"select a.%s, a.%s, a.%s, a.%s, a.%s, a.%s, a.%s, o.%s "
			+ "from %s a "
			+ "join %s o on a.%s = o.%s "
			+ "order by %s desc",
			
			COL_ANIMAL_ID, COL_ANIMAL_NAME, COL_ANIMAL_TYPE, COL_ANIMAL_AGE, COL_ANIMAL_GENDER,COL_ANIMAL_WEIGHT,
			COL_ANIMAL_MEMO,
			COL_OWNER_NAME,
			
			TBL_ANIMAL,TBL_OWNER,
			COL_ANIMAL_OWNERID, COL_OWNER_ID,
			COL_ANIMAL_ID);
					
			public List<AnimalOwner> readAnimalOwner(){
			List<AnimalOwner> animalOwner = new ArrayList<>();
			
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				conn = DriverManager.getConnection(URL, USER , PASSWORD);
				stmt = conn.prepareStatement(SQL_JOIN_ANIMAL);
				rs = stmt.executeQuery();
				
				while (rs.next()) {
					AnimalOwner ao = getPetAnimalFromResultSet(rs);
					animalOwner.add(ao);	
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeResources(conn, stmt, rs);
			}
			
			return animalOwner;
		}
		
		
			
			
	// 데이터 베이스 동물 추가
	private static final String SQL_ANIMAL_INSERT = String.format(
			
			"insert into %s (%s, %s, %s, %s, %s, %s, %s, %s, %s) "
			+ "values (?, ?, ? ,? ,? ,? , ?, systimestamp,systimestamp)",
			
			TBL_ANIMAL,
			COL_ANIMAL_NAME,
			COL_ANIMAL_TYPE, 
			COL_ANIMAL_AGE ,
			COL_ANIMAL_GENDER,
			COL_ANIMAL_WEIGHT,
			COL_ANIMAL_MEMO,
			COL_ANIMAL_OWNERID,
			COL_ANIMAL_CREATED_TIME,
			COL_ANIMAL_MODIFIED_TIME
			);
	
	public int createAnimal(Animal animal, Integer ownerId) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_ANIMAL_INSERT);
			
			stmt.setString(1, animal.getAnimalName());
			stmt.setString(2, animal.getAnimalType());
			stmt.setInt(3, animal.getAge());
			stmt.setString(4, animal.getGender());
			stmt.setDouble(5, animal.getWeight());
			stmt.setString(6, animal.getMemo());
			
			if (ownerId == null) {
	            throw new Exception("Owner ID cannot be null");
	        }
	        stmt.setInt(7, ownerId);  

			result = stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt);
		}
		return result;
	}
	
	// 데이터베이스 사람 추가
	private static final String SQL_QWNER_INSERT = String.format(
			"insert into %s "
			+ "(%s, %s, %s, %s, %s, %s) "
			+ "values "
			+ "(?, ?, ?, systimestamp, systimestamp , ?)", 
			
			TBL_OWNER,
			COL_OWNER_NAME, COL_OWNER_PHONE_NUMBER, COL_OWNER_ADDRESS, COL_OWNER_CREATED_TIME,
			COL_OWNER_MODIFIED_TIME, COL_OWNER_MEMO);
				
	
		public int createOwner(Owner owner) {
			int result = 0;

			Connection conn = null;
			PreparedStatement stmt = null;

			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				stmt = conn.prepareStatement(SQL_QWNER_INSERT);

				stmt.setString(1, owner.getOwnerName());
				stmt.setString(2, owner.getPhoneNumber());
				stmt.setString(3, owner.getAddress());
				stmt.setString(4, owner.getOwnerMemo());
				result = stmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeResources(conn, stmt);
			}
			return result;
		}	
		
		// 사람 아이디 기본값 
		public int getLastInsertedOwnerId() {
			
		    int ownerId = -1;  
		    String SQL = "SELECT MAX(owner_id) FROM owner";

		    Connection conn = null;
		    PreparedStatement stmt = null;
		    ResultSet rs = null;

		    try {
		        conn = DriverManager.getConnection(URL, USER, PASSWORD);
		        stmt = conn.prepareStatement(SQL);
		        rs = stmt.executeQuery();

		   
		        if (rs.next()) {
		            ownerId = rs.getInt(1); // 첫 번째 컬럼의 값을 ownerId로 저장
		        }        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        closeResources(conn, stmt, rs);  
		    }

		    return ownerId;  // 마지막으로 삽입된 owner_id를 반환
		}
		
		
		// animal 테이블 1행 삭제 
		private static final String SQL_DELETE_OWNER = String.format(
			    "delete from %s where %s = ?",
			    TBL_OWNER, COL_OWNER_ID);

			public int deleteOwner(Integer ownerId){
			    int result = 0;

			    Connection conn = null;
			    PreparedStatement stmt = null;

			    try {
			        conn = DriverManager.getConnection(URL, USER, PASSWORD);

			        // 먼저 owner 삭제
			        stmt = conn.prepareStatement(SQL_DELETE_OWNER);
			        stmt.setInt(1, ownerId);

			        result = stmt.executeUpdate();
			    } catch (SQLException e) {
			        e.printStackTrace();
			    } finally {
			        closeResources(conn, stmt);
			    }

			    return result;
			}
		// 동물 삭제 메서드
		private static final String SQL_DELETE_ANIMAL = String.format(
				"delete from %s where %s = ?",
				TBL_ANIMAL, COL_ANIMAL_OWNERID);
		public int delete(Integer id){
			int result = 0;
			
			Connection conn = null;
			PreparedStatement stmt = null;
			
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				stmt = conn.prepareStatement(SQL_DELETE_ANIMAL);
				stmt.setInt(1, id);
				
				result = stmt.executeUpdate();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeResources(conn, stmt);
			}
			return result;
		}
		
		
		//업데이트할 고객 목록
		private static final String SQL_SELECT_BY_ID = String.format(
				"select * from %s a "
				+ " join %s o "
				+ "on a.%s = o.%s "
				+ "where a.%s = ? "
				+ "order by a.%s desc",
				TBL_ANIMAL, TBL_OWNER,
				COL_ANIMAL_OWNERID, COL_OWNER_ID, 
				COL_ANIMAL_OWNERID,	
				COL_ANIMAL_OWNERID);
		
		
		public AnimalOwnerUpdate read(Integer id) {
			AnimalOwnerUpdate aou = null;
			
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
				
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				
				if(rs.next()) {
					aou = getPetAoFromResultSet(rs);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeResources(conn, stmt, rs);
			}
			return aou;
		}

		
		
		// 업데이트 검사
		public boolean ownerUpdate(Owner owner) {
			LocalDateTime currentDateTime = LocalDateTime.now();
			String ownerUpdate = String.format(
					"UPDATE %s "
					+ "SET %s = ?, %s = ? , %s = ?, "
					+ " %s = ?, %s = ? "
					+ "WHERE %s = ?" ,					
					TBL_OWNER,
					COL_OWNER_NAME, COL_OWNER_PHONE_NUMBER, COL_OWNER_ADDRESS, 
					COL_OWNER_MEMO, COL_OWNER_MODIFIED_TIME,
					COL_OWNER_ID
					);
			
			Connection conn = null;
			PreparedStatement stmt = null;
			
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				stmt = conn.prepareStatement(ownerUpdate);
				
				stmt.setString(1, owner.getOwnerName());
				stmt.setString(2, owner.getPhoneNumber());
				stmt.setString(3, owner.getAddress());
				stmt.setString(4, owner.getOwnerMemo());
				stmt.setTimestamp(5, Timestamp.valueOf(currentDateTime));
				stmt.setInt(6, owner.getOwnerId());
				
				int ownerUpdated = stmt.executeUpdate();
				
				return ownerUpdated > 0;
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			return false;
				
		}
		
		public boolean animalUpdate(Animal animals) {
			LocalDateTime currentDateTime = LocalDateTime.now();
			String animalUpdate = String.format(					
					"UPDATE %s "
					+ "SET %s = ?, %s = ? , %s = ?, %s = ? , "
					+ "%s = ? , %s = ?, %s = ? "
					+ "WHERE %s = ?" ,					
					TBL_ANIMAL,
					COL_ANIMAL_NAME, COL_ANIMAL_TYPE, COL_ANIMAL_AGE ,
					COL_ANIMAL_GENDER, COL_ANIMAL_WEIGHT,
					COL_ANIMAL_MEMO, COL_ANIMAL_MODIFIED_TIME,
					COL_ANIMAL_ID
					);
			
			Connection conn = null;
			PreparedStatement stmt = null;
			
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				stmt = conn.prepareStatement(animalUpdate);
				
				stmt.setString(1, animals.getAnimalName());
				stmt.setString(2, animals.getAnimalType());
				stmt.setInt(3, animals.getAge());
				stmt.setString(4, animals.getGender());
				stmt.setDouble(5, animals.getWeight());
				stmt.setString(6, animals.getMemo());
				stmt.setTimestamp(7, Timestamp.valueOf(currentDateTime));
				stmt.setInt(8, animals.getId());
				
				int animalUpdated = stmt.executeUpdate();
				
				return animalUpdated > 0;
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			return false;
		}

		// 검색 기능		
		private static final String SQL_SELECT_BY_ANIMAL_NAME = String.format(
				"select * from %s a "
				+ "join %s o on a.%s = o.%s "
				+ "where upper(%s) like upper(?) order by %s desc",
				TBL_ANIMAL,
				TBL_OWNER, COL_ANIMAL_OWNERID, COL_OWNER_ID,			
				COL_ANIMAL_NAME, COL_ANIMAL_ID);
		
		private static final String SQL_SELECT_BY_OWNER_NAME = String.format(
				"select * from %s a "
				+ "join %s o on a.%s = o.%s "
				+ "where upper(%s) like upper(?) order by %s desc",
				TBL_ANIMAL,
				TBL_OWNER, COL_ANIMAL_OWNERID, COL_OWNER_ID,			
				COL_OWNER_NAME, COL_ANIMAL_ID);
		
		private static final String SQL_SELECT_BY_ALL = String.format(
				"select * from %s a "
				+ "join %s o on a.%s = o.%s "
				+ "where upper(%s) like upper(?) or "
				+ "upper(%s) like upper(?) or "
				+ "upper(%s) like upper(?) or "
				+ "upper(%s) like upper(?)"
				+ "order by %s desc",
				TBL_ANIMAL,
				TBL_OWNER, COL_ANIMAL_OWNERID, COL_OWNER_ID,			
				COL_ANIMAL_NAME, 
				COL_ANIMAL_TYPE,
				COL_ANIMAL_GENDER,
				COL_OWNER_NAME,
				COL_ANIMAL_ID);
		
		private static final String SQL_SELECT_BY_ANIMAL_TYPE = String.format(
				"select * from %s a "
				+ "join %s o on a.%s = o.%s "
				+ "where upper(%s) like upper(?) order by %s desc",
				TBL_ANIMAL,
				TBL_OWNER, COL_ANIMAL_OWNERID, COL_OWNER_ID,			
				COL_ANIMAL_TYPE, COL_ANIMAL_ID);
		
		private static final String SQL_SELECT_BY_ANIMAL_GENDER = String.format(
				"select * from %s a "
				+ "join %s o on a.%s = o.%s "
				+ "where upper(%s) like upper(?) order by %s desc",
				TBL_ANIMAL,
				TBL_OWNER, COL_ANIMAL_OWNERID, COL_OWNER_ID,			
				COL_ANIMAL_GENDER, COL_ANIMAL_ID);
		public List<AnimalOwner> read(int type, String keyword) {

			List<AnimalOwner> result = new ArrayList<>();
			
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				
				String searchKeword = "%" + keyword + "%";
				
				switch(type) {
				
				case 0: // 전체로 검색
					stmt = conn.prepareStatement(SQL_SELECT_BY_ALL);
					stmt.setString(1, searchKeword);
					stmt.setString(2, searchKeword);
					stmt.setString(3, searchKeword);
					stmt.setString(4, searchKeword);
					break;
					
				case 1: // 이름으로 검색 
					stmt = conn.prepareStatement(SQL_SELECT_BY_ANIMAL_NAME);
					stmt.setString(1, searchKeword);
					break;
				case 2: // 보호자 이름
					stmt = conn.prepareStatement(SQL_SELECT_BY_OWNER_NAME);
					stmt.setString(1, searchKeword);
					break; 
				case 3: // 분류
					stmt = conn.prepareStatement(SQL_SELECT_BY_ANIMAL_TYPE);
					stmt.setString(1, searchKeword);
					break;
				case 4:
					stmt = conn.prepareStatement(SQL_SELECT_BY_ANIMAL_GENDER);
					stmt.setString(1, searchKeword);
				}	
				rs = stmt.executeQuery();
				while(rs.next()) {
					AnimalOwner ao = getPetAnimalFromResultSet(rs);
					result.add(ao);
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			} finally {
				closeResources(conn, stmt, rs);
			}
			
			return result;
		}
		
		//업데이트할 고객 목록
		private static final String SQL_SELECT_BY_JOIN_ID = String.format(
				"select a.%s, a.%s, a.%s, a.%s, a.%s, a.%s, a.%s, o.%s "
				+ "from %s a "
				+ "join %s o on a.%s = o.%s "
				+ "where a.%s = ?"
				+ "order by %s desc ",
						
				COL_ANIMAL_ID, COL_ANIMAL_NAME, COL_ANIMAL_TYPE,
				COL_ANIMAL_AGE, COL_ANIMAL_GENDER,COL_ANIMAL_WEIGHT,
				COL_ANIMAL_MEMO,
				COL_OWNER_NAME,
				TBL_ANIMAL,TBL_OWNER,
				COL_ANIMAL_OWNERID, COL_OWNER_ID,
				COL_ANIMAL_ID,
				
				COL_ANIMAL_ID);
		
		
		public AnimalOwner readAo(Integer id) {
			AnimalOwner ao = null;
			
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				stmt = conn.prepareStatement(SQL_SELECT_BY_JOIN_ID);
				
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				
				if(rs.next()) {
					ao = getPetAnimalFromResultSet(rs);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeResources(conn, stmt, rs);
			}
			return ao;
		}
		
		
		
		
}
