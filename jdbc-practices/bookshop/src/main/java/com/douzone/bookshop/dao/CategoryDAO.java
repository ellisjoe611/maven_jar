package com.douzone.bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookshop.vo.CategoryVO;

public class CategoryDAO {

	// SELECT
	public CategoryVO find(Long no) {
		CategoryVO result = new CategoryVO();

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet set = null;

		try {
			conn = getConnection();

			String url = "select no, name from category where no = ?";
			statement = conn.prepareStatement(url);
			statement.setLong(1, no);

			set = statement.executeQuery();
			while (set.next()) {
				Long result_no = set.getLong(1);
				result.setNo(result_no);
				String name = set.getString(2);
				result.setName(name);
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

	// SELECT (all)
	public List<CategoryVO> findAll() {
		List<CategoryVO> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet set = null;

		try {
			conn = getConnection();

			String sql = "select no, name from category";
			statement = conn.prepareStatement(sql);

			set = statement.executeQuery();
			while (set.next()) {
				Long no = set.getLong(1);
				String name = set.getString(2);

				CategoryVO vo = new CategoryVO();
				vo.setNo(no);
				vo.setName(name);

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
	public Boolean insert(CategoryVO vo) {
		Boolean result = false;

		Connection conn = null;
		PreparedStatement statement = null;

		try {
			conn = getConnection();

			String sql = "insert into category values(null, ?)";
			statement = conn.prepareStatement(sql);

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

	// UPDATE
	public Boolean update(CategoryVO vo) {
		Boolean result = false;

		Connection conn = null;
		PreparedStatement statement = null;

		try {
			conn = getConnection();

			String sql = "update category set name = ? where no = ?";
			statement = conn.prepareStatement(sql);

			statement.setString(1, vo.getName());
			statement.setLong(2, vo.getNo());

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
	public Boolean delete(CategoryVO vo) {
		Boolean result = false;

		Connection conn = null;
		PreparedStatement statement = null;

		try {
			conn = getConnection();

			String sql = "delete from category where name = ?";
			statement = conn.prepareStatement(sql);

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
