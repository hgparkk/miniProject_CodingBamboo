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
	
	// ����
	// �˻�
	public List<MaterialDTO> selectAllMaterial(String word){
		return dao.selectAllMaterial(word);
	}
	
	// ���� �ҷ�����
	public MaterialDTO selectMaterial(int meNo){
		return dao.selectMaterial(meNo);
	}
	
	// ������
	// �˻�
	public List<MaterialDTO> getMaterialList(String searchWord){
		List<MaterialDTO> result = dao.getMaterialList(searchWord);
		return result;
	}
	
	// ���� �߰�
	public int insertMaterial(MaterialDTO material) {
		int result = dao.insertMaterial(material);
		return result;
	}
	
	// ���� ����
	public int deleteMaterial(int meNo) {
		int result = dao.deleteMaterial(meNo);
		return result;
	}
	
	// ���� ����
	public int updateMaterial(MaterialDTO material) {
		int result = dao.updateMaterial( material);
		return result;
	}
}