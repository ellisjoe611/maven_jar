package com.douzone.bookshop.test;

import java.util.List;

import com.douzone.bookshop.dao.MemberDAO;
import com.douzone.bookshop.vo.MemberVO;

public class MemberDAOTest {

	public static void main(String[] args) {
		insertTest("neo", "01019991999", "neo@matrix.com", "dontgetmypw");
		updateTest("neo", "01019992003", "neo123@matrix.com", "dontgetmypw");
		deleteTest("neo", "dontgetmypw");

	}

	private static void deleteTest(String name, String password) {
		MemberVO deleteVO = new MemberVO();
		deleteVO.setName(name);
		deleteVO.setPassword(password);

		System.out.println(new MemberDAO().delete(deleteVO) == true ? name + "님 회원탈퇴 완료!" : "회원탈퇴 실패...");

		findAllTest();
	}

	private static void updateTest(String name, String newPhone, String newEmail, String password) {
		MemberVO updateVO = new MemberVO();
		updateVO.setName(name);
		updateVO.setPhone(newPhone);
		updateVO.setEmail(newEmail);
		updateVO.setPassword(password);

		System.out.println(new MemberDAO().update(updateVO) == true ? name + "님 회원수정 완료!" : "회원수정 실패...");

		findAllTest();
	}

	private static void insertTest(String name, String phone, String email, String password) {
		MemberVO newVO = new MemberVO();
		newVO.setName(name);
		newVO.setPhone(phone);
		newVO.setEmail(email);
		newVO.setPassword(password);

		System.out.println(new MemberDAO().insert(newVO) == true ? name + "님 회원가입 성공!" : "회원가입 실패...");

		findAllTest();
	}

	public static void findAllTest() {
		List<MemberVO> list = new MemberDAO().findAll();
		System.out.println("<<회원 목록>>");
		for (MemberVO vo : list) {
			System.out.println(vo);
		}
		System.out.println();
	}

}
