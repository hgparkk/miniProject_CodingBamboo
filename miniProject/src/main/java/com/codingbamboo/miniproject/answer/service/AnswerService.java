package com.codingbamboo.miniproject.answer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingbamboo.miniproject.answer.dao.IAnswerDAO;
import com.codingbamboo.miniproject.answer.dto.AnswerDTO;

@Service
public class AnswerService {

	@Autowired
	IAnswerDAO dao;

	// 답변 등록
	public int insertAnswer(AnswerDTO answer) {
		int result = dao.insertAnswer(answer);

		return result;
	}

	// 답변 조회
	public AnswerDTO getAnswer(int awNo) {
		AnswerDTO result = dao.getAnswer(awNo);

		return result;
	}

	// 가장 최근 답변 조회
	public AnswerDTO getResentAnswer(int quNo) {
		AnswerDTO result = dao.getResentAnswer(quNo);
		return result;
	}

	// 특정 게시글의 답변목록 가져오는 메소드
	public List<AnswerDTO> getAnswerList(int quNo) {
		List<AnswerDTO> result = dao.getAnswerList(quNo);
		return result;
	}
	
	// 답변 삭제
	public int delAnswer(int awNo) {
		int result = dao.delAnswer(awNo);
		return result;
	}
	
	public int delAllAnswer(int quNo) {
		int result = dao.delAllAnswer(quNo);
		return result;
	}
	
}
