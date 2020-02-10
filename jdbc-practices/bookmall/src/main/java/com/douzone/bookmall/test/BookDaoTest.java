package com.douzone.bookmall.test;

import java.util.List;

import com.douzone.bookmall.dao.BookDao;
import com.douzone.bookmall.vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
//		insertTest("Autobiograpy of 스테파니", "대여중", 1L);
		findAllTest();

	}

	public static void insertTest(String title, String state, Long authorNo) {
		BookVo vo = new BookVo();
		vo.setTitle(title);
		vo.setState(state);
		vo.setAuthorNo(authorNo);

		Boolean result = new BookDao().insert(vo);
		System.out.println(result == true ? "성공" : "실패");
	}

	public static void findAllTest() {
		List<BookVo> list = new BookDao().findAll();
		for (BookVo vo : list) {
			System.out.println(vo);
		}
	}

}
