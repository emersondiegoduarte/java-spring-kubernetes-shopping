package com.shopping.dto;

import javax.validation.constraints.NotBlank;

import com.shopping.entity.Item;
import com.sun.istack.NotNull;

public class ItemDTO {

	@NotBlank
	private	String	productIdentifier;
	@NotNull
	private	Float	price;
	
	
	
	public String getProductIdentifier() {
		return productIdentifier;
	}



	public void setProductIdentifier(String productIdentifier) {
		this.productIdentifier = productIdentifier;
	}



	public Float getPrice() {
		return price;
	}



	public void setPrice(Float price) {
		this.price = price;
	}



	public	static	ItemDTO	convert(Item item) {
		ItemDTO	itemDTO	=	new	ItemDTO();
		itemDTO.setProductIdentifier(item.getProductIdentifier());
		itemDTO.setPrice(item.getPrice());
		return	itemDTO;
}
}
