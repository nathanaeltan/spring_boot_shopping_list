package com.nat.shoppinglist.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nat.shoppinglist.dto.ShoppingItemDto;
import com.nat.shoppinglist.models.entities.ShoppingItemEntity;
import com.nat.shoppinglist.repository.ShoppingItemRepository;
import com.nat.shoppinglist.service.services.ShoppingItemService;
import com.nat.shoppinglist.utils.Utils;

@Service
public class ShoppingItemServiceImpl implements ShoppingItemService {

	@Autowired
	ShoppingItemRepository shoppingitemRepo;

	@Autowired
	Utils utils;

	@Override
	public ShoppingItemDto addItem(ShoppingItemEntity shoppingItem) {
		// TODO Auto-generated method stub
		ModelMapper modelMapper = new ModelMapper();
		ShoppingItemEntity shoppingItemEntity = modelMapper.map(shoppingItem, ShoppingItemEntity.class);
		shoppingItemEntity.setItemId(utils.generateShoppingListId(30));
		shoppingitemRepo.save(shoppingItemEntity);
		ShoppingItemDto returnValue = modelMapper.map(shoppingItemEntity, ShoppingItemDto.class);

		return returnValue;
	}

	@Override
	public List<ShoppingItemDto> getAllShoppingItems() {
		List<ShoppingItemDto> returnValue = new ArrayList<>();
		List<ShoppingItemEntity> shoppingItemsEntitys = (List<ShoppingItemEntity>) shoppingitemRepo.findAll();

		for (ShoppingItemEntity shoppingItemEntity : shoppingItemsEntitys) {
			ModelMapper modelMapper = new ModelMapper();
			ShoppingItemDto shoppingItemDto = modelMapper.map(shoppingItemEntity, ShoppingItemDto.class);
			returnValue.add(shoppingItemDto);
		}

		return returnValue;
	}

	@Override
	public void deleteShoppingItem(String itemId) {
		ShoppingItemEntity shoppingItemEntity = shoppingitemRepo.findOneByItemId(itemId);
		if (shoppingItemEntity == null)
			throw new RuntimeException("Item does not exist");

		shoppingitemRepo.delete(shoppingItemEntity);

	}

	@Override
	public ShoppingItemDto updateShoppingItem(String listId, ShoppingItemDto shoppingItemDto) {
		ModelMapper modelMapper = new ModelMapper();

		ShoppingItemEntity shoppingItemEntity = shoppingitemRepo.findOneByItemId(listId);

		if (shoppingItemEntity == null)
			throw new RuntimeException("Item does not exist");
		
		
		shoppingItemEntity.setItemName(shoppingItemDto.getItemName());
		shoppingItemEntity.setPrice(shoppingItemDto.getPrice());
		ShoppingItemEntity updatedShoppingItem = shoppingitemRepo.save(shoppingItemEntity);
		
		ShoppingItemDto returnValue = modelMapper.map(updatedShoppingItem, ShoppingItemDto.class);
		
		return returnValue;
	}
}
