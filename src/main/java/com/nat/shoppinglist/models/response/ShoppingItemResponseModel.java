package com.nat.shoppinglist.models.response;

public class ShoppingItemResponseModel {
	
	private String itemId;
	private String itemName;
	private int price;
	public String getItemId() {
		return itemId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	

}
