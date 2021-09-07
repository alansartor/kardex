package com.alan.kardex.stock.support;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MovimientoSalida {
	@NotBlank(message = "El codigo del articulo es obligatorio")
	private String itemCodigo;
	@NotNull(message = "El valor unitario es obligatorio")
	private BigDecimal valorUnitario;
	@NotNull(message = "La cantidad es obligatorio")
	private BigDecimal cantidad;
	
	public String getItemCodigo() {
		return itemCodigo;
	}
	public void setItemCodigo(String itemCodigo) {
		this.itemCodigo = itemCodigo;
	}
	public BigDecimal getCantidad() {
		return cantidad;
	}
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
}
