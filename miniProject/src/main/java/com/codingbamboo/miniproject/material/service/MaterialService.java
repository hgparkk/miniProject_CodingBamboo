package com.codingbamboo.miniproject.material.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingbamboo.miniproject.common.SearchVO;
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
	
	public List<MaterialDTO> getMaterialList(){
		List<MaterialDTO> result = dao.getMaterialList();
		return result;
	}
	
	public int insertMaterial(MaterialDTO material) {
		int result = dao.insertMaterial(material);
		return result;
	}
	
	public int getMaterialCount(SearchVO search) {
		int result = dao.getMaterialCount(search);
		return result;
	}
}