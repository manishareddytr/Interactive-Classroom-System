package com.ts.dto;

public class Student {
		private String name;
		private String rollno;
		private String username;
		private String password;
		private String code;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getrollno() {
			return rollno;
		}
		public void setrollno(String rollno) {
			this.rollno = rollno;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		@Override
		public String toString() {
			return "Student [Name=" + name + ", RollNo=" + rollno + ", Class code=" + code
					+ ", userName=" + username + ", password=" + password + "]";
		}
}

