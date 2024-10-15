package com.codingbamboo.miniproject.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codingbamboo.miniproject.admin.dto.MaterialDTO;
import com.codingbamboo.miniproject.common.SearchVO;

@Mapper
public interface IMaterialDAO {
	List<MaterialDTO> getMaterialList();
	
	int insertMaterial(MaterialDTO material);
	
	MaterialDTO getMaterial();
	
	int getMaterialCount(SearchVO search);
}
