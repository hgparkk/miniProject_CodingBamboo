package com.codingbamboo.miniproject.part.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingbamboo.miniproject.part.dao.IPartDAO;
import com.codingbamboo.miniproject.part.dto.PartDTO;

@Service
public class PartService {

	@Autowired
	IPartDAO dao;
	
	public List<String> getMiddleField(PartDTO part){
		return dao.getMiddleField(part);
	}
	
	public List<String> getSmallField(PartDTO part){
		return dao.getSmallField(part);
	}
	
	public List<String> getPart(PartDTO part){
		return dao.getPart(part);
	}
	
	public List<PartDTO> getResult(PartDTO part){
		return dao.getResult(part);
	}
	
}
