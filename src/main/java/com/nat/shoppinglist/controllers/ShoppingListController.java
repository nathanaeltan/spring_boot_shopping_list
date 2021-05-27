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

import com.nat.shoppinglist.dto.ShoppingListDto;
import com.nat.shoppinglist.dto.UserDto;
import com.nat.shoppinglist.models.entities.ShoppingListEntity;
import com.nat.shoppinglist.models.request.ModifyListItemRequestModel;
import com.nat.shoppinglist.models.response.ShoppingListResponseModel;
import com.nat.shoppinglist.service.services.ShoppingListService;
import com.nat.shoppinglist.service.services.UserService;

@RestController
@RequestMapping("api/v1/shoppingLists")
public class ShoppingListController {

	@Autowired
	ShoppingListService shoppingListService;

	@Autowired
	UserService userService;

	@GetMapping("/{userId}")
	public List<ShoppingListResponseModel> getShoppingList(@PathVariable String userId) {
		List<ShoppingListResponseModel> returnValue = new ArrayList<>();
		UserDto userDto = userService.getUserByUserId(userId);
		List<ShoppingListDto> shoppingList = shoppingListService.getShoppingList(userDto.getId());

		for (ShoppingListDto shoppingListDto : shoppingList) {
			ModelMapper modelMapper = new ModelMapper();
			ShoppingListResponseModel shoppingListEntity = modelMapper.map(shoppingListDto,
					ShoppingListResponseModel.class);
			returnValue.add(shoppingListEntity);
		}
		return returnValue;
	}

	@GetMapping("/list/{listId}")
	public ShoppingListResponseModel getOneShoppingList(@PathVariable String listId) {
		ModelMapper modelMapper = new ModelMapper();
		ShoppingListDto shoppingList = shoppingListService.getShoppingListById(listId);
		ShoppingListResponseModel returnValue = modelMapper.map(shoppingList, ShoppingListResponseModel.class);
		return returnValue;

	}

	@PostMapping("/{userId}")
	public ShoppingListResponseModel createShoppingList(@RequestBody ShoppingListEntity shoppingList,
			@PathVariable String userId) {
		UserDto userDto = userService.getUserByUserId(userId);
		ModelMapper modelMapper = new ModelMapper();

		ShoppingListDto createdList = shoppingListService.addShoppingList(shoppingList, userDto);
		ShoppingListResponseModel returnValue = modelMapper.map(createdList, ShoppingListResponseModel.class);
		return returnValue;
	}

	@DeleteMapping("/{listId}")
	public void deleteShoppingList(@PathVariable String listId) {
		shoppingListService.deleteShoppingList(listId);
	}

	@PutMapping("/{listId}")
	public ShoppingListResponseModel updateShoppingList(@PathVariable String listId,
			@RequestBody ShoppingListEntity shoppingList) {
		ModelMapper modelMapper = new ModelMapper();
		ShoppingListDto shoppingListDto = modelMapper.map(shoppingList, ShoppingListDto.class);
		ShoppingListDto updatedList = shoppingListService.updateShoppingList(listId, shoppingListDto);
		ShoppingListResponseModel returnValue = modelMapper.map(updatedList, ShoppingListResponseModel.class);
		
		return returnValue;
	}
	
	@PostMapping("/list/{listId}")
	public ShoppingListResponseModel addOneItemToShoppingList(@PathVariable String listId, @RequestBody ModifyListItemRequestModel item ) {
		ModelMapper modelMapper = new ModelMapper();
		shoppingListService.addToList(item.getItemId(), listId);
		
		ShoppingListDto shoppingList = shoppingListService.getShoppingListById(listId);
		ShoppingListResponseModel returnValue = modelMapper.map(shoppingList, ShoppingListResponseModel.class);
		return returnValue;
		
	}
	
	@DeleteMapping("/list/{listId}")
	public ShoppingListResponseModel deleteOneItemFromShoppingList(@PathVariable String listId, @RequestBody ModifyListItemRequestModel item ) {
		ModelMapper modelMapper = new ModelMapper();
		System.out.println(item.getItemId());
		System.out.println(listId);
		shoppingListService.deleteItemFromList(item.getItemId(), listId);
		
		ShoppingListDto shoppingList = shoppingListService.getShoppingListById(listId);
		ShoppingListResponseModel returnValue = modelMapper.map(shoppingList, ShoppingListResponseModel.class);
		return returnValue;
		
	}

}
