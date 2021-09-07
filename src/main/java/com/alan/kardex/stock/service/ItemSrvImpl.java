package com.alan.kardex.stock.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alan.kardex.stock.domain.Item;
import com.alan.kardex.stock.dto.ItemDTO;
import com.alan.kardex.stock.repository.ItemDao;

@Service
@Transactional
public class ItemSrvImpl implements ItemSrv {
	@Autowired 
	private ItemDao dao;

	@Override
	public List<ItemDTO> findAllDTO() {
		return dao.findAllDTO();
	}

	@Override
	public Item findByCodigo(String codigo) throws EmptyResultDataAccessException{
		return dao.findByCodigo(codigo);
	}
}