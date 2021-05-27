package com.nat.shoppinglist.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nat.shoppinglist.models.entities.ShoppingItemEntity;

public interface ShoppingItemRepository extends CrudRepository<ShoppingItemEntity, Long> {

	@Query(value = "SELECT * FROM shopping_items u WHERE u.item_id = :itemId LIMIT 1", nativeQuery = true)
	ShoppingItemEntity findOneByItemId(String itemId);

}
