package com.codingbamboo.miniproject.material.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codingbamboo.miniproject.material.dto.MaterialDTO;

@Mapper
public interface IMaterialDAO {
	List<MaterialDTO> selectAllMaterial(String word);
	MaterialDTO selectMaterial(int meNo);
}