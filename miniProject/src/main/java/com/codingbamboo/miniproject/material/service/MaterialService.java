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
	
	// 계산기
	// 검색
	public List<MaterialDTO> selectAllMaterial(String word){
		return dao.selectAllMaterial(word);
	}
	
	// 자재 불러오기
	public MaterialDTO selectMaterial(int meNo){
		return dao.selectMaterial(meNo);
	}
	
	// 관리자
	// 검색
	public List<MaterialDTO> getMaterialList(String searchWord){
		List<MaterialDTO> result = dao.getMaterialList(searchWord);
		return result;
	}
	
	// 자재 추가
	public int insertMaterial(MaterialDTO material) {
		int result = dao.insertMaterial(material);
		return result;
	}
	
	// 자재 삭제
	public int deleteMaterial(int meNo) {
		int result = dao.deleteMaterial(meNo);
		return result;
	}
	
	// 자재 수정
	public int updateMaterial(MaterialDTO material) {
		int result = dao.updateMaterial( material);
		return result;
	}
}