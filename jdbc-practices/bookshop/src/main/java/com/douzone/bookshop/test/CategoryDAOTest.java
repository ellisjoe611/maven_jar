package com.douzone.bookshop.test;

import java.util.List;

import com.douzone.bookshop.dao.CategoryDAO;
import com.douzone.bookshop.vo.CategoryVO;

public class CategoryDAOTest {

	public static void main(String[] args) {
//		findAllTest();
		insertTest("국어");
		updateTest(7, "언어");
		deleteTest("언어");
	}

	private static void deleteTest(String name) {
		CategoryVO vo = new CategoryVO();
		vo.setName(name);
		
		System.out.println(new CategoryDAO().delete(vo) == true ? "삭제 성공!" : "삭제 실패...");
		findAllTest();
	}

	private static void updateTest(int no, String newName) {
		CategoryVO vo = new CategoryVO();
		vo.setNo(Integer.toUnsignedLong(no));
		vo.setName(newName);
		
		System.out.println(new CategoryDAO().update(vo) == true ? "업데이트 성공!" : "업데이트 실패...");
		findAllTest();
	}

	private static void insertTest(String newName) {
		CategoryVO vo = new CategoryVO();
		vo.setName(newName);
		
		System.out.println(new CategoryDAO().insert(vo) == true ? "등록 성공" : "등록 실패");
		findAllTest();
	}

	public static void findTest(Long no) {
		CategoryVO vo = new CategoryDAO().find(no);
		System.out.println(vo);
	}

	public static void findAllTest() {
		List<CategoryVO> list = new CategoryDAO().findAll();
		for (CategoryVO l : list) {
			System.out.println(l);
		}
		System.out.println();
	}

}
