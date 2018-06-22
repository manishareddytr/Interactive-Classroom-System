package com.ts.dto;

public class subject {
	private String name;
	private String dept;
	private String teacherId;
	private int forumId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getteacherId() {
		return teacherId;
	}
	public void setteacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public int getforumId() {
		return forumId;
	}
	public void setforumId(int forumId) {
		this.forumId = forumId;
	}
	
	@Override
	public String toString() {
		return "Subject [Name=" + name + ", Department=" + dept + ", Teacher ID=" + teacherId
				+ ", Forum ID=" + forumId+"]";
	}
}
