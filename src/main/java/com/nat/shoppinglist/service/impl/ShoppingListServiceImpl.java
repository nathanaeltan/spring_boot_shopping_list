package com.nat.shoppinglist.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nat.shoppinglist.dto.ShoppingItemDto;
import com.nat.shoppinglist.dto.ShoppingListDto;
import com.nat.shoppinglist.dto.ShoppingListItemsDto;
import com.nat.shoppinglist.dto.UserDto;
import com.nat.shoppinglist.models.entities.ShoppingListEntity;
import com.nat.shoppinglist.models.entities.UserEntity;
import com.nat.shoppinglist.repository.ShoppingItemRepository;
import com.nat.shoppinglist.repository.ShoppingListRepository;
import com.nat.shoppinglist.service.services.ShoppingListService;
import com.nat.shoppinglist.utils.Utils;

@Service
public class ShoppingListServiceImpl implements ShoppingListService {

	@Autowired
	ShoppingListRepository shoppingListRepo;
	
	@Autowired
	ShoppingItemRepository shoppingItemRepo;

	@Autowired
	Utils utils;

	@Override
	public ShoppingListDto addShoppingList(ShoppingListEntity shoppingList, UserDto user) {

		if (shoppingListRepo.findListByUserIdAndListName(shoppingList.getListName(), user.getId()) != null) {
			throw new RuntimeException("You already have a shopping list with this name");
		}
		ModelMapper modelMapper = new ModelMapper();
		ShoppingListEntity shoppingListEntity = modelMapper.map(shoppingList, ShoppingListEntity.class);
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);
		shoppingListEntity.setUserDetails(userEntity);
		shoppingListEntity.setListId(utils.generateShoppingListId(30));
		shoppingListRepo.save(shoppingListEntity);
		ShoppingListDto returnValue = modelMapper.map(shoppingListEntity, ShoppingListDto.class);
		return returnValue;
	}

	@Override
	public List<ShoppingListDto> getShoppingList(long userId) {
		List<ShoppingListDto> returnValue = new ArrayList<>();
		List<ShoppingListEntity> shoppingLists = shoppingListRepo.findAllByUserId(userId);

		for (ShoppingListEntity shoppinglistEntity : shoppingLists) {
			ModelMapper modelMapper = new ModelMapper();
			ShoppingListDto shoppingListDto = modelMapper.map(shoppinglistEntity, ShoppingListDto.class);
			returnValue.add(shoppingListDto);

		}
		return returnValue;
	}

	@Override
	public ShoppingListDto getOneShoppingList(long userId, String listId) {

		ModelMapper modelMapper = new ModelMapper();
		ShoppingListEntity shoppingList = shoppingListRepo.findOneByUserIdAndListId(userId, listId);
		ShoppingListDto returnValue = modelMapper.map(shoppingList, ShoppingListDto.class);
		return returnValue;
	}

	@Override
	public ShoppingListDto getShoppingListById(String listId) {
		// TODO Auto-generated method stub
		ModelMapper modelMapper = new ModelMapper();
		ShoppingListEntity shoppingList = shoppingListRepo.findOneByListId(listId);
		System.out.println(shoppingList.getId());
		List<ShoppingListItemsDto> shoppingItems = shoppingListRepo.findListItems(shoppingList.getId());
		List<ShoppingItemDto> shoppingItemDtos = new ArrayList<>();
		for(ShoppingListItemsDto item : shoppingItems) {
			ShoppingItemDto shoppignDto = modelMapper.map(item, ShoppingItemDto.class);
			shoppingItemDtos.add(shoppignDto);
			
		}
		
		ShoppingListDto returnValue = modelMapper.map(shoppingList, ShoppingListDto.class);
		returnValue.setShoppingItems(shoppingItemDtos);
		return returnValue;
	}

	@Override
	public void deleteShoppingList(String listId) {
		ShoppingListEntity shoppingListEntity = shoppingListRepo.findOneByListId(listId);
		if (shoppingListEntity == null)
			throw new RuntimeException("List by " + listId + " does not exist");
		
		shoppingListRepo.delete(shoppingListEntity);

	}

	@Override
	public ShoppingListDto updateShoppingList(String listId, ShoppingListDto shoppingListDto) {
		ModelMapper modelMapper = new ModelMapper();

		ShoppingListEntity shoppingListEntity = shoppingListRepo.findOneByListId(listId);
		if (shoppingListEntity == null)
			throw new RuntimeException("List by " + listId + " does not exist");
		shoppingListEntity.setListName(shoppingListDto.getListName());
		ShoppingListEntity updatedShoppingList = shoppingListRepo.save(shoppingListEntity);
		
		ShoppingListDto returnValue = modelMapper.map(updatedShoppingList, ShoppingListDto.class);
		
		
		return returnValue;
	}

	@Override
	public void addToList(String itemId, String listId) {

		long listID = shoppingListRepo.findOneByListId(listId).getId();
		long itemID = shoppingItemRepo.findOneByItemId(itemId).getId();
		
		shoppingListRepo.addItemToList(listID, itemID);
		
	}

	@Override
	@Transactional
	public void deleteItemFromList(String itemId, String listId) {
		long listID = shoppingListRepo.findOneByListId(listId).getId();
		long itemID = shoppingItemRepo.findOneByItemId(itemId).getId();
		shoppingListRepo.deleteItemFromList(listID, itemID);
		
	}

}
