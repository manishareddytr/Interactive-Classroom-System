package com.ts.dto;

public class Chat {

	private String username;
	private String message;
	private int forumId;
	private int type;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getforumId() {
		return forumId;
	}
	public void setforumId(int forumId) {
		this.forumId = forumId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "chat [" + "userName=" + username + ", message=" + message + ", forum ID=" + forumId + ", Message type=" + type +"]";
	}
}
