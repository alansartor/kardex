package com.alan.kardex.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AbstractJpaDAO <T> {

	private Class<T> clazz;

	@PersistenceContext
	protected EntityManager entityManager;

	public final void setClazz(Class<T> clazzToSet){
		this.clazz = clazzToSet;
	}

	public T findOne(Object id){
		return entityManager.find(clazz, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(){
		return entityManager.createQuery("from " + clazz.getName()).getResultList();
   }

	public void create(T entity){
		entityManager.persist(entity);
	}

	public T update( T entity ){
		return entityManager.merge(entity);
	}

	public void delete(T entity){
		entityManager.remove(entity);
	}
	
	public void deleteById(long entityId){
		T entity = findOne(entityId);
		delete(entity);
	}
}