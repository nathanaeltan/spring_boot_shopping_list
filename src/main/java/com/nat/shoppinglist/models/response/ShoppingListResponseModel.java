package com.nat.shoppinglist.models.response;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListResponseModel {
	private String listId;
	private String listName;
	
	private List<ShoppingItemResponseModel> items = new ArrayList<>();
	
	

	public List<ShoppingItemResponseModel> getItems() {
		return items;
	}

	public void setItems(List<ShoppingItemResponseModel> items) {
		this.items = items;
	}

	public String getListId() {
		return listId;
	}

	public void setListId(String listId) {
		this.listId = listId;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

}
