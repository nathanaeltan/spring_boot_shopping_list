package com.nat.shoppinglist.dto;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListDto {
	private long id;
	private String listId;
	private String listName;
	private UserDto userDetails;
	private List<ShoppingItemDto> shoppingItems = new ArrayList<>();
	

	public List<ShoppingItemDto> getShoppingItems() {
		return shoppingItems;
	}

	public void setShoppingItems(List<ShoppingItemDto> shoppingItems) {
		this.shoppingItems = shoppingItems;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public UserDto getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDto userDetails) {
		this.userDetails = userDetails;
	}

}
