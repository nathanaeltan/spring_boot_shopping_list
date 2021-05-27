package com.nat.shoppinglist.service.services;

import java.util.List;

import com.nat.shoppinglist.dto.ShoppingItemDto;
import com.nat.shoppinglist.models.entities.ShoppingItemEntity;

public interface ShoppingItemService {

	ShoppingItemDto addItem(ShoppingItemEntity shoppingItem);

	List<ShoppingItemDto> getAllShoppingItems();

	void deleteShoppingItem(String itemId);

	ShoppingItemDto updateShoppingItem(String listId, ShoppingItemDto shoppingItemDto);

}
