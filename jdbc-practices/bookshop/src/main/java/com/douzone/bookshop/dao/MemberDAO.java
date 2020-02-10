package com.douzone.bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookshop.vo.MemberVO;

public class MemberDAO {

	// SELECT (all)
	public List<MemberVO> findAll() {
		List<MemberVO> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet set = null;

		try {
			conn = getConnection();

			String command = "select * from member order by no asc";
			statement = conn.prepareStatement(command);

			set = statement.executeQuery();
			while (set.next()) {
				Long no = set.getLong(1);
				String name = set.getString(2);
				String phone = set.getString(3);
				String email = set.getString(4);
				String password = set.getString(5);

				MemberVO vo = new MemberVO();
				vo.setNo(no);
				vo.setName(name);
				vo.setPhone(phone);
				vo.setEmail(email);
				vo.setPassword(password);

				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 6. 자원정리
			try {
				if (set != null) {
					set.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// INSERT
	public Boolean insert(MemberVO newVO) {
		Boolean result = false;
		
		Connection conn = null;
		PreparedStatement statement = null;
		
		try {
			conn = getConnection();
			
			String command = "insert into member values(null, ?, ?, ?, password(?))";
			statement = conn.prepareStatement(command);
			
			statement.setString(1, newVO.getName());
			statement.setString(2, newVO.getPhone());
			statement.setString(3, newVO.getEmail());
			statement.setString(4, newVO.getPassword());
			
			result = (statement.executeUpdate() == 1);
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 6. 자원정리
			try {
				if (statement != null) {
					statement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// UPDATE
	public Boolean update(MemberVO updateVO) {
		Boolean result = false;

		Connection conn = null;
		PreparedStatement statement = null;
		
		try {
			conn = getConnection();
			
			String command = "update member set phone = ?, email=? where name = ? and `password` = password(?)";
			statement = conn.prepareStatement(command);
			
			statement.setString(1, updateVO.getPhone());
			statement.setString(2, updateVO.getEmail());
			statement.setString(3, updateVO.getName());
			statement.setString(4, updateVO.getPassword());
			
			result = (statement.executeUpdate() == 1);
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 6. 자원정리
			try {
				if (statement != null) {
					statement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	// DELETE
	public Boolean delete(MemberVO deleteVO) {
		Boolean result = false;

		Connection conn = null;
		PreparedStatement statement = null;
		
		try {
			conn = getConnection();
			
			String command = "delete from member where name = ? and `password` = password(?)";
			statement = conn.prepareStatement(command);
			
			statement.setString(1, deleteVO.getName());
			statement.setString(2, deleteVO.getPassword());
			
			result = (statement.executeUpdate() == 1);
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 6. 자원정리
			try {
				if (statement != null) {
					statement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	// Connection
	private Connection getConnection() {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/bookshop";

			conn = DriverManager.getConnection(url, "bookshop", "bookshop");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("SQL 연결 실패:" + e);
		}
		return conn;
	}

}
