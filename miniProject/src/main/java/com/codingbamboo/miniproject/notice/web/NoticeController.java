package com.codingbamboo.miniproject.notice.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.codingbamboo.miniproject.attach.dto.AttachDTO;
import com.codingbamboo.miniproject.attach.service.AttachService;
import com.codingbamboo.miniproject.common.FileUploadUtils;
import com.codingbamboo.miniproject.common.SearchVO;
import com.codingbamboo.miniproject.notice.dto.NoticeDTO;
import com.codingbamboo.miniproject.notice.service.NoticeService;
import com.codingbamboo.miniproject.user.dto.UserDTO;

@Controller
public class NoticeController {
	@Autowired
	NoticeService noticeService;
	
	@Autowired
	AttachService attachService;
	
	@Autowired
	FileUploadUtils fileUploadUtils;
	
	// �������� �Խ��� ����
	@RequestMapping("noticeView")
	public String getNoticeList(SearchVO search, Model model){
		// �������� ��ü ��������
		int noticeCount = noticeService.getNoticeCount(search);
		
		search.setNoticeCount(noticeCount);
		
		search.noticeSetting();
		
		model.addAttribute("keySearch", search);
		
		// �������� ��� ��������
		List<NoticeDTO> getNoticeList = noticeService.getNoticeList(search);
		
		model.addAttribute("keyGetNoticeList", getNoticeList);
		
		// �������� top ��� ��������
		List<NoticeDTO> noticeTopList = noticeService.getNoticeTopList(search);
		
		model.addAttribute("keyGetNoticeTopList", noticeTopList);
		
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
	public String noticeWriteDo(NoticeDTO notice, HttpServletRequest request, MultipartFile[] boFile) {
		noticeService.insertNotice(notice);
		int atchNoticeNo = noticeService.getNoticeNo();
		
		if(boFile != null && boFile.length > 0 && !boFile[0].isEmpty()) {
			try {
				List<AttachDTO> attachList = fileUploadUtils.getAttachListByMultiparts(boFile);
				if(!attachList.isEmpty()) {
					for(AttachDTO attach : attachList) {
						attach.setAtchNoticeNo(atchNoticeNo);
						attachService.insertAttach(attach);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "error/errorPath500";
			}
			
		}
		
		request.setAttribute("msg", "�������� ��ϵǾ����ϴ�.");
		request.setAttribute("url", "/noticeView");
		
		return "alert";
	}
	
	// ������ �ڼ��� ����
	@RequestMapping("/noticeDetailView")
	public String noticeDetailView(int no, Model model) {
		NoticeDTO notice = new NoticeDTO();
		
		notice = noticeService.getNotice(no);
		
		model.addAttribute("keyNotice", notice);
		
		// �ش� �Խñ��� ÷������ ��� ��������
		List<AttachDTO> attachList = attachService.getAttachList(no);
		
		model.addAttribute("keyAttachList", attachList);
		
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
	
	// �������� top�� ����ϱ�
	@PostMapping("/registTopNoticeDo")
	public String registTopNoticeDo(int no, HttpServletRequest request) {
		int count = 0;
		
		count = noticeService.getNoticeTopCount();
		
		if(count >= 5) {
			request.setAttribute("msg", "�������� top�� ���� ����� �� �����ϴ�.");
			request.setAttribute("url", "/noticeDetailView?no="+no);
			return "alert";
		}
		
		noticeService.registTopNotice(no);
		
		request.setAttribute("msg", "���������� ��ϵǾ����ϴ�.");
		request.setAttribute("url", "/noticeView");
		return "alert";
	}
	
	// �������� top���� ������
	@PostMapping("/deleteTopNoticeDo")
	public String deleteTopNoticeDo(int no, HttpServletRequest request) {
		noticeService.deleteTopNotice(no);
		
		request.setAttribute("msg", "�������� top���� �����Ǿ����ϴ�.");
		request.setAttribute("url", "/noticeView");
		return "alert";
	}
	
	
}
