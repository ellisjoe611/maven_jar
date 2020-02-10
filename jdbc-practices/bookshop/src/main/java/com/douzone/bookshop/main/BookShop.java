package com.douzone.bookshop.main;

import com.douzone.bookshop.test.BookDAOTest;
import com.douzone.bookshop.test.CartDAOTest;
import com.douzone.bookshop.test.CategoryDAOTest;
import com.douzone.bookshop.test.MemberDAOTest;
import com.douzone.bookshop.test.OrderDAOTest;

public class BookShop {

	public static void main(String[] args) {
		MemberDAOTest.findAllTest();
		CategoryDAOTest.findAllTest();
		BookDAOTest.findAllTest();
		CartDAOTest.findAllTest(5);
		OrderDAOTest.findAllTest();

	}

}
