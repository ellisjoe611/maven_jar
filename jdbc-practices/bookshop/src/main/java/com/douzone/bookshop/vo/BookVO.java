package com.douzone.bookshop.vo;

public class BookVO {
	private Long no;
	private Long category_no;
	
	private String category_name;
	private String name;
	private Long price;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getCategory_no() {
		return category_no;
	}

	public void setCategory_no(Long category_no) {
		this.category_no = category_no;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	@Override
	public String toString() {
		return "BookVO [no=" + no + ", name=" + name + ", price=" + price + ", category_no=" + category_no
				+ ", category_name=" + category_name + "]";
	}

}
