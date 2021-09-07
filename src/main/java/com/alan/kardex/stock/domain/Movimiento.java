package com.alan.kardex.stock.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Movimiento {
	@Id
	@SequenceGenerator(name = "MOVIMIENTO_SEQ", sequenceName = "MOVIMIENTO_SEQ", allocationSize = 1, initialValue=1000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOVIMIENTO_SEQ")
	private Long id;
	@ManyToOne(optional=false)
	private StockEjercicio stockEjercicio;
	@Enumerated(EnumType.STRING)
	@Column(length=20, nullable=false)
	private TipoMovimientoEnum tipo;
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;
	@Column(precision = 16, scale = 4, nullable=false)
	private BigDecimal valorUnitario;
	@Column(precision = 10, scale = 0, nullable=false)
	private BigDecimal cantidad;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public StockEjercicio getStockEjercicio() {
		return stockEjercicio;
	}
	public void setStockEjercicio(StockEjercicio stockEjercicio) {
		this.stockEjercicio = stockEjercicio;
	}
	public TipoMovimientoEnum getTipo() {
		return tipo;
	}
	public void setTipo(TipoMovimientoEnum tipo) {
		this.tipo = tipo;
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
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
		if (!(obj instanceof Movimiento))
			return false;
		Movimiento other = (Movimiento) obj;
		if (id == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		return true;
	}
}
