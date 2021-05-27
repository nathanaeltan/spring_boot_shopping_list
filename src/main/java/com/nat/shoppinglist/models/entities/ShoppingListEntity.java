package com.nat.shoppinglist.models.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "shoppingLists")
@Table
public class ShoppingListEntity implements Serializable {

	private static final long serialVersionUID = -1032995570061648636L;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false, length = 50)
	private String listName;

	@ManyToOne
	@JoinColumn(name = "users_id")
	private UserEntity userDetails;

	@Column(length = 30, nullable = false)
	private String listId;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "shoppinglist_items",
	joinColumns = @JoinColumn(name = "shoppingList_id"), 
	inverseJoinColumns = @JoinColumn(name = "shoppingItem_id"))
	private List<ShoppingItemEntity> shoppingItems;

	public String getListId() {
		return listId;
	}

	public void setListId(String listId) {
		this.listId = listId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public UserEntity getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserEntity userDetails) {
		this.userDetails = userDetails;
	}

	public List<ShoppingItemEntity> getShoppingItems() {
		return shoppingItems;
	}

	public void setShoppingItems(List<ShoppingItemEntity> shoppingItems) {
		this.shoppingItems = shoppingItems;
	}

}
