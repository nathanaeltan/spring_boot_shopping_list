package com.nat.shoppinglist.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nat.shoppinglist.dto.ShoppingItemDto;
import com.nat.shoppinglist.models.entities.ShoppingItemEntity;
import com.nat.shoppinglist.models.response.ShoppingItemResponseModel;
import com.nat.shoppinglist.service.services.ShoppingItemService;
import com.nat.shoppinglist.service.services.UserService;

@RestController
@RequestMapping("api/v1/shoppingItems")
public class ShoppingItemController {

	@Autowired
	ShoppingItemService shoppingItemService;

	@Autowired
	UserService userService;

	@GetMapping
	public List<ShoppingItemResponseModel> getAllShoppingItems() {
		List<ShoppingItemResponseModel> returnValue = new ArrayList<>();
		List<ShoppingItemDto> shoppingItems = shoppingItemService.getAllShoppingItems();

		for (ShoppingItemDto shoppingItemDto : shoppingItems) {
			ModelMapper modelMapper = new ModelMapper();
			ShoppingItemResponseModel shoppingItemEntity = modelMapper.map(shoppingItemDto,
					ShoppingItemResponseModel.class);
			returnValue.add(shoppingItemEntity);
		}
		return returnValue;
	}

	@PostMapping
	public ShoppingItemResponseModel createShoppingItem(@RequestBody ShoppingItemEntity shoppingItem) {
		ModelMapper modelMapper = new ModelMapper();
		ShoppingItemDto createdItem = shoppingItemService.addItem(shoppingItem);
		ShoppingItemResponseModel returnValue = modelMapper.map(createdItem, ShoppingItemResponseModel.class);

		return returnValue;
	}

	@DeleteMapping("/{itemId}")
	public void deleteShoppingItem(@PathVariable String itemId) {
		shoppingItemService.deleteShoppingItem(itemId);
	}

	@PutMapping("/{itemId}")
	public ShoppingItemResponseModel updateShoppingItem(@PathVariable String itemId,
			@RequestBody ShoppingItemEntity shoppingItem) {
		ModelMapper modelMapper = new ModelMapper();
		ShoppingItemDto shoppingItemDto = modelMapper.map(shoppingItem, ShoppingItemDto.class);
		ShoppingItemDto updateItem = shoppingItemService.updateShoppingItem(itemId, shoppingItemDto);
		ShoppingItemResponseModel returnValue = modelMapper.map(updateItem, ShoppingItemResponseModel.class);
		return returnValue; 
	}

}
