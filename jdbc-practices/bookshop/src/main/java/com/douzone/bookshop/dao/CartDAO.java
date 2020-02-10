package com.douzone.bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookshop.vo.CartVO;

public class CartDAO {

	// SELECT (all)
	public List<CartVO> findAll(CartVO cartVO) {
		List<CartVO> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet set = null;

		try {
			conn = getConnection();

			String command = "select C.member_no, C.book_no, B.name, C.count, C.price from cart C join book B on (C.book_no = B.no) where C.member_no = ?";
			statement = conn.prepareStatement(command);

			statement.setLong(1, cartVO.getMember_no());

			set = statement.executeQuery();
			while (set.next()) {
				Long member_no = set.getLong(1);
				Long book_no = set.getLong(2);
				String book_name = set.getString(3);
				Long count = set.getLong(4);
				Long price = set.getLong(5);

				CartVO vo = new CartVO();
				vo.setMember_no(member_no);
				vo.setBook_no(book_no);
				vo.setBook_name(book_name);
				vo.setCount(count);
				vo.setPrice(price);

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
	public Boolean insert(CartVO vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement statement = null;

		try {
			conn = getConnection();

			String command = "insert into cart values(?, ?, ?, ?*(select price from book where no = ?))";
			statement = conn.prepareStatement(command);

			statement.setLong(1, vo.getBook_no());
			statement.setLong(2, vo.getMember_no());
			statement.setLong(3, vo.getCount());
			statement.setLong(4, vo.getCount());
			statement.setLong(5, vo.getBook_no());

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
	public Boolean update(CartVO vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement statement = null;

		try {
			conn = getConnection();

			String command = "update cart set count = ?, price = ?*(select price from book where no = ?) where member_no = ? and book_no = ?";
			statement = conn.prepareStatement(command);

			statement.setLong(1, vo.getCount());
			statement.setLong(2, vo.getCount());
			statement.setLong(3, vo.getBook_no());
			statement.setLong(4, vo.getMember_no());
			statement.setLong(5, vo.getBook_no());

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
	public Boolean delete(CartVO vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement statement = null;

		try {
			conn = getConnection();

			String command = "delete from cart where member_no = ? and book_no = ?";
			statement = conn.prepareStatement(command);

			statement.setLong(1, vo.getMember_no());
			statement.setLong(2, vo.getBook_no());

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
