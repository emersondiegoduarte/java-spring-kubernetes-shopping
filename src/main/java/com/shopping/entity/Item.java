package com.shopping.entity;

import javax.persistence.Embeddable;

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
	
	
}
