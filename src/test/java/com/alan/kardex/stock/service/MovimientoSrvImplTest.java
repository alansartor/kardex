package com.alan.kardex.stock.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.alan.kardex.config.FieldErrorDetail;
import com.alan.kardex.config.FieldErrorDetailExeption;
import com.alan.kardex.stock.domain.Item;
import com.alan.kardex.stock.domain.Movimiento;
import com.alan.kardex.stock.domain.StockEjercicio;
import com.alan.kardex.stock.repository.MovimientoDaoImpl;
import com.alan.kardex.stock.support.MovimientoEntrada;
import com.alan.kardex.stock.support.MovimientoSalida;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class MovimientoSrvImplTest {

	@MockBean
	private ItemSrvImpl itemSrv;
	@MockBean
	private StockEjercicioSrvImpl stockEjercicioSrv;
	@MockBean
	private MovimientoDaoImpl dao;
	
	@InjectMocks
	private MovimientoSrvImpl movimientoSrv;
	
	@Test
	public void testExeptionThrowWhenItemNotFounOnRegistrarEntrada() {
		String itemCodigo = "CAM_0001";
		
		MovimientoEntrada newMovimiento = new MovimientoEntrada();
		newMovimiento.setItemCodigo(itemCodigo);
		
		Mockito.when(itemSrv.findByCodigo(itemCodigo)).thenThrow(
				new FieldErrorDetailExeption(new FieldErrorDetail("itemCodigo", "El codigo de articulo no existe")));

		Throwable exception = assertThrows(FieldErrorDetailExeption.class, () -> movimientoSrv.registrarEntrada(newMovimiento));
	    assertEquals(new FieldErrorDetail("itemCodigo", "El codigo de articulo no existe").toString(), exception.getMessage());
	}
	
	@Test 
	public void testExeptionThrowWhenStockEjercicioNotFounOnRegistrarEntrada() { 
		String itemCodigo = "CAM_0001";
		String year = DateFormatUtils.format(new Date(), "yyyy");
		Item item = new Item();
		MovimientoEntrada newMovimiento = new MovimientoEntrada();
		newMovimiento.setItemCodigo(itemCodigo);
	  
		Mockito.when(itemSrv.findByCodigo(itemCodigo)).thenReturn(item);
		Mockito.when(stockEjercicioSrv.findByItemYear(item, year)).thenThrow(
				new FieldErrorDetailExeption(new FieldErrorDetail("stockEjercicio", "No existe registro de existencia para el articulo seleccionado y el year " + year)));
	  
		Throwable exception = assertThrows(FieldErrorDetailExeption.class, () -> movimientoSrv.registrarEntrada(newMovimiento)); 
		assertEquals(new FieldErrorDetail("stockEjercicio", "No existe registro de existencia para el articulo seleccionado y el year " + year).toString(), exception.getMessage());
	}
	  
	@Test 
	public void testExeptionNotThrowOnRegistrarEntrada() {
		String itemCodigo = "CAM_0001";
		BigDecimal cantidad = new BigDecimal(4);
		BigDecimal valorUnitario = new BigDecimal(5);
		String year = DateFormatUtils.format(new Date(), "yyyy");
		Item item = new Item();
		StockEjercicio stockEjercicio = new StockEjercicio();
		MovimientoEntrada newMovimiento = new MovimientoEntrada();
		newMovimiento.setItemCodigo(itemCodigo);
		newMovimiento.setCantidad(cantidad);
		newMovimiento.setValorUnitario(valorUnitario);
		
		Mockito.when(itemSrv.findByCodigo(itemCodigo)).thenReturn(item);
		Mockito.when(stockEjercicioSrv.findByItemYear(item, year)).thenReturn(stockEjercicio);
		Mockito.doNothing().when(dao).create(new Movimiento());
		Mockito.doNothing().when(stockEjercicioSrv).updateByEntrada(stockEjercicio, cantidad, valorUnitario);
	
		assertDoesNotThrow(() -> movimientoSrv.registrarEntrada(newMovimiento));
	}
	
	@Test
	public void testExeptionThrowWhenItemNotFounOnRegistrarSalida() {
		String itemCodigo = "CAM_0001";
		
		MovimientoSalida newMovimiento = new MovimientoSalida();
		newMovimiento.setItemCodigo(itemCodigo);
		
		Mockito.when(itemSrv.findByCodigo(itemCodigo)).thenThrow(
				new FieldErrorDetailExeption(new FieldErrorDetail("itemCodigo", "El codigo de articulo no existe")));

		Throwable exception = assertThrows(FieldErrorDetailExeption.class, () -> movimientoSrv.registrarSalida(newMovimiento));
	    assertEquals(new FieldErrorDetail("itemCodigo", "El codigo de articulo no existe").toString(), exception.getMessage());
	}
	
	@Test 
	public void testExeptionThrowWhenStockEjercicioNotFounOnRegistrarSalida() { 
		String itemCodigo = "CAM_0001";
		String year = DateFormatUtils.format(new Date(), "yyyy");
		Item item = new Item();
		MovimientoSalida newMovimiento = new MovimientoSalida();
		newMovimiento.setItemCodigo(itemCodigo);
	  
		Mockito.when(itemSrv.findByCodigo(itemCodigo)).thenReturn(item);
		Mockito.when(stockEjercicioSrv.findByItemYear(item, year)).thenThrow(
				new FieldErrorDetailExeption(new FieldErrorDetail("stockEjercicio", "No existe registro de existencia para el articulo seleccionado y el year " + year)));
	  
		Throwable exception = assertThrows(FieldErrorDetailExeption.class, () -> movimientoSrv.registrarSalida(newMovimiento)); 
		assertEquals(new FieldErrorDetail("stockEjercicio", "No existe registro de existencia para el articulo seleccionado y el year " + year).toString(), exception.getMessage());
	}
	  
	@Test 
	public void testExeptionNotThrowOnRegistrarSalida() {
		String itemCodigo = "CAM_0001";
		BigDecimal cantidad = new BigDecimal(4);
		BigDecimal valorUnitario = new BigDecimal(5);
		String year = DateFormatUtils.format(new Date(), "yyyy");
		Item item = new Item();
		StockEjercicio stockEjercicio = new StockEjercicio();
		MovimientoSalida newMovimiento = new MovimientoSalida();
		newMovimiento.setItemCodigo(itemCodigo);
		newMovimiento.setCantidad(cantidad);
		newMovimiento.setValorUnitario(valorUnitario);
		
		Mockito.when(itemSrv.findByCodigo(itemCodigo)).thenReturn(item);
		Mockito.when(stockEjercicioSrv.findByItemYear(item, year)).thenReturn(stockEjercicio);
		Mockito.doNothing().when(stockEjercicioSrv).verificarStock(stockEjercicio, newMovimiento.getCantidad());
		Mockito.doNothing().when(stockEjercicioSrv).verificarValor(stockEjercicio, newMovimiento.getValorUnitario());
		Mockito.doNothing().when(dao).create(new Movimiento());
		Mockito.doNothing().when(stockEjercicioSrv).updateBySalida(stockEjercicio, cantidad);
	
		assertDoesNotThrow(() -> movimientoSrv.registrarSalida(newMovimiento));
	}
}
