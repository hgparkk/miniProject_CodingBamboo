package com.codingbamboo.miniproject.board.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingbamboo.miniproject.board.dto.BoardDTO;
import com.codingbamboo.miniproject.board.service.BoardService;
import com.codingbamboo.miniproject.common.SearchVO;
import com.codingbamboo.miniproject.user.dto.UserDTO;

@Controller
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@RequestMapping("/boardView")
	public String boardView(Model model, SearchVO search) {
		int boardCount = boardService.getBoardCount(search);
		
		search.setBoardCount(boardCount);
		
		search.setting();
		
		List<BoardDTO> boardList = boardService.getBoardList(search);
		
		
		model.addAttribute("keyBoardList", boardList);
		
		model.addAttribute("keySearch", search);
		
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
	
	@RequestMapping("/boardDetailView")
	public String boardDetailView(int no, Model model) {
		BoardDTO board = boardService.getBoard(no);
		
		model.addAttribute("keyBoard", board);
		
		return "board/boardDetailView";
	}
	
	@PostMapping("/boardDelDo")
	public String boardDelDo(int no, HttpServletRequest request) {
		boardService.deleteBoard(no);
		
		request.setAttribute("msg", "질문이 삭제되었습니다.");
		request.setAttribute("url", "/boardView");
		return "alert";
	}
}
