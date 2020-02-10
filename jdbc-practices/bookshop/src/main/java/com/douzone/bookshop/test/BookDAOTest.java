package com.douzone.bookshop.test;

import java.util.List;

import com.douzone.bookshop.dao.BookDAO;
import com.douzone.bookshop.vo.BookVO;

public class BookDAOTest {

	public static void main(String[] args) {
		insertTest("C++ 기초", 25000, 3);
		updateTest("C++ 기초", 23000);
		deleteTest("C++ 기초");
	}

	private static void deleteTest(String bookName) {
		BookVO vo = new BookVO();
		vo.setName(bookName);

		System.out.println(new BookDAO().delete(vo) == true ? bookName + " <- 삭제 성공!" : bookName + " <- 삭제 실패...");
		findAllTest();

	}

	private static void updateTest(String bookName, Integer newPrice) {
		BookVO vo = new BookVO();
		vo.setName(bookName);
		vo.setPrice(Integer.toUnsignedLong(newPrice));

		System.out.println(new BookDAO().update(vo) == true ? bookName + " <- 가격변경 성공!" : bookName + " <- 가격변경 실패...");
		findAllTest();
	}

	private static void insertTest(String bookName, Integer price, Integer category_no) {
		BookVO vo = new BookVO();
		vo.setName(bookName);
		vo.setPrice(Integer.toUnsignedLong(price));
		vo.setCategory_no(Integer.toUnsignedLong(category_no));

		System.out.println(new BookDAO().insert(vo) == true ? bookName + " <- 등록 성공!" : bookName + " <- 등록 실패...");
		findAllTest();
	}

	public static void findAllTest() {
		List<BookVO> list = new BookDAO().findAll();
		for (BookVO vo : list) {
			System.out.println("[" + vo.getNo() + "] name: " + vo.getName() + ", price: " + vo.getPrice()
					+ "원, category: " + vo.getCategory_name());
		}
		System.out.println();
	}

}
