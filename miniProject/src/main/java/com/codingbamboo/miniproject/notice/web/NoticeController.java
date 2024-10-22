package com.codingbamboo.miniproject.notice.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingbamboo.miniproject.answer.dto.AnswerDTO;
import com.codingbamboo.miniproject.board.dto.BoardDTO;
import com.codingbamboo.miniproject.common.SearchVO;
import com.codingbamboo.miniproject.common.exception.BizNotFoundException;
import com.codingbamboo.miniproject.notice.dto.NoticeDTO;
import com.codingbamboo.miniproject.notice.service.NoticeService;
import com.codingbamboo.miniproject.user.dto.UserDTO;

@Controller
public class NoticeController {
	@Autowired
	NoticeService noticeService;
	
	// 공지사항 게시판 가기
	@RequestMapping("noticeView")
	public List<NoticeDTO> getNoticeList(SearchVO search, Model model){
		int boardCount = noticeService.getNoticeCount(search);
		
		search.setBoardCount(boardCount);
		
		search.setting();
		
		model.addAttribute("keySearch", search);
		
		List<NoticeDTO> noticeList = noticeService.getNoticeList(search);
		
		model.addAttribute("keyNoticeList", noticeList);
		
		List<BoardDTO> getNoticeList = noticeService.getNoticeList(search);
		
		model.addAttribute("keyGetNoticeList", getNoticeList);
		
		System.out.println(search.getStart() + "," + search.getEnd());
		
		return "notice/noticeView";
	}
	
	
}
