package com.nat.shoppinglist.models.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name="shoppingItems")
@Table
public class ShoppingItemEntity implements Serializable{


	private static final long serialVersionUID = 4747299454505210803L;
	
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false, length = 50)
	private String itemName;
	
	@Column(nullable = false)
	private	int price;
	
	@Column(length=30, nullable=false)
	private String itemId;
	
	@ManyToMany(mappedBy = "shoppingItems" ,fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<ShoppingListEntity> shoppingList;
	



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public List<ShoppingListEntity> getShoppingList() {
		return shoppingList;
	}

	public void setShoppingList(List<ShoppingListEntity> shoppingList) {
		this.shoppingList = shoppingList;
	}
	
}
