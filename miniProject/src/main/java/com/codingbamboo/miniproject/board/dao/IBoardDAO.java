package com.codingbamboo.miniproject.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codingbamboo.miniproject.board.dto.BoardDTO;
import com.codingbamboo.miniproject.common.SearchVO;

@Mapper
public interface IBoardDAO {
	// �Խñ� ��� ��������
	List<BoardDTO> getBoardList(SearchVO search);
	
	// �Խñ� ��ü ���� ��������
	int getBoardCount(SearchVO search);
	
	// �������� ��ü ���� ��������
	List<BoardDTO> getNoticeList(SearchVO saerch);
	
	// �۾���
	int insertBoard(BoardDTO board);
	
	// �ۺ���
	BoardDTO getBoard(int no);
	
	// �ۻ���
	int deleteBoard(int quNo);
	
	//ȸ�� Ż�� ���� �Խ��Ǹ���Ʈ ��������
	List<Integer> getUserBoardList(String userId);
	
	// �ۼ���
	int updateBoard(BoardDTO board);

	// �亯�Ϸ� �޽��� ���
	int updateBoardAnswer(int quNo);
	
	// �亯�Ϸ� �޽��� ����
	int deleteBoardAnswer(int quNo);
}
