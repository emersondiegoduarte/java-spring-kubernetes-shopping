package com.shopping.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import com.shopping.dto.ShopDTO;

@Entity(name = "shop")
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="user_identifier")
	private String userIdentifier;
	
	private LocalDateTime date;
	
	private float total;
	
	
	@ElementCollection
	@CollectionTable(name = "item", 
					 joinColumns = @JoinColumn(name="shop_id"))
	private List<Item> itens;


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


	public List<Item> getItens() {
		return itens;
	}


	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	
	public	static	Shop convert(ShopDTO	shopDTO) {
		Shop shop =	new	Shop();
		shop.setUserIdentifier(shopDTO.getUserIdentifier());
		shop.setTotal(shopDTO.getTotal());
		shop.setDate(shopDTO.getDate());
		shop.setItens(shopDTO
						.getItens()
						.stream()
						.map(Item::convert)
						.collect(Collectors.toList()));
		return	shop;
	}
}
