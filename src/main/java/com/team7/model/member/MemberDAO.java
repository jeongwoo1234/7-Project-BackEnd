package com.team7.model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.team7.model.connectDB;

public class MemberDAO {

	private Connection conn = null;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// Get DB Connection to AWS RDS
	public MemberDAO() {
		try { conn = connectDB.getRemoteConnection(); } 
		catch (Exception e) { e.printStackTrace(); }
	}
	
	// return all Members type ArrayList
	public ArrayList<MemberDTO> getMembers() {
		String sql = "SELECT * FROM member";
		ArrayList<MemberDTO> memberList = new ArrayList<MemberDTO>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberDTO m = new MemberDTO();
				m.setId(rs.getInt(1));
				m.setImage(rs.getString(2));
				m.setName(rs.getString(3));
				m.setBirthday(rs.getString(4));
				m.setGender(rs.getString(5));
				m.setJob(rs.getString(6));
				memberList.add(m);
			}
			rs.close(); pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberList;
	}
	
	// Insert new Member
	public int insertMember(String image, String name, String birthday, String gender, String job) {
		String sql = "INSERT INTO member VALUES(NULL, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, image);
			pstmt.setString(2, name);
			pstmt.setString(3, birthday);
			pstmt.setString(4, gender);
			pstmt.setString(5, job);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
