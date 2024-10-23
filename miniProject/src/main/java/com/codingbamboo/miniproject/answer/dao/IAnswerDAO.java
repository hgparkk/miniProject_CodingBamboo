package com.codingbamboo.miniproject.answer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codingbamboo.miniproject.answer.dto.AnswerDTO;

@Mapper
public interface IAnswerDAO {
	// 답변 등록
	int insertAnswer(AnswerDTO answer);
	
	// 답변 조회
	AnswerDTO getAnswer(int awNo);
	
	// 가장 최근 답변 조회
	AnswerDTO getResentAnswer(int quNo);
	
	// 특정 게시글 답변 목록 조회
	List<AnswerDTO> getAnswerList(int quNo);
	
	// 답변 삭제
	int delAnswer(int awNo);
	
	//모든 답변삭제
	int delAllAnswer(int quNo);
}
