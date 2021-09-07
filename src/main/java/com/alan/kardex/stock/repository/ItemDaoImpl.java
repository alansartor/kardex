package com.alan.kardex.stock.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.alan.kardex.repository.AbstractJpaDAO;
import com.alan.kardex.stock.domain.Item;
import com.alan.kardex.stock.dto.ItemDTO;

@Repository
public class ItemDaoImpl extends AbstractJpaDAO<Item> implements ItemDao {
	
	public ItemDaoImpl(){
		setClazz(Item.class);
	}
	
	@Override
	public List<ItemDTO> findAllDTO() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ItemDTO> query = builder.createQuery(ItemDTO.class);
        Root<Item> root = query.from(Item.class);
        query.multiselect(
        	root.get("id").alias("id"),
        	root.get("codigo").alias("codigo"),
        	root.get("tipo").alias("tipo"),
        	root.get("descripcion").alias("descripcion"));
        query.orderBy(builder.asc(root.get("codigo")));
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public Item findByCodigo(String codigo) throws EmptyResultDataAccessException{
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> query = builder.createQuery(Item.class);
        Root<Item> root = query.from(Item.class);
        query.select(root);
        query.where(builder.equal(root.get("codigo"), codigo));
        return entityManager.createQuery(query).getSingleResult();
	}
}
