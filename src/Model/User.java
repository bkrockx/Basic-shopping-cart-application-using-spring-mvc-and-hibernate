package Model;

import java.io.Serializable;


import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User implements Serializable{
	
	@Id
	@Column(name="userId")
	@GeneratedValue
	private Integer userId;
	
	@Column(name="userName")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="userAddress")
	private String userAddress;
	
	//----------------------------------User to role mapping------------------------------------------//
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(
			name="UserRole",
			joinColumns = @JoinColumn(name="USER_ID"),
			inverseJoinColumns = @JoinColumn(name="ROLE_ID")
	)
	private Set<Role> role;
	//--------------------------------------------------------------------------------------------------//
	
	
	
	//-----------------------------------User to Item mapping-------------------------------------------//
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(
			name="UserItem",
			joinColumns = @JoinColumn(name="USER_ID"),
			inverseJoinColumns = @JoinColumn(name="ITEM_ID")
	)
	private Set<Item> item;
	//--------------------------------------------------------------------------------------------------//
	
	
	
	//-----------------------------user billable mapping---------------------------------------------//
	/*
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<billable> billable;
	*/
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(
			name="UserBillable",
			joinColumns = @JoinColumn(name="USER_ID"),
			inverseJoinColumns = @JoinColumn(name="BILLABLE_ID")
	)
	private Set<billable> billable;
	//-------------------------------------------------------------------------------------------------//
	
	
	
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
	
	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	
	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}
	
	public Set<Item> getItem() {
		return item;
	}

	public void setItem(Set<Item> item) {
		this.item = item;
	}
	
	public Set<billable> getBillable() {
		return billable;
	}

	public void setBillable(Set<billable> billable) {
		this.billable = billable;
	}
	
}
