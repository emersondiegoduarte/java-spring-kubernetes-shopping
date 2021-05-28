package com.shopping.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dto.DTOConverter;
import com.shopping.entity.Shop;
import com.shopping.repository.ShopRepository;

import dto.ItemDTO;
import dto.ProductDTO;
import dto.ShopDTO;
import dto.UserDTO;

@Service
public class ShopService {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ShopRepository shopRepository;
	
	public List<ShopDTO> getAll(){
		List<Shop> lista = shopRepository.findAll();
		return lista.stream()
				.map(DTOConverter::convert)
				.collect(Collectors.toList());
	}
	
	public List<ShopDTO> getByUser(String userIdentifier){
		List<Shop> lista = shopRepository.findAllByUserIdentifier(userIdentifier);
		return lista.stream()
				.map(DTOConverter::convert)
				.collect(Collectors.toList());
	}
	
	public List<ShopDTO> getByDate(LocalDateTime date){
		List<Shop> lista = shopRepository.findAllByDateGreaterThan(date);
		return lista.stream()
				.map(DTOConverter::convert)
				.collect(Collectors.toList());
	}
	
	public ShopDTO findById(Long id){
		Optional<Shop> shop = shopRepository.findById(id);
		if(shop.isPresent()) {
			return DTOConverter.convert(shop.get());
		}
		return null;
	}
	
	public ShopDTO save(ShopDTO shopDTO){
		
		UserDTO user = userService.getUserByCpf(shopDTO.getUserIdentifier());
		
		if(user == null) {
			return null;
		}
		
		if(!validateProducts(shopDTO.getItens())) {
			return null;
		}
		
		Shop shop = DTOConverter.convert(shopDTO);
		float total = shop.getItens().stream()
					  .map(item -> item.getPrice())
					  .reduce((float)0, Float::sum);
		
//		float total2 = shop.getItens().stream()
//				  .map(Item::getPrice)
//				  .reduce((float)0, Float::sum);
		
		shop.setTotal(total);
		shop.setDate(LocalDateTime.now());
		shop = shopRepository.save(shop);
		return  DTOConverter.convert(shop);
	}
	
	private Boolean validateProducts(List<ItemDTO> items) {
		Boolean isValid =  Boolean.TRUE;
		for (ItemDTO itemDTO : items) {
			ProductDTO productDTO = productService.findByProductIdentifier(itemDTO.getProductIdentifier());
			if(productDTO == null) {
				isValid = Boolean.FALSE;
				break;
			}
			itemDTO.setPrice(productDTO.getPreco().floatValue());
		}
		return isValid;
	}
}
