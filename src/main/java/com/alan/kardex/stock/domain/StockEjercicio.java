package com.alan.kardex.stock.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"item_id", "year"}))
public class StockEjercicio {
	@Id
	@SequenceGenerator(name = "STOCK_EJERCICIO_SEQ", sequenceName = "STOCK_EJERCICIO_SEQ", allocationSize = 1, initialValue=1000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STOCK_EJERCICIO_SEQ")
	private Long id;
	@ManyToOne(optional=false)
	private Item item;
	@Column(length=4, nullable=false)
	private String year;
	/**
	 * Promedio Ponderado del Item en el Ejercicio
	 */
	@Column(precision = 16, scale = 4, nullable=false)
	private BigDecimal valorUnitario;
	/**
	 * Cantidad del Item en el Ejercicio
	 */
	@Column(precision = 10, scale = 0, nullable=false)
	private BigDecimal cantidad;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof StockEjercicio))
			return false;
		StockEjercicio other = (StockEjercicio) obj;
		if (id == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		return true;
	}
}
