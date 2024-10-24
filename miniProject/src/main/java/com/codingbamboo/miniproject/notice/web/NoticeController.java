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
	
	// 공지사항 게시판 가기
	@RequestMapping("noticeView")
	public String getNoticeList(SearchVO search, Model model){
		// 공지사항 전체 가져오기
		int noticeCount = noticeService.getNoticeCount(search);
		
		search.setNoticeCount(noticeCount);
		
		search.noticeSetting();
		
		model.addAttribute("keySearch", search);
		
		// 공지사항 목록 가져오기
		List<NoticeDTO> getNoticeList = noticeService.getNoticeList(search);
		
		model.addAttribute("keyGetNoticeList", getNoticeList);
		
		// 공지사항 top 목록 가져오기
		List<NoticeDTO> noticeTopList = noticeService.getNoticeTopList(search);
		
		model.addAttribute("keyGetNoticeTopList", noticeTopList);
		
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
		
		request.setAttribute("msg", "공지글이 등록되었습니다.");
		request.setAttribute("url", "/noticeView");
		
		return "alert";
	}
	
	// 공지글 자세히 보기
	@RequestMapping("/noticeDetailView")
	public String noticeDetailView(int no, Model model) {
		NoticeDTO notice = new NoticeDTO();
		
		notice = noticeService.getNotice(no);
		
		model.addAttribute("keyNotice", notice);
		
		// 해당 게시글의 첨부파일 목록 가져오기
		List<AttachDTO> attachList = attachService.getAttachList(no);
		
		model.addAttribute("keyAttachList", attachList);
		
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
	
	// 공지사항 top에 등록하기
	@PostMapping("/registTopNoticeDo")
	public String registTopNoticeDo(int no, HttpServletRequest request) {
		int count = 0;
		
		count = noticeService.getNoticeTopCount();
		
		if(count >= 5) {
			request.setAttribute("msg", "공지사항 top에 더는 등록할 수 없습니다.");
			request.setAttribute("url", "/noticeDetailView?no="+no);
			return "alert";
		}
		
		noticeService.registTopNotice(no);
		
		request.setAttribute("msg", "공지사항이 등록되었습니다.");
		request.setAttribute("url", "/noticeView");
		return "alert";
	}
	
	// 공지사항 top에서 내리기
	@PostMapping("/deleteTopNoticeDo")
	public String deleteTopNoticeDo(int no, HttpServletRequest request) {
		noticeService.deleteTopNotice(no);
		
		request.setAttribute("msg", "공지사항 top에서 삭제되었습니다.");
		request.setAttribute("url", "/noticeView");
		return "alert";
	}
	
	
}
