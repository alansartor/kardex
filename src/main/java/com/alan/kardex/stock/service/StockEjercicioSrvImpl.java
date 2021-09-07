package com.alan.kardex.stock.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alan.kardex.config.BadRequestExeption;
import com.alan.kardex.config.FieldErrorDetail;
import com.alan.kardex.config.FieldErrorDetailExeption;
import com.alan.kardex.config.NotFoundExeption;
import com.alan.kardex.stock.domain.Item;
import com.alan.kardex.stock.domain.StockEjercicio;
import com.alan.kardex.stock.dto.StockEjercicioDTO;
import com.alan.kardex.stock.repository.StockEjercicioDao;

@Service
@Transactional
public class StockEjercicioSrvImpl implements StockEjercicioSrv {
	@Autowired 
	private StockEjercicioDao dao;
	@Autowired 
	private ItemSrv itemSrv;
	
	@Override
	public StockEjercicio findByItemYear(Item item, String year) throws EmptyResultDataAccessException{
		return dao.findByItemYear(item, year);
	}
	
	@Override
	public StockEjercicioDTO findByItemDTO(String itemCodigo) throws EmptyResultDataAccessException{
		Item item = null;
		try {
			item = itemSrv.findByCodigo(itemCodigo);
		} catch (EmptyResultDataAccessException e) {
			throw new FieldErrorDetailExeption(new FieldErrorDetail("itemCodigo", "El codigo de articulo no existe"));
		}
		Date fechaActual = new Date();
		String year = DateFormatUtils.format(fechaActual, "yyyy");
		try {
			return dao.findByItemYearDTO(item, year);
		} catch (EmptyResultDataAccessException e) {
			throw new FieldErrorDetailExeption(new FieldErrorDetail("stockEjercicio", "No existe registro de existencia para el articulo seleccionado y el year " + year));
		}		 
	}

	@Override
	public List<StockEjercicioDTO> findAllDTO() {
		return dao.findAllDTO();
	}

	@Override
	public void updateByEntrada(StockEjercicio stockEjercicio, BigDecimal cantidad, BigDecimal valorUnitario) {
		BigDecimal valorTotal = stockEjercicio.getCantidad().multiply(stockEjercicio.getValorUnitario());
		stockEjercicio.setCantidad(stockEjercicio.getCantidad().add(cantidad));
		valorTotal = valorTotal.add(cantidad.multiply(valorUnitario));
		stockEjercicio.setValorUnitario(valorTotal.divide(stockEjercicio.getCantidad(), 4, RoundingMode.HALF_DOWN));
	}

	@Override
	public void verificarStock(StockEjercicio stockEjercicio, BigDecimal cantidad) throws NotFoundExeption{
		if(stockEjercicio.getCantidad().compareTo(cantidad) < 0) {
			throw new NotFoundExeption("Stock insuficiente", "No puede realizar una compra por " + cantidad + " unidades debido a que solo quedan en stock " + stockEjercicio.getCantidad() + " unidades");
		}
	}
	
	@Override
	public void verificarValor(StockEjercicio stockEjercicio, BigDecimal valorUnitario) throws BadRequestExeption{
		if(stockEjercicio.getValorUnitario().compareTo(valorUnitario) != 0) {
			throw new BadRequestExeption("Valor unitario invalido", "El valor unitario informado esta desactualizado. El valor unitario del articulo es " + stockEjercicio.getValorUnitario());
		}
	}
	
	@Override
	public void updateBySalida(StockEjercicio stockEjercicio, BigDecimal cantidad) {
		stockEjercicio.setCantidad(stockEjercicio.getCantidad().subtract(cantidad));
	}
}