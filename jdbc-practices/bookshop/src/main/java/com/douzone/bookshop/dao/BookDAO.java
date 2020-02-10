package com.douzone.bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookshop.vo.BookVO;

public class BookDAO {

	// SELECT (all)
	public List<BookVO> findAll() {
		List<BookVO> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet set = null;

		try {
			conn = getConnection();

			String command = "select B.no, B.name, B.price, C.name from book B join category C on(B.category_no = C.no) order by B.no asc";
			statement = conn.prepareStatement(command);

			set = statement.executeQuery();
			while (set.next()) {
				Long no = set.getLong(1);
				String name = set.getString(2);
				Long price = set.getLong(3);
				String category_name = set.getString(4);

				BookVO vo = new BookVO();
				vo.setNo(no);
				vo.setName(name);
				vo.setPrice(price);
				vo.setCategory_name(category_name);

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
	public Boolean insert(BookVO vo) {
		Boolean result = false;

		Connection conn = null;
		PreparedStatement statement = null;

		try {
			conn = getConnection();

			String command = "insert into book values(null, ?, ?, ?)";
			statement = conn.prepareStatement(command);

			statement.setString(1, vo.getName());
			statement.setLong(2, vo.getPrice());
			statement.setLong(3, vo.getCategory_no());

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
	public Boolean update(BookVO vo) {
		Boolean result = false;

		Connection conn = null;
		PreparedStatement statement = null;

		try {
			conn = getConnection();

			String command = "update book set price = ? where name = ?";
			statement = conn.prepareStatement(command);

			statement.setLong(1, vo.getPrice());
			statement.setString(2, vo.getName());

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
	public Boolean delete(BookVO vo) {
		Boolean result = false;

		Connection conn = null;
		PreparedStatement statement = null;

		try {
			conn = getConnection();

			String command = "delete from book where name = ?";
			statement = conn.prepareStatement(command);

			statement.setString(1, vo.getName());

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
