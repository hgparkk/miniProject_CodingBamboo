package com.codingbamboo.miniproject.answer.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
		// �亯 DB�� ���
		answerService.insertAnswer(answer);

		// �亯 ��� �Ϸ�
		boardService.updateBoardAnswer(quNo);

		// ���� �ֱٿ� �޸� �亯 �ҷ�����
		AnswerDTO resultAnswer = answerService.getResentAnswer(quNo);

		return resultAnswer;
	}

	@ResponseBody
	@PostMapping("/delAnswerDo")
	public String delAnswer(int awNo, int quNo) {
		String result = "fail";

		int cnt = answerService.delAnswer(awNo);

		if (cnt != 0) {
			result = "success";
			List<AnswerDTO> answerList = answerService.getAnswerList(quNo);
			if (answerList.size() == 0) {
				boardService.deleteBoardAnswer(quNo);
			}
		}

		return result;
	}
}
