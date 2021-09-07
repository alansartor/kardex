package com.alan.kardex.stock.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.alan.kardex.repository.AbstractJpaDAO;
import com.alan.kardex.stock.domain.Item;
import com.alan.kardex.stock.domain.StockEjercicio;
import com.alan.kardex.stock.dto.StockEjercicioDTO;

@Repository
public class StockEjercicioDaoImpl extends AbstractJpaDAO<StockEjercicio> implements StockEjercicioDao {
	
	public StockEjercicioDaoImpl(){
		setClazz(StockEjercicio.class);
	}

	@Override
	public StockEjercicio findByItemYear(Item item, String year) throws EmptyResultDataAccessException{
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StockEjercicio> query = builder.createQuery(StockEjercicio.class);
        Root<StockEjercicio> root = query.from(StockEjercicio.class);
        query.select(root);
        query.where(
        	builder.equal(root.get("item"), item),
        	builder.equal(root.get("year"), year));
        return entityManager.createQuery(query).getSingleResult();
	}

	@Override
	public StockEjercicioDTO findByItemYearDTO(Item item, String year) throws EmptyResultDataAccessException{
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StockEjercicioDTO> query = builder.createQuery(StockEjercicioDTO.class);
        Root<StockEjercicio> root = query.from(StockEjercicio.class);
        Path<Item> itemPath = root.get("item");
        query.multiselect(
        	itemPath.get("codigo").alias("codigo"),
        	itemPath.get("tipo").alias("tipo"),
        	itemPath.get("descripcion").alias("descripcion"),
        	root.get("valorUnitario").alias("valorUnitario"),
        	root.get("cantidad").alias("cantidad"));
        query.where(
        	builder.equal(root.get("item"), item),
        	builder.equal(root.get("year"), year));
        return entityManager.createQuery(query).getSingleResult();
	}

	@Override
	public List<StockEjercicioDTO> findAllDTO() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StockEjercicioDTO> query = builder.createQuery(StockEjercicioDTO.class);
        Root<StockEjercicio> root = query.from(StockEjercicio.class);
        Path<Item> itemPath = root.get("item");
        query.multiselect(
        	itemPath.get("codigo").alias("codigo"),
        	itemPath.get("tipo").alias("tipo"),
        	itemPath.get("descripcion").alias("descripcion"),
        	root.get("valorUnitario").alias("valorUnitario"),
        	root.get("cantidad").alias("cantidad"));
        query.orderBy(builder.asc(itemPath.get("codigo")));
		return entityManager.createQuery(query).getResultList();
	}
}
