package com.shopping.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dto.ShopDTO;
import com.shopping.entity.Item;
import com.shopping.entity.Shop;
import com.shopping.repository.ShopRepository;

@Service
public class ShopService {

	
	@Autowired
	private ShopRepository shopRepository;
	
	public List<ShopDTO> getAll(){
		List<Shop> lista = shopRepository.findAll();
		return lista.stream()
				.map(ShopDTO::convert)
				.collect(Collectors.toList());
	}
	
	public List<ShopDTO> getByUser(String userIdentifier){
		List<Shop> lista = shopRepository.findAllByUserIdentifier(userIdentifier);
		return lista.stream()
				.map(ShopDTO::convert)
				.collect(Collectors.toList());
	}
	
	public List<ShopDTO> getByDate(LocalDateTime date){
		List<Shop> lista = shopRepository.findAllByDateGreaterThan(date);
		return lista.stream()
				.map(ShopDTO::convert)
				.collect(Collectors.toList());
	}
	
	public ShopDTO findById(Long id){
		Optional<Shop> shop = shopRepository.findById(id);
		if(shop.isPresent()) {
			return ShopDTO.convert(shop.get());
		}
		return null;
	}
	
	public ShopDTO save(ShopDTO shopDTO){
		Shop shop = Shop.convert(shopDTO);
		float total = shop.getItens().stream()
					  .map(item -> item.getPrice())
					  .reduce((float)0, Float::sum);
		
//		float total2 = shop.getItens().stream()
//				  .map(Item::getPrice)
//				  .reduce((float)0, Float::sum);
		
		shop.setTotal(total);
		shop.setDate(LocalDateTime.now());
		shop = shopRepository.save(shop);
		return ShopDTO.convert(shop);
	}
}
