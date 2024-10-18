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
	
	// Ư�� �Խñ� ��� ��� ��ȸ
	List<AnswerDTO> getAnswerList(int qwNo);
}
