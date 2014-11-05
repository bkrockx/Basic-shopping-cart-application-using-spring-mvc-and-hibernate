package Dto;

import java.util.*;
import Model.*;

public class UserDTO {

	private Integer userId;
	private String userName;
	private String password;
	private List<Role> role;
	private List<Item> item;
	
	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Role> getRole() {
		return role;
	}
	
	public void setRole(List<Role> role) {
		this.role = role;
	}
	
}
