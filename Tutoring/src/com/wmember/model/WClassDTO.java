package com.wmember.model;

public class WClassDTO {
	private String clevel;
	private int classnum;
	private String classname;
	private int stu_num;
	private String stu_regdate;
	private String topic;
	private String content;
	private String uploadFile;
	
	public String getUploadFile() {
		return uploadFile == null ? "" : uploadFile.trim();
	}
	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getClevel() {
		return clevel == null ? "" : clevel.trim();
	}
	public void setClevel(String clevel) {
		this.clevel = clevel;
	}
	public int getClassnum() {
		return classnum;
	}
	public void setClassnum(int classnum) {
		this.classnum = classnum;
	}
	public String getClassname() {
		return classname == null ? "" : classname.trim();
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public int getStu_num() {
		return stu_num;
	}
	public void setStu_num(int stu_num) {
		this.stu_num = stu_num;
	}
	public String getStu_regdate() {
		return stu_regdate == null ? "" : stu_regdate.trim();
	}
	public void setStu_regdate(String stu_regdate) {
		this.stu_regdate = stu_regdate;
	}
	public String getTopic() {
		return topic == null ? "" : topic.trim();
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getContent() {
		return content == null ? "" : content.trim();
	}
	public void setContent(String content) {
		this.content = content;
	}
}