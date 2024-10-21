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
	
	public List<String> PE_middleField(PartDTO part){
		return dao.PE_middleField(part);
	}
	
	public List<String> PE_smallField(PartDTO part){
		return dao.PE_smallField(part);
	}
	
	public List<String> PE_PART(PartDTO part){
		return dao.PE_PART(part);
	}
	
	public List<PartDTO> result(PartDTO part){
		return dao.result(part);
	}
	
}
