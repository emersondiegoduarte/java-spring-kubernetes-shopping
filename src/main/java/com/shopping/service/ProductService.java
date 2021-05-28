package com.shopping.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import dto.ProductDTO;
import exceptions.ProductNotFoundException;

@Service
public class ProductService {

	public	ProductDTO	findByProductIdentifier(String	productIdentifier) {
		try {
			RestTemplate rest = new RestTemplate();
			String url = "http://localhost:8081/product/" + productIdentifier;
			ResponseEntity<ProductDTO> response = rest.getForEntity(url, ProductDTO.class);
			return response.getBody();
		} catch (HttpClientErrorException.NotFound e) {
			throw new ProductNotFoundException();
		}
		
		
	}
}
