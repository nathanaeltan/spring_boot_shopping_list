package com.nat.shoppinglist.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nat.shoppinglist.dto.ShoppingListItemsDto;
import com.nat.shoppinglist.models.entities.ShoppingListEntity;

@Repository
public interface ShoppingListRepository extends CrudRepository<ShoppingListEntity, Long> {

	@Query(value = "SELECT * FROM shopping_lists u WHERE u.list_name = :listName AND u.users_id = :userId LIMIT 1", nativeQuery = true)
	ShoppingListEntity findListByUserIdAndListName(@Param("listName") String listName, @Param("userId") long userId);

	@Query(value = "SELECT * FROM shopping_lists u WHERE u.users_id = :userId", nativeQuery = true)
	List<ShoppingListEntity> findAllByUserId(Long userId);

	@Query(value = "SELECT * FROM shopping_lists u WHERE u.users_id = :userId AND u.list_id = :listId LIMIT 1", nativeQuery = true)
	ShoppingListEntity findOneByUserIdAndListId(long userId, String listId);
	
	@Query(value = "SELECT * FROM shopping_lists u WHERE u.list_id = :listId LIMIT 1", nativeQuery = true)
	ShoppingListEntity findOneByListId(String listId);
	
	@Query(value="select child1.*  from shoppinglist_items parent "
			+ "inner join shopping_lists child2 on parent.shopping_list_id = child2.id "
			+ "inner join shopping_items child1 on parent.shopping_item_id = child1.id "
			
			+ "WHERE parent.shopping_list_id = :listId", nativeQuery = true)
	List<ShoppingListItemsDto> findListItems(long listId);
	
	@Modifying
	@Query(value="INSERT INTO shoppinglist_items (shopping_list_id, shopping_item_id) VALUES (:listID, :itemID)", nativeQuery = true)
	@Transactional
	void addItemToList(long listID, long itemID);



	
	@Modifying
	@Query(value="DELETE FROM shoppinglist_items t WHERE (t.shopping_list_id = :listID AND t.shopping_item_id = :itemID)" ,nativeQuery = true)
	@Transactional
	void deleteItemFromList(long listID, long itemID);
}
