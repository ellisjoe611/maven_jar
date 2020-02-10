package com.douzone.bookshop.vo;

public class OrderVO {
	private Long no;
	private Long member_no;

	private String no_full;
	private String member_name;
	private String member_email;
	private Long price;
	private String address;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Long getMember_no() {
		return member_no;
	}

	public void setMember_no(Long member_no) {
		this.member_no = member_no;
	}

	public String getNo_full() {
		return no_full;
	}

	public void setNo_full(String no_full) {
		this.no_full = no_full;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "OrderVO [no=" + no + ", member_no=" + member_no + ", no_full=" + no_full + ", member_name="
				+ member_name + ", member_email=" + member_email + ", price=" + price + ", address=" + address + "]";
	}

}
