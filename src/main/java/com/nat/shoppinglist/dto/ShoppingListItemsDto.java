package com.nat.shoppinglist.dto;

import org.springframework.beans.factory.annotation.Value;

public interface ShoppingListItemsDto {
	long getid();
	
	
	@Value("#{target.item_id}")
	String getItemId();

	@Value("#{target.item_name}")
	String getItemName();

	int getPrice();
}
