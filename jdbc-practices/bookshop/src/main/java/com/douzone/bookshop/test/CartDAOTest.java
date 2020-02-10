package com.douzone.bookshop.test;

import java.util.List;

import com.douzone.bookshop.dao.CartDAO;
import com.douzone.bookshop.vo.CartVO;

public class CartDAOTest {

	public static void main(String[] args) {
//		findAllTest(5);
		insertTest(5, 5, 4);
		updateTest(5, 5, 3);
		deleteTest(5, 5);
	}

	private static void deleteTest(int memberNo, int bookNo) {
		CartVO vo = new CartVO();
		vo.setBook_no(Integer.toUnsignedLong(bookNo));
		vo.setMember_no(Integer.toUnsignedLong(memberNo));

		System.out.println(new CartDAO().delete(vo) == true ? "카트 삭제 성공!" : "카트 삭제 실패...");
		findAllTest(memberNo);
	}

	private static void updateTest(int memberNo, int bookNo, int count) {
		CartVO vo = new CartVO();
		vo.setBook_no(Integer.toUnsignedLong(bookNo));
		vo.setMember_no(Integer.toUnsignedLong(memberNo));
		vo.setCount(Integer.toUnsignedLong(count));

		System.out.println(new CartDAO().update(vo) == true ? "카트 업데이트 성공!" : "카트 업데이트 실패...");
		findAllTest(memberNo);
	}

	private static void insertTest(int memberNo, int bookNo, int count) {
		CartVO vo = new CartVO();
		vo.setBook_no(Integer.toUnsignedLong(bookNo));
		vo.setMember_no(Integer.toUnsignedLong(memberNo));
		vo.setCount(Integer.toUnsignedLong(count));

		System.out.println(new CartDAO().insert(vo) == true ? "카트 추가 성공!" : "카트 추가 실패...");
		findAllTest(memberNo);
	}

	public static void findAllTest(int memberNo) {
		CartVO searchVO = new CartVO();
		searchVO.setMember_no(Integer.toUnsignedLong(memberNo));

		List<CartVO> list = new CartDAO().findAll(searchVO);
		for (CartVO vo : list) {
			System.out.println(vo);
		}
		System.out.println();
	}

}
