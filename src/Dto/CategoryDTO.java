package Dto;

import java.util.*;

import Model.*;

public class CategoryDTO {
	
	private Integer categoryId;
	private String categoryName;
	private List<Item> Item;
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<Item> getItem() {
		return Item;
	}
	public void setItem(List<Item> item) {
		Item = item;
	}
	
}
