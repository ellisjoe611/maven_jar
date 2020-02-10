package com.douzone.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.vo.BookVo;

public class BookDao {
	
	// SELECT
	public BookVo find(Long no) {
		return null;
	}
	
	// SELECT (all)
	public List<BookVo> findAll() {
		List<BookVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select B.no, B.title, B.state, B.author_no, A.name from book B join author A on (B.author_no = A.no) order by B.no asc";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String state = rs.getString(3);
				Long authorNo = rs.getLong(4);
				String authorName = rs.getString(5);

				BookVo vo = new BookVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setState(state);
				vo.setAuthorNo(authorNo);
				vo.setAuthorName(authorName);

				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 6. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
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
	public Boolean insert(BookVo bookVo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "insert into book values(null, ?, ? ,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bookVo.getTitle());
			pstmt.setString(2, bookVo.getState());
			pstmt.setLong(3, bookVo.getAuthorNo());

			result = (pstmt.executeUpdate() == 1);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 6. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
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
	
	
	// UPDATE STATE
	

	// Connection을 리턴
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://127.0.0.1:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;

	}

}
