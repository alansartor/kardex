package com.alan.kardex.stock.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alan.kardex.config.FinalUserException;
import com.alan.kardex.config.InternalServerErrorExeption;
import com.alan.kardex.stock.domain.Movimiento;
import com.alan.kardex.stock.service.MovimientoSrv;
import com.alan.kardex.stock.support.MovimientoEntrada;
import com.alan.kardex.stock.support.MovimientoSalida;
import com.alan.kardex.stock.support.OkResponse;

@RestController
public class MovimientoController {
	@Autowired 
	private MovimientoSrv movimientoSrv;
	
	Logger logger = LoggerFactory.getLogger(MovimientoController.class);
	
	@PostMapping("entrada")
	public OkResponse entrada(@Valid @RequestBody MovimientoEntrada newMovimiento) {
		try {
			Movimiento movimiento = movimientoSrv.registrarEntrada(newMovimiento);
			logger.info("Entrada de "+movimiento.getCantidad()+" Articulos con codigo " + movimiento.getStockEjercicio().getItem().getCodigo() + " registrada con exito");
			StringBuilder builder = new StringBuilder();
			builder.append("Entrada de ")
				.append(movimiento.getCantidad())
				.append(" ")
				.append(movimiento.getStockEjercicio().getItem().getDescripcion())
				.append(" con un valor unitario de ")
				.append(movimiento.getValorUnitario())
				.append(" registrada con exito.")
				.append(" Hay en existencia ")
				.append(movimiento.getStockEjercicio().getCantidad())
				.append(" unidades, con un valor promedio ponderado de ")
				.append(movimiento.getStockEjercicio().getValorUnitario());
			return new OkResponse(builder.toString());
		} catch (Exception e) {
			if(e instanceof FinalUserException) {
				throw e;
			}
			logger.error("Error inesperado al registrar entrada de articulos", e);
			throw new InternalServerErrorExeption("No se pudo registrar la entrada de articulos", "Para mas informacion comuniquese con el administrador del sistema");
		}
	}
	
	@PostMapping("salida")
	public OkResponse salida(@Valid @RequestBody MovimientoSalida newMovimiento) {
		try {
			Movimiento movimiento = movimientoSrv.registrarSalida(newMovimiento);
			logger.info("Salida de "+movimiento.getCantidad()+" Articulos con codigo " + movimiento.getStockEjercicio().getItem().getCodigo() + " registrada con exito");
			StringBuilder builder = new StringBuilder();
			builder.append("Salida de ")
				.append(movimiento.getCantidad())
				.append(" ")
				.append(movimiento.getStockEjercicio().getItem().getDescripcion())
				.append(" registrada con exito.")
				.append(" Quedan en existencia ")
				.append(movimiento.getStockEjercicio().getCantidad())
				.append(" unidades");
			return new OkResponse(builder.toString());
		} catch (Exception e) {
			if(e instanceof FinalUserException) {
				throw e;
			}
			logger.error("Error inesperado al registrar la salida de articulos", e);
			throw new InternalServerErrorExeption("No se pudo registrar la salida de articulos", "Para mas informacion comuniquese con el administrador del sistema");
		}
	}
}
