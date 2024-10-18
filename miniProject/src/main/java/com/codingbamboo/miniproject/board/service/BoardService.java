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

	// 게시글 목록 가져오기
	public List<BoardDTO> getBoardList(SearchVO search){
		List<BoardDTO> result = dao.getBoardList(search);
		return result;
	}
	
	// 게시글 전체 갯수 가져오기
	public int getBoardCount(SearchVO search) {
		int result = dao.getBoardCount(search);
		
		return result;
	}
	
	// 게시글 불러오기
	public BoardDTO getBoard(int no) throws BizNotFoundException{
		BoardDTO result = dao.getBoard(no);
		
		if(result == null) {
			throw new BizNotFoundException("API_001", "해당 글 번호가 존재하지 않음");
		}
		
		return result;
	}
	
	
	
	// 게시글 등록
	public int insertBoard(BoardDTO board) {
		int result = dao.insertBoard(board);
		
		return result;
	}
	
	// 게시글 삭제
	public int deleteBoard(int quNo) {
		int result = dao.deleteBoard(quNo);
		
		return result;
	}
	
	public int updateBoard(BoardDTO board) {
		int result = dao.updateBoard(board);
		
		return result;
	}
}
