package com.codingbamboo.miniproject.board.web;

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
import com.codingbamboo.miniproject.common.SearchVO;
import com.codingbamboo.miniproject.common.exception.BizNotFoundException;
import com.codingbamboo.miniproject.user.dto.UserDTO;

@Controller
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@Autowired
	AnswerService answerService;
	
	// q&a �Խ��� ����
	@RequestMapping("/boardView")
	public String boardView(Model model, SearchVO search) {
		int boardCount = boardService.getBoardCount(search);
		
		search.setBoardCount(boardCount);
		
		search.setting();
		
		model.addAttribute("keySearch", search);
		
		List<BoardDTO> boardList = boardService.getBoardList(search);
		
		model.addAttribute("keyBoardList", boardList);
		
		List<BoardDTO> getNoticeList = boardService.getNoticeList(search);
		
		model.addAttribute("keyGetNoticeList", getNoticeList);
		
		System.out.println(search.getStart() + "," + search.getEnd());
		
		return "board/boardView";
	}
	
	// �� �ۼ� �������� �̵�
	@RequestMapping("/boardWriteView")
	public String boardWriteView(HttpSession session) {
		UserDTO login = (UserDTO)session.getAttribute("login");
		
		// �α��� ���� �ȴ�������� login = null
		if(login == null) {
			return "redirect:/loginView";
		}
		return "board/boardWriteView";
	}
	
	// �� �ۼ��ϱ�
	@PostMapping("/boardWriteDo")
	public String boardWriteDo(BoardDTO board, HttpServletRequest request) {
		boardService.insertBoard(board);
		System.out.println(board);
		
		request.setAttribute("msg", "������ ��ϵǾ����ϴ�.");
		request.setAttribute("url", "/boardView");
		
		return "alert";
	}
	
	// ���� �ڼ��� ����
	@RequestMapping("/boardDetailView")
	public String boardDetailView(int no, Model model) {
		BoardDTO board = null;
		try {
			board = boardService.getBoard(no);
		} catch (BizNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			// ���� �߻� �� ���� �����ڵ�� �����޽��� Ȯ��
			String errCode = e.getErrCode();
			String errMsg = e.getMessage();
			
			// ������������ �����޽����� �����ְ��� �Ѵٸ� �𵨿� �߰� 
			model.addAttribute("errMsg", errMsg);
			
			// ������������ ������
			return "errPage";
		}
		
		model.addAttribute("keyBoard", board);
		
		List<AnswerDTO> answerList = answerService.getAnswerList(no);
		
		model.addAttribute("keyAnswerList", answerList);
		
		return "board/boardDetailView";
	}
	
	// ���� �����ϱ�
	@PostMapping("/boardDelDo")
	public String boardDelDo(int no, HttpServletRequest request) {
		boardService.deleteBoard(no);
		
		request.setAttribute("msg", "������ �����Ǿ����ϴ�.");
		request.setAttribute("url", "/boardView");
		return "alert";
	}
	
	// ���� ���� ������
	@PostMapping("/boardEditView")
	public String boardEditView(int no, Model model) {
		BoardDTO board;
		try {
			board = boardService.getBoard(no);
			model.addAttribute("keyBoard", board);
		} catch (BizNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "board/boardEditView";
	}
	
	// ���� �����ϱ�
	@PostMapping("/boardEditDo")
	public String boardEditDo(BoardDTO board, HttpServletRequest request) {
		boardService.updateBoard(board);
		
		request.setAttribute("msg", "������ �����Ǿ����ϴ�.");
		request.setAttribute("url", "/boardDetailView?no=" + board.getQuNo());
		return "alert";
	}
}
