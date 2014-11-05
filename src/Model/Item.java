package Model;

import java.io.Serializable;

import javassist.bytecode.ByteArray;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.sql.Blob;

@Entity
@Table(name="item")
public class Item implements Serializable{
	
	@Id
	@Column(name="ItemId")
	@GeneratedValue
	private Integer itemId;
	
	@Column(name="ItemName")
	private String itemName;
	
	@Column(name="ItemContent")
	private String itemContent;
	
	@Column(name="ItemPrice")
	private int itemPrice;
	
	@Column(name="ItemImage")
	private byte[] itemImage;
	
	//---------------------------------------item mapped to category------------------------------------------//
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(
			name="CategoryItem",
			joinColumns= @JoinColumn(name="ITEM_ID")
	)
	private Category category;
	//--------------------------------------------------------------------------------------------------------//
	
	//------------------------------------------Item mapped to User--------------------------------------------//
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(
			name="UserItem",
			joinColumns= @JoinColumn(name="ITEM_ID"),
			inverseJoinColumns = @JoinColumn(name="USER_ID")
	)
	private User user;
	//--------------------------------------------------------------------------------------------------------//
	
	//-------------------------------------item mapped to billable--------------------------------------------//
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(
			name="BillableItem",
			joinColumns = @JoinColumn(name = "ITEM_ID"),
			inverseJoinColumns = @JoinColumn(name="BILLABLE_ID")
	)
	public billable billable;
	//--------------------------------------------------------------------------------------------------------//
	
	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemContent() {
		return itemContent;
	}

	public void setItemContent(String itemContent) {
		this.itemContent = itemContent;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public byte[] getItemImage() {
		return itemImage;
	}

	public void setItemImage(byte[] itemImage) {
		this.itemImage = itemImage;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public billable getBillable() {
		return billable;
	}

	public void setBillable(billable billable) {
		this.billable = billable;
	}

}

