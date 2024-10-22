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
	
	// �������� �Խ��� ����
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
	
	// �������� �ۼ� �������� �̵�
	@RequestMapping("/noticeWriteView")
	public String noticeWriteView(HttpSession session) {
		UserDTO login = (UserDTO)session.getAttribute("login");
		
		// �α��� ���� �ȴ�������� login = null
		if(login == null) {
			return "redirect:/loginView";
		}
		return "notice/noticeWriteView";
	}
	
	// ������ �ۼ��ϱ�
	@PostMapping("/noticeWriteDo")
	public String noticeWriteDo(NoticeDTO notice, HttpServletRequest request) {
		noticeService.insertNotice(notice);
		
		request.setAttribute("msg", "�������� ��ϵǾ����ϴ�.");
		request.setAttribute("url", "/noticeView");
		
		return "alert";
	}
	
	// ������ �ڼ��� ����
	@RequestMapping("/noticeDetailView")
	public String noticeDetailView(int no, Model model) {
		NoticeDTO notice = null;
		notice = noticeService.getNotice(no);
		
		model.addAttribute("keyNotice", notice);
		
		return "notice/noticeDetailView";
	}
	
	// ������ �����ϱ�
	@PostMapping("/noticeDelDo")
	public String noticeDelDo(int no, HttpServletRequest request) {
		noticeService.deleteNotice(no);
		
		request.setAttribute("msg", "���������� �����Ǿ����ϴ�.");
		request.setAttribute("url", "/noticeView");
		return "alert";
	}
	
	// ������ ���� ������
	@PostMapping("/noticeEditView")
	public String noticeEditView(int no, Model model) {
		NoticeDTO notice;
		notice = noticeService.getNotice(no);
		model.addAttribute("keyNotice", notice);
		
		return "notice/noticeEditView";
	}
	
	// ���� �����ϱ�
	@PostMapping("/noticeEditDo")
	public String noticeEditDo(NoticeDTO notice, HttpServletRequest request) {
		noticeService.updateNotice(notice);
		
		request.setAttribute("msg", "���������� �����Ǿ����ϴ�.");
		request.setAttribute("url", "/noticeDetailView?no=" + notice.getNoNo());
		return "alert";
	}
	
	
}
