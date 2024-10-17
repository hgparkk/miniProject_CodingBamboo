package com.codingbamboo.miniproject.material.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.codingbamboo.miniproject.material.dto.MaterialDTO;

@Mapper
public interface IMaterialDAO {
	// ���� ���
	// �˻�
	List<MaterialDTO> selectAllMaterial(String word);
	// ���� �ҷ�����
	MaterialDTO selectMaterial(int meNo);

	// ������ ���
	// ���� ��� ��������
	List<MaterialDTO> getMaterialList(String searchWord);
	
	// ���� �߰��ϱ�
	int insertMaterial(MaterialDTO material);

	// ���� ����
	int deleteMaterial(int meNo);
	
	// ���� ����
	int updateMaterial(MaterialDTO material);
}