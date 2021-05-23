package com.shopping.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.entity.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long>{

	
	public List<Shop> findAllByUserIdentifier(String userIdentifier);
	
	public List<Shop> findAllByTotalGreaterThan(float total);
	
	public List<Shop> findAllByDateGreaterThan(LocalDateTime data);
	
}
