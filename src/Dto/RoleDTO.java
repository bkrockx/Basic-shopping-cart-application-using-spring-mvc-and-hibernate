package Dto;

import java.util.*;
import Model.*;

public class RoleDTO {
	
	private Integer roleId;
	private String roleName;
	private List<User>user;
	
	public Integer getRoleId() {
		return roleId;
	}
	
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public List<User> getUser() {
		return user;
	}
	
	public void setUser(List<User> user) {
		this.user = user;
	}

}
