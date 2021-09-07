package com.alan.kardex.stock.repository;

import com.alan.kardex.stock.domain.Movimiento;

public interface MovimientoDao {

	/**
	 * Persiste un movimiento en el repositorio
	 * @param movimiento que se desea persistir
	 */
	void create(Movimiento movimiento);

}