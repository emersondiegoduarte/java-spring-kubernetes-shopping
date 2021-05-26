package com.shopping.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.shopping.dto.ShopReportDTO;
import com.shopping.entity.Shop;

public interface ReportRepository {
	
	
	public List<Shop> getShopByFilters(LocalDateTime dataInicio, LocalDateTime dataFim, Float valorMinimo);
	
	public ShopReportDTO getReportByDate(LocalDateTime dataInicio, LocalDateTime dataFim);

}
