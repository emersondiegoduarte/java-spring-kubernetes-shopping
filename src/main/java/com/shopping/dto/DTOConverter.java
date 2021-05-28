package com.shopping.dto;

import java.util.stream.Collectors;

import com.shopping.entity.Item;
import com.shopping.entity.Shop;

import dto.ItemDTO;
import dto.ShopDTO;

public class DTOConverter {
	
	public	static	ItemDTO	convert(Item item) {
		ItemDTO	itemDTO	=	new	ItemDTO();
		itemDTO.setProductIdentifier(item.getProductIdentifier());
		itemDTO.setPrice(item.getPrice());
		return	itemDTO;
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
						.map(DTOConverter::convert)
						.collect(Collectors.toList()));
		return	shopDTO;
	}
	
	public	static	Item	convert(ItemDTO	itemDTO) {
		Item item	=	new	Item();
		item.setProductIdentifier(itemDTO.getProductIdentifier());
		item.setPrice(itemDTO.getPrice());
		return	item;
	}
	
	public	static	Shop convert(ShopDTO	shopDTO) {
		Shop shop =	new	Shop();
		shop.setUserIdentifier(shopDTO.getUserIdentifier());
		shop.setTotal(shopDTO.getTotal());
		shop.setDate(shopDTO.getDate());
		shop.setItens(shopDTO
						.getItens()
						.stream()
						.map(DTOConverter::convert)
						.collect(Collectors.toList()));
		return	shop;
	}
}
