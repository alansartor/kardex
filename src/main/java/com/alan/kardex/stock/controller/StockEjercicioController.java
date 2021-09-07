package com.alan.kardex.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.alan.kardex.stock.dto.StockEjercicioDTO;
import com.alan.kardex.stock.service.StockEjercicioSrv;

@RestController
public class StockEjercicioController {
	@Autowired 
	private StockEjercicioSrv stockEjercicioSrv; 
	
	@GetMapping("stocks/{itemCodigo}")
	public StockEjercicioDTO stocks(@PathVariable String itemCodigo) {
		return stockEjercicioSrv.findByItemDTO(itemCodigo);
	}
	
	@GetMapping("stocks")
	public List<StockEjercicioDTO> stocks() {
		return stockEjercicioSrv.findAllDTO();
	}
}
