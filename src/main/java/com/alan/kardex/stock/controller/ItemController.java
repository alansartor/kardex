package com.alan.kardex.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alan.kardex.stock.dto.ItemDTO;
import com.alan.kardex.stock.service.ItemSrv;

@RestController
public class ItemController {
	@Autowired 
	private ItemSrv itemSrv; 
	
	@GetMapping("items")
	public List<ItemDTO> items() {	
		return itemSrv.findAllDTO();
	}
}
