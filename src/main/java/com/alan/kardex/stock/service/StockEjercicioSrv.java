package com.alan.kardex.stock.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.alan.kardex.config.BadRequestExeption;
import com.alan.kardex.config.NotFoundExeption;
import com.alan.kardex.stock.domain.Item;
import com.alan.kardex.stock.domain.StockEjercicio;
import com.alan.kardex.stock.dto.StockEjercicioDTO;

public interface StockEjercicioSrv {
	
	/**
	 * Busca un StockEjercicio en el repositorio
	 * @param item del StockEjercicio que se esta buscando (nunca {@code null})
	 * @param year del StockEjercicio que se esta buscando (nunca {@code null})
	 * @return el StockEjercicio solicitado nunca {@code null})
	 * @throws EmptyResultDataAccessException si no hay StockEjercicio con el item y el year pasados por parametro
	 */
	StockEjercicio findByItemYear(Item item, String year) throws EmptyResultDataAccessException;
	
	/**
	 * Busca un StockEjercicio en el repositorio y lo retorna como un DTO
	 * @param item del StockEjercicio que se esta buscando (nunca {@code null})
	 * @param year del StockEjercicio que se esta buscando (nunca {@code null})
	 * @return el StockEjercicioDTO solicitado nunca {@code null})
	 * @throws EmptyResultDataAccessException si no hay StockEjercicio con el item y el year pasados por parametro
	 */
	StockEjercicioDTO findByItemDTO(String itemCodigo) throws EmptyResultDataAccessException;
	
	/**
	 * Busca todos los StockEjercicio del repositorio y lo retorna como DTO
	 * @return una lista con todos los StockEjercicio (nunca {@code null})
	 */
	List<StockEjercicioDTO> findAllDTO();
	
	/**
	 * Actualiza la cantidad y el nuevo precio promedio ponderado en el stockEjercicio
	 * @param stockEjercicio a actualizar (nunca {@code null})
	 * @param cantidad que se debe sumar al stock (nunca {@code null})
	 * @param valorUnitario de la cantidad que entro, se usa para calcular el nuevo precio promedio ponderado (nunca {@code null})
	 */
	void updateByEntrada(StockEjercicio stockEjercicio, BigDecimal cantidad, BigDecimal valorUnitario);

	/**
	 * Verifica que stockEjercicio.cantidad sea superior a cantidad 
	 * @param stockEjercicio (nunca {@code null})
	 * @param cantidad (nunca {@code null})
	 * @throws NotFoundExeption cuando stockEjercicio.cantidad sea inferior a cantidad 
	 */
	void verificarStock(StockEjercicio stockEjercicio, BigDecimal cantidad) throws NotFoundExeption;

	/**
	 * Verifica que stockEjercicio.valorUnitario sea igual a valorUnitario
	 * @param stockEjercicio (nunca {@code null})
	 * @param valorUnitario (nunca {@code null})
	 * @throws BadRequestExeption cuando stockEjercicio.valorUnitario es distinto que valorUnitario
	 */
	void verificarValor(StockEjercicio stockEjercicio, BigDecimal valorUnitario) throws BadRequestExeption;

	/**
	 * Actualiza la cantidad en stockEjercicio
	 * Este metodo no verifica si hay stock suficiente, antes de llamar a este metodo hay que llamar a {@link #verificarStock(StockEjercicio, BigDecimal)}
	 * @param stockEjercicio a actualizar (nunca {@code null})
	 * @param cantidad que se debe restar al stock (nunca {@code null})
	 */
	void updateBySalida(StockEjercicio stockEjercicio, BigDecimal cantidad);

}
