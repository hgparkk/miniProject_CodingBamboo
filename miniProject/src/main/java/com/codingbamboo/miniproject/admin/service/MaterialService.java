package com.codingbamboo.miniproject.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingbamboo.miniproject.admin.dao.IMaterialDAO;
import com.codingbamboo.miniproject.admin.dto.MaterialDTO;
import com.codingbamboo.miniproject.common.SearchVO;

@Service
public class MaterialService {
	
	@Autowired
	IMaterialDAO dao;
	
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
