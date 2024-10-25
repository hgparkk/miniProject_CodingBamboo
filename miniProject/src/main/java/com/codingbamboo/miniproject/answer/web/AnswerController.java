package com.codingbamboo.miniproject.answer.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingbamboo.miniproject.answer.dto.AnswerDTO;
import com.codingbamboo.miniproject.answer.service.AnswerService;
import com.codingbamboo.miniproject.board.dto.BoardDTO;
import com.codingbamboo.miniproject.board.service.BoardService;
import com.codingbamboo.miniproject.user.dto.UserDTO;

@Controller
public class AnswerController {

	@Autowired
	BoardService boardService;

	@Autowired
	AnswerService answerService;
	
	@RequestMapping("/answerWriteView")
	public String answerWriteView(int quNo, Model model, HttpSession session) {
		UserDTO login = (UserDTO)session.getAttribute("login");
		if (login == null || !login.getUserId().equals("admin")) {
			return "redirect:/";
		}
		
		model.addAttribute("quNo",quNo);
		return "answer/answerWriteView";
	}

	@RequestMapping("/answerWriteDo")
	public String answerWriteDo(AnswerDTO answer, int quNo, HttpServletRequest request, BoardDTO board) {
	    // 답변 DB에 등록
	    answerService.insertAnswer(answer);

	    // 답변 등록 완료
	    boardService.updateBoardAnswer(quNo);

	    // 가장 최근에 달린 답변 불러오기
	    answerService.getResentAnswer(quNo);
	    
	    request.setAttribute("msg", "답변이 등록되었습니다.");
		request.setAttribute("url", "/boardDetailView?no=" + board.getQuNo());
		
		return "alert";
	}
	
	@PostMapping("/answerEditView")
	public String answerEditView(int quNo, Model model) {
		List<AnswerDTO> answer = new ArrayList<>();
		
		answer = answerService.getAnswerList(quNo);
		model.addAttribute("keyAnswer", answer);
		
		return "answer/answerEditView";
	}
	
	@PostMapping("/answerEditDo")
	public String answerEdidDo(AnswerDTO answer, HttpServletRequest request, BoardDTO board) {
		answerService.updateAnswer(answer);
		
		request.setAttribute("msg", "답변이 수정되었습니다.");
		request.setAttribute("url", "/boardDetailView?no=" + board.getQuNo());
		return "alert";
	}

	@PostMapping("/delAnswerDo")
	public String delAnswer(int awNo, int quNo, HttpServletRequest request, BoardDTO board) {
		String result = "fail";

		int cnt = answerService.delAnswer(awNo);

		if (cnt != 0) {
			result = "success";
			List<AnswerDTO> answerList = answerService.getAnswerList(quNo);
			if (answerList.size() == 0) {
				boardService.deleteBoardAnswer(quNo);
				
				request.setAttribute("msg", "답변이 삭제되었습니다.");
				request.setAttribute("url", "/boardDetailView?no=" + board.getQuNo());
				return "alert";
			}
		}

		return result;
	}
}
