package com.douzone.bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookshop.vo.OrderVO;

public class OrderDAO {

	public List<OrderVO> findAll() {
		List<OrderVO> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet set = null;

		try {
			conn = getConnection();

			String command = "select O.no, O.no_full, M.name, M.email, O.price, O.address from `order` O join member M on(O.member_no = M.no) order by O.no asc";
			statement = conn.prepareStatement(command);

			set = statement.executeQuery();
			while (set.next()) {
				Long no = set.getLong(1);
				String no_full = set.getString(2);
				String member_name = set.getString(3);
				String member_email = set.getString(4);
				Long price = set.getLong(5);
				String address = set.getString(6);

				OrderVO vo = new OrderVO();
				vo.setNo(no);
				vo.setNo_full(no_full);
				vo.setMember_name(member_name);
				vo.setMember_email(member_email);
				vo.setPrice(price);
				vo.setAddress(address);

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
	public Boolean insert(OrderVO vo) {
		Boolean result = false;

		Connection conn = null;
		PreparedStatement statement = null;

		try {
			conn = getConnection();

			String command = "insert into `order` values(?, concat( date_format(now(), '%Y%m%d-'), lpad(cast(? as char), 5, '0') ), ?, ?, ?)";
			statement = conn.prepareStatement(command);

			statement.setLong(1, vo.getNo());
			statement.setLong(2, vo.getNo());
			statement.setLong(3, vo.getPrice());
			statement.setString(4, vo.getAddress());
			statement.setLong(5, vo.getMember_no());

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
	public Boolean update(OrderVO vo) {
		Boolean result = false;

		Connection conn = null;
		PreparedStatement statement = null;

		try {
			conn = getConnection();

			String command = "update `order` set price = ?, address = ? where no_full = ?";
			statement = conn.prepareStatement(command);

			statement.setLong(1, vo.getPrice());
			statement.setString(2, vo.getAddress());
			statement.setString(3, vo.getNo_full());

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
	public Boolean delete(OrderVO vo) {
		Boolean result = false;

		Connection conn = null;
		PreparedStatement statement = null;

		try {
			conn = getConnection();

			String command = "delete from `order` where no_full = ?";
			statement = conn.prepareStatement(command);

			statement.setString(1, vo.getNo_full());

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
