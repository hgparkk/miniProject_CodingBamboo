package com.codingbamboo.miniproject.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codingbamboo.miniproject.answer.dto.AnswerDTO;
import com.codingbamboo.miniproject.board.dto.BoardDTO;
import com.codingbamboo.miniproject.common.SearchVO;

@Mapper
public interface IBoardDAO {
	// 게시글 목록 가져오기
	List<BoardDTO> getBoardList(SearchVO search);
	
	// 게시글 전체 갯수 가져오기
	int getBoardCount(SearchVO search);
	
	// 글쓰기
	int insertBoard(BoardDTO board);
	
	// 글보기
	BoardDTO getBoard(int no);
	
	// 글삭제
	int deleteBoard(int quNo);
	
	// 글수정
	int updateBoard(BoardDTO board);

	// 답변완료 메시지 등록
	int updateBoardAnswer(int quNo);
	
	// 답변완료 메시지 삭제
	int deleteBoardAnswer(int quNo);
}
