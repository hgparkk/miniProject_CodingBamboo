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
import com.codingbamboo.miniproject.notice.dto.NoticeDTO;
import com.codingbamboo.miniproject.notice.service.NoticeService;
import com.codingbamboo.miniproject.user.dto.UserDTO;

@Controller
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@Autowired
	NoticeService noticeService;
	
	@Autowired
	AnswerService answerService;
	
	
	// q&a �Խ��� ����
	@RequestMapping("/boardView")
	public String boardView(Model model, SearchVO search) {
		int boardCount = boardService.getBoardCount(search);
		
		search.setBoardCount(boardCount);
		
		search.boardSetting();
		
		model.addAttribute("keySearch", search);
		
		// q&a �Խñ� ��� ��������
		// �������� top ��� ��������
		List<NoticeDTO> getNoticeTopList = noticeService.getNoticeTopList(search);
		
		model.addAttribute("keyGetNoticeTopList", getNoticeTopList);
		
		// q&a �Խñ� ��������
		List<BoardDTO> getBoardList = boardService.getBoardList(search);
		
		model.addAttribute("keyBoardList", getBoardList);
		
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
			e.printStackTrace();
			String errCode = e.getErrCode();
			String errMsg = e.getMessage();
			
			model.addAttribute("errMsg", errMsg);
			
			return "errPage";
		}
		
		model.addAttribute("keyBoard", board);
		
		// �亯 ��������
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
			e.printStackTrace();
		}
		
		return "redirect:/boardEditView";
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
