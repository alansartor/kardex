package com.alan.kardex.stock.service;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.alan.kardex.config.BadRequestExeption;
import com.alan.kardex.config.NotFoundExeption;
import com.alan.kardex.stock.domain.StockEjercicio;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class StockEjercicioSrvImplTest {

	@InjectMocks
	private StockEjercicioSrvImpl stockEjercicioSrv;
	
	@Test
	public void testStockWhenUpdateByEntrada() {
		StockEjercicio stockEjercicio = new StockEjercicio();
		stockEjercicio.setCantidad(new BigDecimal(2));
		stockEjercicio.setValorUnitario(new BigDecimal(4));
		
		BigDecimal cantidad = new BigDecimal(8);
		BigDecimal valorUnitario = new BigDecimal(5);
		
		stockEjercicioSrv.updateByEntrada(stockEjercicio, cantidad, valorUnitario);
		assertAll("update by entrada",
		        () -> assertThat(stockEjercicio.getCantidad(),  comparesEqualTo(new BigDecimal(10))),
		        () -> assertThat(stockEjercicio.getValorUnitario(),  
		        		Matchers.comparesEqualTo(new BigDecimal(48).divide(new BigDecimal(10), 4, RoundingMode.HALF_DOWN)))
		);
	}
	
	@Test
	public void testExeptionNotThrowOnVerificarStock() {
		StockEjercicio stockEjercicio = new StockEjercicio();
		stockEjercicio.setCantidad(new BigDecimal(2));
		
		BigDecimal cantidad = new BigDecimal(2);
		
		assertDoesNotThrow(() -> stockEjercicioSrv.verificarStock(stockEjercicio, cantidad));
	}
	
	@Test
	public void testExeptionThrowOnVerificarStock() {
		StockEjercicio stockEjercicio = new StockEjercicio();
		stockEjercicio.setCantidad(new BigDecimal(2));
		
		BigDecimal cantidad = new BigDecimal(8);
		
		Throwable exception = assertThrows(NotFoundExeption.class, () -> stockEjercicioSrv.verificarStock(stockEjercicio, cantidad));
	    assertEquals("Stock insuficiente", exception.getMessage());
	}
	
	@Test
	public void testExeptionNotThrowOnVerificarValor() {
		StockEjercicio stockEjercicio = new StockEjercicio();
		stockEjercicio.setValorUnitario(new BigDecimal(4));
		
		BigDecimal valorUnitario = new BigDecimal(4);
		
		assertDoesNotThrow(() -> stockEjercicioSrv.verificarValor(stockEjercicio, valorUnitario));
	}
	
	@Test
	public void testExeptionThrowOnVerificarValor() {
		StockEjercicio stockEjercicio = new StockEjercicio();
		stockEjercicio.setValorUnitario(new BigDecimal(4));
		
		BigDecimal valorUnitario = new BigDecimal(5);
		
		Throwable exception = assertThrows(BadRequestExeption.class, () -> stockEjercicioSrv.verificarValor(stockEjercicio, valorUnitario));
	    assertEquals("Valor unitario invalido", exception.getMessage());
	}
	
	@Test
	public void testStockWhenUpdateBySalida() {
		StockEjercicio stockEjercicio = new StockEjercicio();
		stockEjercicio.setCantidad(new BigDecimal(7));
		
		BigDecimal cantidad = new BigDecimal(4);
		
		stockEjercicioSrv.updateBySalida(stockEjercicio, cantidad);
		assertThat(stockEjercicio.getCantidad(),  Matchers.comparesEqualTo(new BigDecimal(3)));
	}
}
