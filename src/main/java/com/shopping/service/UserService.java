package com.shopping.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import dto.UserDTO;
import exceptions.UserNotFoundException;

@Service
public class UserService {

	public UserDTO getUserByCpf(String cpf) {
		try {
			RestTemplate rest = new RestTemplate();
			String url = "http://localhost:8080/users/" + cpf;
			ResponseEntity<UserDTO> response = rest.getForEntity(url, UserDTO.class);
			return response.getBody();
		} catch (HttpClientErrorException.NotFound e) {
			throw new UserNotFoundException();
		}
		
	}
	
	public UserDTO getUserByCpfAndKey(String cpf, String key) {
		try {
			RestTemplate rest = new RestTemplate();
			String url = "http://localhost:8080/users/" + cpf+ "?key=" + key;
			ResponseEntity<UserDTO> response = rest.getForEntity(url, UserDTO.class);
			return response.getBody();
		} catch (HttpClientErrorException.NotFound e) {
			throw new UserNotFoundException();
		}
		
	}
	
}
