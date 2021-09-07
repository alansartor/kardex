package com.alan.kardex.stock.dto;

import java.math.BigDecimal;

public class StockEjercicioDTO {
	private String codigo;
	private String tipo;
	private String descripcion;
	private BigDecimal valorUnitario;
	private BigDecimal cantidad;
	
	public StockEjercicioDTO(String codigo, String tipo, String descripcion, BigDecimal valorUnitario, BigDecimal cantidad) {
		super();
		this.codigo = codigo;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.valorUnitario = valorUnitario;
		this.cantidad = cantidad;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public BigDecimal getCantidad() {
		return cantidad;
	}
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}
}
