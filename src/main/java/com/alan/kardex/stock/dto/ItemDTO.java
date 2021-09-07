package com.alan.kardex.stock.dto;

public class ItemDTO {
	private Long id;
	private String codigo;
	private String tipo;
	private String descripcion;
	
	public ItemDTO(Long id, String codigo, String tipo, String descripcion) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.tipo = tipo;
		this.descripcion = descripcion;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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

}
