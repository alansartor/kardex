package com.alan.kardex.stock.service;

import com.alan.kardex.config.BadRequestExeption;
import com.alan.kardex.config.FieldErrorDetailExeption;
import com.alan.kardex.config.NotFoundExeption;
import com.alan.kardex.stock.domain.Movimiento;
import com.alan.kardex.stock.support.MovimientoEntrada;
import com.alan.kardex.stock.support.MovimientoSalida;

public interface MovimientoSrv {
	
	/**
	 * Persiste un movimiento de entrada en el repositorio
	 * El movimiento se crea con los atributos de movimientoEntrada
	 * @param movimientoEntrada tiene toda la informacion para crear el movimiento (nunca {@code null})
	 * @return el movimiento persistido nunca {@code null})
	 * @throws FieldErrorDetailExeption si movimientoEntrada.itemCodigo no corresponde a un Item en el repositorio o si no hay un StockEjercicio para el Item y el year actual
	 */
	Movimiento registrarEntrada(MovimientoEntrada movimientoEntrada) throws FieldErrorDetailExeption;

	/**
	 * Persiste un movimiento de salida en el repositorio
	 * El movimiento se crea con los atributos de movimientoSalida
	 * @param movimientoSalida tiene toda la informacion para crear el movimiento (nunca {@code null})
	 * @return el movimiento persistido nunca {@code null})
	 * @throws FieldErrorDetailExeption si movimientoSalida.itemCodigo no corresponde a un Item en el repositorio o si no hay un StockEjercicio para el Item y el year actual
	 * @throws NotFoundExeption si no hay stock suficiente
	 * @throws BadRequestExeption si movimientoSalida.valorUnitario no coincide con el valor unitario actual del item
	 */
	Movimiento registrarSalida(MovimientoSalida movimientoSalida) throws FieldErrorDetailExeption, NotFoundExeption, BadRequestExeption;
}
