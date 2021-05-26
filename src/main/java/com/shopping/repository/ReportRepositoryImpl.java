package com.shopping.repository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import com.shopping.dto.ShopReportDTO;
import com.shopping.entity.Shop;

public class ReportRepositoryImpl implements ReportRepository{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Shop> getShopByFilters(LocalDateTime dataInicio, LocalDateTime dataFim, Float valorMinimo) {
		StringBuilder sql = new StringBuilder();
		sql.append("select s from shop s where s.date >= :dataInicio ");
		
		if(dataFim != null) {
			sql.append("and s.date <= :dataFim ");
		}
		
		if(valorMinimo != null) {
			sql.append("and s.total <= :valorMinimo");
		}
		
		Query query = em.createQuery(sql.toString());
		query.setParameter("dataInicio", dataInicio);
		
		if(dataFim != null) {
			query.setParameter("dataFim", dataFim);
		}
		
		if(valorMinimo != null) {
			query.setParameter("valorMinimo", valorMinimo);
		}
		return query.getResultList();
	}

	@Override
	public ShopReportDTO getReportByDate(LocalDateTime dataInicio, LocalDateTime dataFim) {
		// TODO Auto-generated method stub
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(id), sum(total), avg(total) from shopping.shop where date >= :dataInicio");
		sql.append(" and date <= :dataFim");
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("dataInicio", dataInicio);
		query.setParameter("dataFim", dataFim);
		Object[] result = (Object[]) query.getSingleResult();
		ShopReportDTO shop = new ShopReportDTO();
		shop.setCount(((BigInteger) result[0]).intValue());
		shop.setTotal((Double) result[1]);
		shop.setMean((Double) result[2]);
		return shop;
	}

}
