package com.shopping.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.shopping.entity.Item;
import com.shopping.entity.Shop;

public class ShopDTO {
	
	private Long id;
	
	private String userIdentifier;
	
	private LocalDateTime date;
	
	private float total;
	
	
	private List<ItemDTO> itens;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUserIdentifier() {
		return userIdentifier;
	}


	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}


	public LocalDateTime getDate() {
		return date;
	}


	public void setDate(LocalDateTime date) {
		this.date = date;
	}


	public float getTotal() {
		return total;
	}


	public void setTotal(float total) {
		this.total = total;
	}


	public List<ItemDTO> getItens() {
		return itens;
	}


	public void setItens(List<ItemDTO> itens) {
		this.itens = itens;
	}
	
	public	static	ShopDTO convert(Shop	shop) {
		ShopDTO shopDTO =	new	ShopDTO();
		shopDTO.setId(shop.getId());
		shopDTO.setUserIdentifier(shop.getUserIdentifier());
		shopDTO.setTotal(shop.getTotal());
		shopDTO.setDate(shop.getDate());
		shopDTO.setItens(shop
						.getItens()
						.stream()
						.map(ItemDTO::convert)
						.collect(Collectors.toList()));
		return	shopDTO;
	}

}
