package com.alan.kardex.stock.service;

import java.util.Date;

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
import com.alan.kardex.stock.domain.Movimiento;
import com.alan.kardex.stock.domain.StockEjercicio;
import com.alan.kardex.stock.domain.TipoMovimientoEnum;
import com.alan.kardex.stock.repository.MovimientoDao;
import com.alan.kardex.stock.support.MovimientoEntrada;
import com.alan.kardex.stock.support.MovimientoSalida;

@Service
@Transactional
public class MovimientoSrvImpl implements MovimientoSrv {
	@Autowired 
	private MovimientoDao dao;
	@Autowired 
	private ItemSrv itemSrv;
	@Autowired 
	private StockEjercicioSrv stockEjercicioSrv;
	
	@Override
	public Movimiento registrarEntrada(MovimientoEntrada movimientoEntrada) throws FieldErrorDetailExeption{
		Item item = null;
		try {
			item = itemSrv.findByCodigo(movimientoEntrada.getItemCodigo());
		} catch (EmptyResultDataAccessException e) {
			throw new FieldErrorDetailExeption(new FieldErrorDetail("itemCodigo", "El codigo de articulo no existe"));
		}
		Date fechaActual = new Date();
		String year = DateFormatUtils.format(fechaActual, "yyyy");
		StockEjercicio stockEjercicio = null;
		try {
			stockEjercicio = stockEjercicioSrv.findByItemYear(item, year);
		} catch (EmptyResultDataAccessException e) {
			throw new FieldErrorDetailExeption(new FieldErrorDetail("stockEjercicio", "No existe registro de existencia para el articulo seleccionado y el year " + year));
		}
		Movimiento movimiento = new Movimiento();
		movimiento.setStockEjercicio(stockEjercicio);
		movimiento.setTipo(TipoMovimientoEnum.ENTRADA);
		movimiento.setFecha(fechaActual);
		movimiento.setCantidad(movimientoEntrada.getCantidad());
		movimiento.setValorUnitario(movimientoEntrada.getValorUnitario());
		dao.create(movimiento);
		stockEjercicioSrv.updateByEntrada(stockEjercicio, movimiento.getCantidad(), movimiento.getValorUnitario());
		return movimiento;
	}

	@Override
	public Movimiento registrarSalida(MovimientoSalida movimientoSalida) throws FieldErrorDetailExeption, NotFoundExeption, BadRequestExeption{
		Item item = null;
		try {
			item = itemSrv.findByCodigo(movimientoSalida.getItemCodigo());
		} catch (EmptyResultDataAccessException e) {
			throw new FieldErrorDetailExeption(new FieldErrorDetail("itemCodigo", "El codigo de articulo no existe"));
		}
		Date fechaActual = new Date();
		String year = DateFormatUtils.format(fechaActual, "yyyy");
		StockEjercicio stockEjercicio = null;
		try {
			stockEjercicio = stockEjercicioSrv.findByItemYear(item, year);
		} catch (EmptyResultDataAccessException e) {
			throw new FieldErrorDetailExeption(new FieldErrorDetail("stockEjercicio", "No existe registro de existencia para el articulo seleccionado y el year " + year));
		}
		stockEjercicioSrv.verificarStock(stockEjercicio, movimientoSalida.getCantidad());
		stockEjercicioSrv.verificarValor(stockEjercicio, movimientoSalida.getValorUnitario());
		Movimiento movimiento = new Movimiento();
		movimiento.setStockEjercicio(stockEjercicio);
		movimiento.setTipo(TipoMovimientoEnum.SALIDA);
		movimiento.setFecha(fechaActual);
		movimiento.setCantidad(movimientoSalida.getCantidad());
		movimiento.setValorUnitario(stockEjercicio.getValorUnitario());
		dao.create(movimiento);
		stockEjercicioSrv.updateBySalida(stockEjercicio, movimiento.getCantidad());
		return movimiento;
	}

}