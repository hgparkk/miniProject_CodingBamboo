package com.codingbamboo.miniproject.answer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codingbamboo.miniproject.answer.dto.AnswerDTO;

@Mapper
public interface IAnswerDAO {
	// �亯 ���
	int insertAnswer(AnswerDTO answer);
	
	// �亯 ��ȸ
	AnswerDTO getAnswer(int awNo);
	
	// ���� �ֱ� �亯 ��ȸ
	AnswerDTO getResentAnswer(int quNo);
	
	// Ư�� �Խñ� �亯 ��� ��ȸ
	List<AnswerDTO> getAnswerList(int quNo);
	
	// �亯 ����
	int delAnswer(int awNo);
	
	//��� �亯����
	int delAllAnswer(int quNo);
}
