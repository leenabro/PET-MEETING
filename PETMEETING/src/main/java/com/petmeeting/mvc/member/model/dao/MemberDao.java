package com.petmeeting.mvc.member.model.dao;

import static com.petmeeting.mvc.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.petmeeting.mvc.admin.model.vo.Admin;
import com.petmeeting.mvc.common.jdbc.JDBCTemplate;
import com.petmeeting.mvc.member.model.vo.Dog;
import com.petmeeting.mvc.member.model.vo.Member;;

public class MemberDao {
	
	public Member findMemberById(Connection connection, String id) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM MEMBER WHERE M_ID=? AND M_STATUS='Y'";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				member = new Member();
				
				member.setMCode(rs.getInt("M_CODE"));
				member.setId(rs.getString("M_ID"));
				member.setPassword(rs.getString("M_PWD"));
				member.setName(rs.getString("M_NAME"));
				member.setNickname(rs.getString("M_NICKNAME"));
				member.setPhone(rs.getString("M_PHONE"));
				member.setAddress(rs.getString("M_ADDRESS"));
				member.setEmail(rs.getString("M_EMAIL"));
				member.setBirth(rs.getString("M_BIRTH"));
				member.setSignUpDate(rs.getDate("M_SU_DATE"));
				member.setGender(rs.getString("M_GENDER"));
				member.setStatus(rs.getString("M_STATUS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return member;
	}
	
	public String findIdByEmail(Connection connection, String email) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectId = null;
		String query = "SELECT M_ID FROM MEMBER WHERE M_EMAIL=? AND M_STATUS='Y'";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				selectId = rs.getString("M_ID");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return selectId;
	}
	
//	public boolean findPwd(Connection connection, String id, String email) {
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String selectId = null;
//		String selectEmail = null;
//		String query = "SELECT M_ID FROM MEMBER WHERE M_EMAIL=? AND M_STATUS='Y'";
//		
//		try {
//			pstmt = connection.prepareStatement(query);
//			
//			pstmt.setString(1, email);
//			
//			rs = pstmt.executeQuery();
//			
//			if(!rs.next()) {
//				selectId = rs.getString("M_ID");
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(rs);
//			close(pstmt);
//		}
//		
//		return selectId;
//	}
	
	public int insertMember(Connection connection, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO MEMBER VALUES(M_CODE_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,DEFAULT,?,DEFAULT)";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getNickname());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getAddress());
			pstmt.setString(7, member.getEmail());
			pstmt.setString(8, member.getBirth());
			pstmt.setString(9, member.getGender());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int updateMember(Connection connection, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query ="UPDATE MEMBER SET M_NICKNAME=?,M_PHONE=? WHERE M_CODE=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, member.getNickname());
			pstmt.setString(2, member.getPhone());
			pstmt.setInt(3, member.getMCode());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	public boolean emailDuplicateCheck(Connection connection ,String inputEmail) {
		boolean result = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(M_EMAIL) FROM MEMBER WHERE M_EMAIL=? AND M_STATUS='Y'";

		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, inputEmail);
			
			rs = pstmt.executeQuery();

			rs.next();
			
			result = (rs.getInt(1) == 1); // 값 없음(0, 사용가능, false) 값 있음(1, 사용 불가능, true)
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return result;
	}
	
	public boolean nickDuplicateCheck(Connection connection ,String inputNick) {
		boolean result = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(M_NICKNAME) FROM MEMBER WHERE M_NICKNAME=? AND M_STATUS='Y'";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, inputNick);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			
			result = (rs.getInt(1) == 1); // 값 없음(0, 사용가능, false) 값 있음(1, 사용 불가능, true)
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return result;
	}
	
	// 강아지 정보 인서트
		public int insertDog(Connection connection, Dog dog, Member member) {
			int result = 0;
			PreparedStatement pstmt = null;
			String query = "INSERT INTO DOG ( "
					+ "    D_ID, "
					+ "    D_ORI_IMG, "
					+ "    D_RE_IMG, "
					+ "    D_NUM, "
					+ "    D_NAME, "
					+ "    D_KIND, "
					+ "    D_SIZE, "
					+ "    D_GENDER, "
					+ "    NEUTERED, "
					+ "    VACCINE, "
					+ "    M_CODE "
					+ ") VALUES('DOG' || D_ID.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			try {
				pstmt = connection.prepareStatement(query);
				
				pstmt.setString(1, dog.getImgOriginName());
				pstmt.setString(2, dog.getImgReName());
				pstmt.setInt(3, dog.getNum());
				pstmt.setString(4, dog.getName());
				pstmt.setString(5, dog.getKind());
				pstmt.setString(6, dog.getSize());
				pstmt.setString(7, dog.getGender());
				pstmt.setString(8, dog.getNeutered());
				pstmt.setString(9, dog.getVaccine());
				pstmt.setInt(10, member.getMCode());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}
			
			
			return result;
		}
		
		public Dog findDogByCode(Connection connection, int code) {
			Dog dog = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = "SELECT * FROM DOG WHERE M_CODE=? AND D_NUM = 1";
			
			try {
				pstmt = connection.prepareStatement(query);
				
				pstmt.setInt(1, code);
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					dog = new Dog();
					
					dog.setId(rs.getString("D_ID"));
					dog.setNum(rs.getInt("D_NUM"));
					dog.setMCode(rs.getInt("M_CODE"));
					dog.setName(rs.getString("D_NAME"));
					dog.setKind(rs.getString("D_KIND"));
					dog.setSize(rs.getString("D_SIZE"));
					dog.setGender(rs.getString("D_GENDER"));
					dog.setNeutered(rs.getString("NEUTERED"));
					dog.setVaccine(rs.getString("VACCINE"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return dog;
		}

		public List<Dog> findAllDogByCode(Connection connection, int mCode) {
			List<Dog> list = new ArrayList<>();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = "SELECT D_NUM, D_ORI_IMG, D_RE_IMG, D_NAME, D_KIND, D_SIZE, D_GENDER, NEUTERED, VACCINE, M_CODE "
						 + "FROM DOG "
						 + "WHERE M_CODE = ?";
			
			try {
				pstmt = connection.prepareStatement(query);
				
				pstmt.setInt(1, mCode);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					Dog dog = new Dog();
					
					dog.setMCode(rs.getInt("M_CODE"));
					dog.setNum(rs.getInt("D_NUM"));
					dog.setImgOriginName(rs.getString("D_ORI_IMG"));
					dog.setImgReName(rs.getString("D_RE_IMG"));
					dog.setName(rs.getString("D_NAME"));
					dog.setKind(rs.getString("D_KIND"));
					dog.setSize(rs.getString("D_SIZE"));
					dog.setGender(rs.getString("D_GENDER"));
					dog.setNeutered(rs.getString("NEUTERED"));
					dog.setVaccine(rs.getString("VACCINE"));
					
					list.add(dog);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return list;
		}

		public int countMemberDog(Connection connection, int mCode) {
			int result = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = "SELECT COUNT(D_NUM) "
						 + "FROM DOG "
						 + "WHERE M_CODE = ?";
			
			try {
				pstmt = connection.prepareStatement(query);
				
				pstmt.setInt(1, mCode);
				
				rs = pstmt.executeQuery();
				
				rs.next();
				
				result = rs.getInt("COUNT(D_NUM)");
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			
			
			return result;
		}

		public Admin findAdminById(Connection connection, String id) {
			Admin admin = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = "SELECT * FROM ADMIN WHERE A_ID=?";
			
			try {
				pstmt = connection.prepareStatement(query);
				
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					admin = new Admin();
					
					admin.setId(rs.getString("A_ID"));
					admin.setPassword(rs.getString("A_PWD"));
					admin.setRole(rs.getString("A_ROLE"));
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return admin;
		}

		public int updateMemberPassword(Connection connection, int mCode, String userPwd) {
			int result = 0;
			PreparedStatement pstmt = null;
			String query = "UPDATE MEMBER SET M_PWD=? WHERE M_CODE=?";
			
			try {
				pstmt = connection.prepareStatement(query);
				
				pstmt.setString(1, userPwd);
				pstmt.setInt(2, mCode);
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}
	
	
	
	
	
	
	
	
	
	
	
	
}