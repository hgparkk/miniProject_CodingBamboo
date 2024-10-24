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
	
	
	// q&a 게시판 가기
	@RequestMapping("/boardView")
	public String boardView(Model model, SearchVO search) {
		int boardCount = boardService.getBoardCount(search);
		
		search.setBoardCount(boardCount);
		
		search.boardSetting();
		
		model.addAttribute("keySearch", search);
		
		// q&a 게시글 목록 가져오기
		// 공지사항 top 목록 가져오기
		List<NoticeDTO> getNoticeTopList = noticeService.getNoticeTopList(search);
		
		model.addAttribute("keyGetNoticeTopList", getNoticeTopList);
		
		// q&a 게시글 가져오기
		List<BoardDTO> getBoardList = boardService.getBoardList(search);
		
		model.addAttribute("keyBoardList", getBoardList);
		
		return "board/boardView";
	}
	
	// 글 작성 페이지로 이동
	@RequestMapping("/boardWriteView")
	public String boardWriteView(HttpSession session) {
		UserDTO login = (UserDTO)session.getAttribute("login");
		
		// 로그인 정보 안담겨있으면 login = null
		if(login == null) {
			return "redirect:/loginView";
		}
		return "board/boardWriteView";
	}
	
	// 글 작성하기
	@PostMapping("/boardWriteDo")
	public String boardWriteDo(BoardDTO board, HttpServletRequest request) {
		boardService.insertBoard(board);
		
		request.setAttribute("msg", "질문이 등록되었습니다.");
		request.setAttribute("url", "/boardView");
		
		return "alert";
	}
	
	// 질문 자세히 보기
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
		
		// 답변 가져오기
		List<AnswerDTO> answerList = answerService.getAnswerList(no);
		
		model.addAttribute("keyAnswerList", answerList);
		
		return "board/boardDetailView";
	}
	
	// 질문 삭제하기
	@PostMapping("/boardDelDo")
	public String boardDelDo(int no, HttpServletRequest request) {
		boardService.deleteBoard(no);
		
		request.setAttribute("msg", "질문이 삭제되었습니다.");
		request.setAttribute("url", "/boardView");
		return "alert";
	}
	
	// 질문 수정 페이지
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
	
	// 질문 수정하기
	@PostMapping("/boardEditDo")
	public String boardEditDo(BoardDTO board, HttpServletRequest request) {
		boardService.updateBoard(board);
		
		request.setAttribute("msg", "질문이 수정되었습니다.");
		request.setAttribute("url", "/boardDetailView?no=" + board.getQuNo());
		return "alert";
	}
}
