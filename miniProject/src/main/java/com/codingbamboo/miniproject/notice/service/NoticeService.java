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
	
	public List<NoticeDTO> getNoticeList(SearchVO search){
		List<NoticeDTO> result = dao.getNoticeList(search);
		return result;
	}
	
	public List<NoticeDTO> getNoticeTopList(SearchVO search){
		List<NoticeDTO> result = dao.getNoticeTopList(search);
		return result;
	} 
	
	public int getNoticeCount(SearchVO search) {
		int result = dao.getNoticeCount(search);
		return result;
	}
	
	public int insertNotice(NoticeDTO notice) {
		int result = dao.insertNotice(notice);
		return result;
	}
	
	public NoticeDTO getNotice(int no) {
		NoticeDTO result = dao.getNotice(no);
		return result;
	}
	
	public int deleteNotice(int noNo) {
		int result = dao.deleteNotice(noNo);
		return result;
	}
	
	public int updateNotice(NoticeDTO notice) {
		int result = dao.updateNotice(notice);
		return result;
	}
	
	public int registTopNotice(int noNo) {
		int result = dao.registTopNotice(noNo);
		return result;
	}
	
	public int deleteTopNotice(int noNo) {
		int result = dao.deleteTopNotice(noNo);
		return result;
	}
	
	public int getNoticeTopCount() {
		int result = dao.getNoticeTopCount();
		return result;
	}

}
