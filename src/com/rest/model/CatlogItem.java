package com.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CatlogItem {
	@JsonProperty("itemId")
	int itemId;
	@JsonProperty("name")
	String itemName;
	@JsonProperty("description")
	String itemDescription;
	@JsonProperty("image")
	String image;
	@JsonProperty("quantity")
	int quantityAvailable;
	@JsonProperty("price")
	int price;
	
	public CatlogItem(int id,String name,String desc,String image,int quant,int price){
		this.itemId = id;
		this.itemName = name;
		this.itemDescription = desc;
		this.image = image;
		this.price = price;
	}
	public int getItemId() {
		return itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public String getImage() {
		return image;
	}
	public int getQuantityAvailable() {
		return quantityAvailable;
	}
	public int getPrice() {
		return price;
	}
}
