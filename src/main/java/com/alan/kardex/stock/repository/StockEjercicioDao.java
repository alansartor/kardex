package com.alan.kardex.stock.repository;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.alan.kardex.stock.domain.Item;
import com.alan.kardex.stock.domain.StockEjercicio;
import com.alan.kardex.stock.dto.StockEjercicioDTO;

public interface StockEjercicioDao {
	/**
	 * Busca un StockEjercicio en el repositorio
	 * @param item del StockEjercicio que se esta buscando (nunca {@code null})
	 * @param year del StockEjercicio que se esta buscando (nunca {@code null})
	 * @return el StockEjercicio solicitado
	 * @throws EmptyResultDataAccessException si no hay StockEjercicio con el item y el year pasados por parametro
	 */
	StockEjercicio findByItemYear(Item item, String year) throws EmptyResultDataAccessException;

	/**
	 * Busca un StockEjercicio en el repositorio y lo retorna como un DTO
	 * @param item del StockEjercicio que se esta buscando (nunca {@code null})
	 * @param year del StockEjercicio que se esta buscando (nunca {@code null})
	 * @return el StockEjercicioDTO solicitado
	 * @throws EmptyResultDataAccessException si no hay StockEjercicio con el item y el year pasados por parametro
	 */
	StockEjercicioDTO findByItemYearDTO(Item item, String year) throws EmptyResultDataAccessException;

	/**
	 * Busca todos los StockEjercicio del repositorio y lo retorna como DTO
	 * @return una lista con todos los StockEjercicio (nunca {@code null})
	 */
	List<StockEjercicioDTO> findAllDTO();

}