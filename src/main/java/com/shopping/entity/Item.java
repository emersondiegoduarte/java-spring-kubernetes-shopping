package com.shopping.entity;

import javax.persistence.Embeddable;

import com.shopping.dto.ItemDTO;

@Embeddable
public class Item {
	
	private String productIdentifier;
	
	private float price;

	public String getProductIdentifier() {
		return productIdentifier;
	}

	public void setProductIdentifier(String productIdentifier) {
		this.productIdentifier = productIdentifier;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	
	public	static	Item	convert(ItemDTO	itemDTO) {
		Item item	=	new	Item();
		item.setProductIdentifier(itemDTO.getProductIdentifier());
		item.setPrice(itemDTO.getPrice());
		return	item;
}
	

}
