package com.codingbamboo.miniproject.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingbamboo.miniproject.board.dao.IBoardDAO;
import com.codingbamboo.miniproject.board.dto.BoardDTO;
import com.codingbamboo.miniproject.common.SearchVO;
import com.codingbamboo.miniproject.common.exception.BizNotFoundException;

@Service
public class BoardService {
	
	@Autowired
	IBoardDAO dao;

	// 질문 목록 가져오기
	public List<BoardDTO> getBoardList(SearchVO search){
		List<BoardDTO> result = dao.getBoardList(search);
		return result;
	}
	
	// 공지사항 목록 가져오기
	public List<BoardDTO> getNoticeList(SearchVO saerch){
		List<BoardDTO> result = dao.getNoticeList(saerch);
		return result;
	}
	
	// 질문 전체 갯수 가져오기
	public int getBoardCount(SearchVO search) {
		int result = dao.getBoardCount(search);
		
		return result;
	}
	
	// 질문 불러오기
	public BoardDTO getBoard(int no) throws BizNotFoundException{
		BoardDTO result = dao.getBoard(no);
		
		if(result == null) {
			throw new BizNotFoundException("API_001", "해당 글 번호가 존재하지 않음");
		}
		
		return result;
	}
	
	
	
	// 질문 등록
	public int insertBoard(BoardDTO board) {
		int result = dao.insertBoard(board);
		
		return result;
	}
	
	// 질문 삭제
	public int deleteBoard(int quNo) {
		int result = dao.deleteBoard(quNo);
		
		return result;
	}
	
	public List<Integer> getUserBoardList(String userId){
		return dao.getUserBoardList(userId);	
	}
	
	// 질문 수정
	public int updateBoard(BoardDTO board) {
		int result = dao.updateBoard(board);
		
		return result;
	}
	
	// 질문 답변 등록 메시지 삭제
	public int updateBoardAnswer(int quNo) {
		int result = dao.updateBoardAnswer(quNo);
		return result;
	}
	
	// 질문 답변 완료 메시지 출력
	public int deleteBoardAnswer(int quNo) {
		int result = dao.deleteBoardAnswer(quNo);
		return result;
	}
}
