package com.shopping.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dto.ShopDTO;
import com.shopping.dto.ShopReportDTO;
import com.shopping.entity.Shop;
import com.shopping.repository.ShopRepository;

@Service
public class ReportService {

	
	@Autowired
	private ShopRepository shopRepository;
	
	public List<ShopDTO> getShopsByFilter(LocalDateTime dataInicio, LocalDateTime dataFim, Float valorMinimo){
		
		List<Shop> lista = shopRepository.getShopByFilters(dataInicio, dataFim, valorMinimo);
		return lista.stream()
				.map(ShopDTO::convert)
				.collect(Collectors.toList());
		
	}
	
	public ShopReportDTO getReportByDate(LocalDateTime	dataInicio,	LocalDateTime	dataFim) {
		return	shopRepository.getReportByDate(dataInicio,dataFim);
	}

}
