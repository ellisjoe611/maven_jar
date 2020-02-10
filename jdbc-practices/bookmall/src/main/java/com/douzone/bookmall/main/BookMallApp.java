package com.douzone.bookmall.main;

import java.util.List;
import java.util.Scanner;

import com.douzone.bookmall.dao.BookDao;
import com.douzone.bookmall.vo.BookVo;

public class BookMallApp {

	public static void main(String[] args) {
		displayBookInfo();
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("대여할 책 번호 > ");
		Long no = scanner.nextLong();
		
		rent(no);
		displayBookInfo();
		
		scanner.close();
		

	}

	private static void rent(Long no) {
		// TODO Auto-generated method stub
		
	}

	private static void displayBookInfo() {
		System.out.println("**********************정보 출력**********************");
		List<BookVo> list = new BookDao().findAll();
		for (BookVo vo : list) {
			System.out.println("[" + vo.getNo() + "] 책 제목: " + vo.getTitle() + ", 작가: " + vo.getAuthorName() + ", 대여 여부 : " + vo.getState());
		}
	}

}
