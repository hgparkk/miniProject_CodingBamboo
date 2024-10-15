package com.codingbamboo.miniproject.material.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingbamboo.miniproject.material.dao.IMaterialDAO;
import com.codingbamboo.miniproject.material.dto.MaterialDTO;

@Service
public class MaterialService {
	
	@Autowired
	IMaterialDAO dao;
	
	public List<MaterialDTO> selectAllMaterial(String word){
		return dao.selectAllMaterial(word);
	}
	
	public MaterialDTO selectMaterial(int meNo){
		return dao.selectMaterial(meNo);
	}
}