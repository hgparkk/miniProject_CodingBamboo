package com.codingbamboo.miniproject.notice.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String getNoticeList(SearchVO search, Model model){
		int noticeCount = noticeService.getNoticeCount(search);
		
		search.setNoticeCount(noticeCount);
		
		search.setting();
		
		model.addAttribute("keySearch", search);
		
		List<NoticeDTO> noticeList = noticeService.getNoticeList(search);
		
		model.addAttribute("keyNoticeList", noticeList);
		
		List<NoticeDTO> noticeTopList = noticeService.getNoticeTopList(search);
		
		model.addAttribute("keyNoticeTopList", noticeTopList);
		
		List<NoticeDTO> getNoticeList = noticeService.getNoticeList(search);
		
		model.addAttribute("keyGetNoticeList", getNoticeList);
		
		return "notice/noticeView";
	}
	
	// 공지사항 작성 페이지로 이동
	@RequestMapping("/noticeWriteView")
	public String noticeWriteView(HttpSession session) {
		UserDTO login = (UserDTO)session.getAttribute("login");
		
		// 로그인 정보 안담겨있으면 login = null
		if(login == null) {
			return "redirect:/loginView";
		}
		return "notice/noticeWriteView";
	}
	
	// 공지글 작성하기
	@PostMapping("/noticeWriteDo")
	public String noticeWriteDo(NoticeDTO notice, HttpServletRequest request) {
		noticeService.insertNotice(notice);
		
		request.setAttribute("msg", "공지글이 등록되었습니다.");
		request.setAttribute("url", "/noticeView");
		
		return "alert";
	}
	
	// 공지글 자세히 보기
	@RequestMapping("/noticeDetailView")
	public String noticeDetailView(int no, Model model) {
		NoticeDTO notice = null;
		notice = noticeService.getNotice(no);
		
		model.addAttribute("keyNotice", notice);
		
		return "notice/noticeDetailView";
	}
	
	// 공지글 삭제하기
	@PostMapping("/noticeDelDo")
	public String noticeDelDo(int no, HttpServletRequest request) {
		noticeService.deleteNotice(no);
		
		request.setAttribute("msg", "공지사항이 삭제되었습니다.");
		request.setAttribute("url", "/noticeView");
		return "alert";
	}
	
	// 공지글 수정 페이지
	@PostMapping("/noticeEditView")
	public String noticeEditView(int no, Model model) {
		NoticeDTO notice;
		notice = noticeService.getNotice(no);
		model.addAttribute("keyNotice", notice);
		
		return "notice/noticeEditView";
	}
	
	// 질문 수정하기
	@PostMapping("/noticeEditDo")
	public String noticeEditDo(NoticeDTO notice, HttpServletRequest request) {
		noticeService.updateNotice(notice);
		
		request.setAttribute("msg", "공지사항이 수정되었습니다.");
		request.setAttribute("url", "/noticeDetailView?no=" + notice.getNoNo());
		return "alert";
	}
	
	
}
