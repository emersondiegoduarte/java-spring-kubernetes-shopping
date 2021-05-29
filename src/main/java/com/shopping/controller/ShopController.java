package com.shopping.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.dto.ShopReportDTO;
import com.shopping.service.ReportService;
import com.shopping.service.ShopService;

import dto.ShopDTO;

@RestController
public class ShopController {

	
	@Autowired
	private ShopService shopService;
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/shopping/")
	public List<ShopDTO> getShops() {		
		List<ShopDTO> produtos = shopService.getAll();		
		return produtos;
	}
	
	@GetMapping("/shopping/shopByUser/{userIdentifier}")
	public List<ShopDTO> getShops(@PathVariable String userIdentifier) {		
		List<ShopDTO> produtos = shopService.getByUser(userIdentifier);		
		return produtos;
	}
	
	@GetMapping("/shopping/shopByDate")
	public List<ShopDTO> getShops(@RequestBody ShopDTO shopDTO) {		
		List<ShopDTO> produtos = shopService.getByDate(shopDTO.getDate());		
		return produtos;
	}
		
	@GetMapping("/shopping/{id}")
	public ShopDTO findById(@PathVariable Long id) {
	    return shopService.findById(id);
	}
	
	@PostMapping("/shopping")
	public ShopDTO newShop(@Valid @RequestBody ShopDTO shopDTO, @RequestHeader(name = "key", required = true) String key) {
		return shopService.save(shopDTO, key);
	}
	
	
	@GetMapping("/shopping/search")
	public List<ShopDTO> getShopsByFilter(@RequestParam(name = "dataInicio", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
			@RequestParam(name = "dataFim", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim,
			@RequestParam(name = "valorMinimo", required = false) Float valorMinimo) {		
		List<ShopDTO> produtos = reportService.getShopsByFilter(LocalDateTime.ofInstant(dataInicio.toInstant(), ZoneId.systemDefault()), 
				LocalDateTime.ofInstant(dataFim.toInstant(), ZoneId.systemDefault()), valorMinimo);
		return produtos;
	}
	
	@GetMapping("/shopping/report")
	public ShopReportDTO getShopsReport(@RequestParam(name = "dataInicio", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
			@RequestParam(name = "dataFim", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim) {		
		return reportService.getReportByDate(LocalDateTime.ofInstant(dataInicio.toInstant(), ZoneId.systemDefault()), 
				LocalDateTime.ofInstant(dataFim.toInstant(), ZoneId.systemDefault()));
	}
}
