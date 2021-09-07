package com.alan.kardex.stock.repository;

import org.springframework.stereotype.Repository;

import com.alan.kardex.repository.AbstractJpaDAO;
import com.alan.kardex.stock.domain.Movimiento;

@Repository
public class MovimientoDaoImpl extends AbstractJpaDAO<Movimiento> implements MovimientoDao {
	
	public MovimientoDaoImpl(){
		setClazz(Movimiento.class);
	}
}
