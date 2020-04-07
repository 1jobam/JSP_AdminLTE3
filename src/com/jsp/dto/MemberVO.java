package com.jsp.dto;

/**
 * @author PC-02
 *
 */
public class MemberVO {
	
	private String id; //아이디
	private String pwd; //패스워드
	private String phone; //전화번호
	private String name; // 이름
	private String email; //이메일
	private String picture; //사진파일명
	private String authority; //권한
	private int enabled; //사용여부
	private String address; //주소
	
	public MemberVO() {}

	public MemberVO(String id, String pwd, String phone, String name, String email, String picture, String authority,
			int enabled, String address) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.phone = phone;
		this.name = name;
		this.email = email;
		this.picture = picture;
		this.authority = authority;
		this.enabled = enabled;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pwd=" + pwd + ", phone=" + phone + ", name=" + name + ", email=" + email
				+ ", picture=" + picture + ", authority=" + authority + ", enabled=" + enabled + ", address=" + address
				+ "]";
	}
	
}
