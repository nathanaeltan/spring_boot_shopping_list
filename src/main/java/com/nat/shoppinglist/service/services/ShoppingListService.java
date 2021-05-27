package com.nat.shoppinglist.service.services;

import java.util.List;

import com.nat.shoppinglist.dto.ShoppingListDto;
import com.nat.shoppinglist.dto.UserDto;
import com.nat.shoppinglist.models.entities.ShoppingListEntity;

public interface ShoppingListService {

	ShoppingListDto addShoppingList(ShoppingListEntity shoppingList, UserDto userDto);
	
	List<ShoppingListDto> getShoppingList(long userId);
	
	ShoppingListDto getOneShoppingList(long userId, String listId);
	ShoppingListDto getShoppingListById(String listId);

	void deleteShoppingList(String listId);

	ShoppingListDto updateShoppingList(String listId, ShoppingListDto shoppingListDto);

	void addToList(String itemId, String listId);

	void deleteItemFromList(String itemId, String listId);

	
}
