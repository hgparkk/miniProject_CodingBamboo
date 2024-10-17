package com.codingbamboo.miniproject.material.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.codingbamboo.miniproject.material.dto.MaterialDTO;

@Mapper
public interface IMaterialDAO {
	// 계산기 기능
	// 검색
	List<MaterialDTO> selectAllMaterial(String word);
	// 자재 불러오기
	MaterialDTO selectMaterial(int meNo);

	// 관리자 기능
	// 자재 목록 가져오기
	List<MaterialDTO> getMaterialList(String searchWord);
	
	// 자재 추가하기
	int insertMaterial(MaterialDTO material);

	// 자재 삭제
	int deleteMaterial(int meNo);
	
	// 자재 수정
	int updateMaterial(MaterialDTO material);
}