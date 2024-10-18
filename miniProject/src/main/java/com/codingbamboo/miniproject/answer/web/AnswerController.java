package com.codingbamboo.miniproject.answer.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codingbamboo.miniproject.answer.dto.AnswerDTO;
import com.codingbamboo.miniproject.answer.service.AnswerService;
import com.codingbamboo.miniproject.board.dto.BoardDTO;
import com.codingbamboo.miniproject.board.service.BoardService;

@Controller
public class AnswerController {
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	AnswerService answerService;

	@ResponseBody
	@RequestMapping("/answerWriteDo")
	public AnswerDTO answerWriteDo(AnswerDTO answer, int quNo) {
		System.out.println(answer);
		
		// 답변 DB에 등록
		answerService.insertAnswer(answer);
		
		// 가장 최근에 달립 답변 불러오기
		AnswerDTO resultAnswer = answerService.getResentAnswer(quNo);
		
		return resultAnswer;
	}
}
