package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.JdbcUtils;

public class PrisonerDAO {
	// 죄수 테이블의 필드 식별번호
	public static final int FIELD_PRINUM = 1;	// 죄수번호
	public static final int FIELD_NAME = 2;		// 이름
	public static final int FIELD_CRIME = 3;	// 죄목
	public static final int FIELD_TYPE = 4;		// 죄수구분
	public static final int FIELD_PENALTY = 5;	// 형량
	public static final int FIELD_SCORE = 6;	// 상벌점
	public static final int FIELD_WORK = 7;		// 노동량
	public static final int FIELD_ILL = 8;		// 질병유무
	public static final int FIELD_PUNISH = 9;	// 징계횟수
	
	Connection conn = null;
	JdbcUtils db;
	
	public PrisonerDAO() {
		db = new JdbcUtils();
	}
	
	/**
	 * DB에 죄수 추가
	 * @param prisoner
	 * @return
	 */
	public boolean insertPrisoner(Prisoner prisoner) {
		String sql = "insert into prisoner values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement pstmt = null;
		int insertResult = 0;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, prisoner.getPrinum());		// 죄수번호
			pstmt.setString(2, prisoner.getName());			// 이름
			pstmt.setString(3, prisoner.crimeToString());	// 죄목
			pstmt.setString(4, prisoner.typeToString());	// 죄수구분
			pstmt.setInt(5, prisoner.getPenalty());			// 형량
			pstmt.setInt(6, prisoner.getScore());			// 상벌점
			pstmt.setInt(7, prisoner.getWork());			// 노동량
			pstmt.setInt(8, prisoner.isIll() ? 1 : 0);		// 질병유무(참이면 1, 거짓이면 0)
			pstmt.setInt(9, prisoner.getPunish());			// 징계횟수
			
			insertResult = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.close(conn, pstmt, null);
		}
		
		return insertResult > 0;
	}
	
	/**
	 * DB에서 죄수 삭제
	 * @param prisonerNo
	 * @return
	 */
	public boolean deletePrisoner(String prisonerNo) {
		String sql = "delete from prisoner where prinum = ?";
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, prisonerNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.close(conn, pstmt, null);
		}
		
		return result > 0;
	}
	
	/**
	 * 죄수검색<br>
	 * 죄수번호 이외의 문자열 값으로 검색하는 기능을 위한 메서드
	 * @param value 검색값
	 * @param field 필드 식별번호
	 * @return
	 */
	public ArrayList<Prisoner> selectPrisoners(String value, int field) {
		String sql = "";
		switch(field) {
		case FIELD_NAME:
			sql = "select * from prisoner where name = ?";
			break;
		case FIELD_CRIME:
			sql = "select * from prisoner where crime = ?";
			break;
		case FIELD_TYPE:
			sql = "select * from prisoner where prisoner_type = ?";
			break;
		default:
			System.err.println("오류: 잘못된 매개변수입니다.\nPrisonerDAO.selectPrisoners()");
			return null;
		}
		ArrayList<Prisoner> foundPrisoners = new ArrayList<Prisoner>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				foundPrisoners.add(new Prisoner(
						rs.getString("name"),
						rs.getString("prinum"),
						rs.getString("crime"),
						rs.getString("prisoner_type"),
						rs.getInt("penalty"),
						rs.getInt("score"),
						rs.getInt("work"),
						rs.getInt("ill") == 0 ? false : true,
						rs.getInt("punish")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.close(conn, pstmt, rs);
		}
		
		return foundPrisoners;
	}
	
	public Prisoner selectPrisonerByPrinum(String prinum) {
				String sql = "select * from prisoner where prinum = ?";
				Prisoner foundPrisoner = null;
				
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				
				try {
					conn = db.getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, prinum);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						foundPrisoner = new Prisoner(
								rs.getString("name"),
								rs.getString("prinum"),
								rs.getString("crime"),
								rs.getString("prisoner_type"),
								rs.getInt("penalty"),
								rs.getInt("score"),
								rs.getInt("work"),
								rs.getInt("ill") == 0 ? false : true,
								rs.getInt("punish")
								);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					db.close(conn, pstmt, rs);
				}
				
				return foundPrisoner;
	}
	
	/**
	 * 전체 죄수 목록 가져오기
	 * @return
	 */
	public ArrayList<Prisoner> selectAllPrisoners() {
		String sql = "select * from prisoner order by prinum asc";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<Prisoner> prisoners = new ArrayList<Prisoner>();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs != null && rs.next()) {
				prisoners.add(new Prisoner(
						rs.getString("name"),
						rs.getString("prinum"),
						rs.getString("crime"),
						rs.getString("prisoner_type"),
						rs.getInt("penalty"),
						rs.getInt("score"),
						rs.getInt("work"),
						rs.getInt("ill") == 0 ? false : true,
						rs.getInt("punish")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.close(conn, pstmt, rs);
		}
		
		return prisoners;
	}
	
	/**
	 * 죄수 정보의 레코드를 갱신
	 * @param modifiedPrionser
	 * @return
	 */
	public boolean updatePrisoner(Prisoner modifiedPrisoner) {
		String sql = "update prisoner set name = ?, crime = ?, prisoner_type = ?, " + 
				"penalty = ?, score = ?, work = ?, " + 
				"ill = ?, punish = ? where prinum = ?";
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, modifiedPrisoner.getName());
			pstmt.setString(2, modifiedPrisoner.crimeToString());
			pstmt.setString(3, modifiedPrisoner.typeToString());
			pstmt.setInt(4, modifiedPrisoner.getPenalty());
			pstmt.setInt(5, modifiedPrisoner.getScore());
			pstmt.setInt(6, modifiedPrisoner.getWork());
			pstmt.setInt(7, modifiedPrisoner.isIll() == true ? 1 : 0);
			pstmt.setInt(8, modifiedPrisoner.getPunish());
			pstmt.setString(9, modifiedPrisoner.getPrinum());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.close(conn, pstmt, null);
		}
		
		return result > 0;
	}
	
	/**
	 * 징계 처리 내부동작(DB)
	 * @param prisonerNo 죄수번호
	 * @return 성공여부
	 */
	public boolean initScore(String prisonerNo) {
		// 벌점이 -40점인 죄수만 징계 가능
		// 징계를 받은 죄수의 벌점은 0으로 초기화
		Prisoner prisoner = selectPrisonerByPrinum(prisonerNo);
		
		if(prisoner == null)
			return false;
		
		if(prisoner.getScore() > -40)
			return false;
		
		prisoner.setPunish(prisoner.getPunish() + 1);	// 징계 횟수 1 증가
		prisoner.setScore(0);							// 상벌점 초기화
		
		
		return updatePrisoner(prisoner);
	}
}
