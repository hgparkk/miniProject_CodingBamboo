package com.codingbamboo.miniproject.notice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codingbamboo.miniproject.common.SearchVO;
import com.codingbamboo.miniproject.notice.dto.NoticeDTO;

@Mapper
public interface INoticeDAO {
	// 공지사항 게시글 목록 가져오기
	List<NoticeDTO> getNoticeList(SearchVO search);
	
	// 공지사항 탑 게시글 목록 가져오기
	List<NoticeDTO> getNoticeTopList(SearchVO search);
	
	// 공지사항 게시글 전체 갯수 가져오기
	int getNoticeCount(SearchVO search);
	
	// 공지사항 작성
	int insertNotice(NoticeDTO notice);
	
	// 공지사항 보기
	NoticeDTO getNotice(int no);
	
	// 공지사항 삭제
	int deleteNotice(int noNo);

	// 공지사항 수정
	int updateNotice(NoticeDTO notice);
	
	// 공지사항 top에 등록
	int registTopNotice(int noNo);
	
	// 공지사항 top에서 삭제
	int deleteTopNotice(int noNo);
	
	// 공지사항 top 갯수 확인
	int getNoticeTopCount();
	
	// 공지사항의 noNo를 미리 확인
	int getNoticeNo();
}