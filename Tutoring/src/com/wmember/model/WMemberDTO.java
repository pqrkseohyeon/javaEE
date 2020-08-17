package com.wmember.model;

public class WMemberDTO {
	private int num;
	private String name;
	private String userid;
	private String pwd;
	private String email;
	
	private int postcode;
	private String address;
	private String detailAddress;
	private String extraAddress;
	
	private String classnum;
	private String reg_date;
	private int admin;
	
	public int getPostcode() {
		return postcode;
	}
	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}
	public String getAddress() {
		return address == null ? "" : address.trim();
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDetailAddress() {
		return detailAddress == null ? "" : detailAddress.trim();
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getExtraAddress() {
		return extraAddress == null ? "" : extraAddress.trim();
	}
	public void setExtraAddress(String extraAddress) {
		this.extraAddress = extraAddress;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name == null ? "" : name.trim();
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserid() {
		return userid == null ? "" : userid.trim();
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPwd() {
		return pwd == null ? "" : pwd.trim();
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email == null ? "" : email.trim();
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getClassnum() {
		return classnum == null ? "" : classnum.trim();
	}
	public void setClassnum(String classnum) {
		this.classnum = classnum;
	}
	public String getReg_date() {
		return reg_date == null ? "" : reg_date.trim();
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
}
