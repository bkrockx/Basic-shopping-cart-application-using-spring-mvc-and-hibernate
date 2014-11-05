package Dto;

import java.util.*;

import com.mysql.jdbc.Blob;

import Model.*;

public class ItemDTO {
	
	private Integer itemId;
	private String itemName;
	private String itemContent;
	private int itemPrice;
	private byte[] itemImage;

	private List<Category> category;
	
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
	
	public List<Category> getCategory() {
		return category;
	}
	
	public void setCategory(List<Category> category) {
		this.category = category;
	}
	
	public byte[] getItemImage() {
		return itemImage;
	}

	public void setItemImage(byte[] itemImage) {
		this.itemImage = itemImage;
	}
}
