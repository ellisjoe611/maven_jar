package com.douzone.bookshop.test;

import java.util.List;

import com.douzone.bookshop.dao.OrderDAO;
import com.douzone.bookshop.vo.OrderVO;

public class OrderDAOTest {

	public static void main(String[] args) {
//		findAllTest();
		insertTest(4, 30000, "Hong Kong, China", 6);
		updateTest("20200201-00004", 50000, "Shanghai, China");
		deleteTest("20200201-00004");
		
	}

	private static void deleteTest(String no_full) {
		OrderVO vo = new OrderVO();
		vo.setNo_full(no_full);
		
		System.out.println(new OrderDAO().delete(vo) == true ? "주문 정보 삭제 성공!" : "삭제 실패...");
		findAllTest();
	}

	private static void updateTest(String no_full, int price, String address) {
		OrderVO vo = new OrderVO();
		vo.setNo_full(no_full);
		vo.setPrice(Integer.toUnsignedLong(price));
		vo.setAddress(address);
		
		System.out.println(new OrderDAO().update(vo) == true ? "주문 정보 업데이트 성공!" : "업데이트 실패...");
		findAllTest();
	}

	private static void insertTest(int no, int price, String address, int member_no) {
		OrderVO vo = new OrderVO();
		vo.setNo(Integer.toUnsignedLong(no));
		vo.setPrice(Integer.toUnsignedLong(price));
		vo.setAddress(address);
		vo.setMember_no(Integer.toUnsignedLong(member_no));

		System.out.println(new OrderDAO().insert(vo) == true ? "주문 정보 등록 성공!" : "등록 실패...");
		findAllTest();
	}

	public static void findAllTest() {
		List<OrderVO> list = new OrderDAO().findAll();
		for (OrderVO vo : list) {
			System.out.println(vo);
		}
		System.out.println();
	}

}
