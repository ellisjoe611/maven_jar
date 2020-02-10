package com.douzone.bookshop.vo;

public class CartVO {
	private Long book_no;
	private Long member_no;

	private String book_name;
	private Long count;
	private Long price;

	public Long getBook_no() {
		return book_no;
	}

	public void setBook_no(Long book_no) {
		this.book_no = book_no;
	}

	public Long getMember_no() {
		return member_no;
	}

	public void setMember_no(Long member_no) {
		this.member_no = member_no;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CartVO [book_no=" + book_no + ", member_no=" + member_no + ", book_name=" + book_name + ", count="
				+ count + ", TOTAL : " + price + " 원]";
	}

}
