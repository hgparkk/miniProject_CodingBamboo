package com.codingbamboo.miniproject.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingbamboo.miniproject.common.SearchVO;
import com.codingbamboo.miniproject.notice.dao.INoticeDAO;
import com.codingbamboo.miniproject.notice.dto.NoticeDTO;

@Service
public class NoticeService {
	@Autowired
	INoticeDAO dao;
	
	// 공지사항 목록 가져오기
	public List<NoticeDTO> getNoticeList(SearchVO search){
		List<NoticeDTO> result = dao.getNoticeList(search);
		return result;
	}
	
	// 공지사항 top 목록 가져오기
	public List<NoticeDTO> getNoticeTopList(SearchVO search){
		List<NoticeDTO> result = dao.getNoticeTopList(search);
		return result;
	} 
	
	// 공지사항 갯수 세기
	public int getNoticeCount(SearchVO search) {
		int result = dao.getNoticeCount(search);
		return result;
	}
	
	// 공지사항 등록
	public int insertNotice(NoticeDTO notice) {
		int result = dao.insertNotice(notice);
		return result;
	}
	
	// 공지사항 하나 가져오기
	public NoticeDTO getNotice(int no) {
		NoticeDTO result = dao.getNotice(no);
		return result;
	}
	
	// 공지사항 삭제
	public int deleteNotice(int noNo) {
		int result = dao.deleteNotice(noNo);
		return result;
	}
	
	// 공지사항 수정
	public int updateNotice(NoticeDTO notice) {
		int result = dao.updateNotice(notice);
		return result;
	}
	
	// 공지사항 top 등록
	public int registTopNotice(int noNo) {
		int result = dao.registTopNotice(noNo);
		return result;
	}
	
	// 공지사항 top 삭제
	public int deleteTopNotice(int noNo) {
		int result = dao.deleteTopNotice(noNo);
		return result;
	}
	
	// 공지사항 top 개수 가져오기
	public int getNoticeTopCount() {
		int result = dao.getNoticeTopCount();
		return result;
	}
	
	// 공지사항 번호 미리 알기
	public int getNoticeNo() {
		int result = dao.getNoticeNo();
		return result;
	}

}
