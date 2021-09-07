package com.alan.kardex.stock.repository;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.alan.kardex.stock.domain.Item;
import com.alan.kardex.stock.dto.ItemDTO;

public interface ItemDao {

	/**
	 * Busca todos los Items del repositorio
	 * @return una lista con todos los Items (nunca {@code null})
	 */
	List<ItemDTO> findAllDTO();

	/**
	 * Busca un Item por codigo
	 * @param codigo del Item que se esta buscando (nunca {@code null})
	 * @return el Item solicitado
	 * @throws EmptyResultDataAccessException si no hay Item con el codigo pasado por parametro
	 */
	Item findByCodigo(String codigo) throws EmptyResultDataAccessException;

}