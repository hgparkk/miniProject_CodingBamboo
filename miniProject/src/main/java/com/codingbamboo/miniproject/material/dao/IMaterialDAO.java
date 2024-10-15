package com.codingbamboo.miniproject.material.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codingbamboo.miniproject.common.SearchVO;
import com.codingbamboo.miniproject.material.dto.MaterialDTO;

@Mapper
public interface IMaterialDAO {
	// 계산기 기능
	List<MaterialDTO> selectAllMaterial(String word); 
	MaterialDTO selectMaterial(int meNo);

	// 관리자 기능
	// 자재 목록 가져오기
	List<MaterialDTO> getMaterialList(String searchWord);
	
	// 자재 추가하기
	int insertMaterial(MaterialDTO material);
	
	// 자재 하나 가져오기
	MaterialDTO getMaterial();

	// 자재 삭제
	int deleteMaterial(int no);
	
	// 자재 수정
	int updateMaterial(int no);
}