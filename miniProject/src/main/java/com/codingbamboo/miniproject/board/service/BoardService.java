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

	// �Խñ� ��� ��������
	public List<BoardDTO> getBoardList(SearchVO search){
		List<BoardDTO> result = dao.getBoardList(search);
		return result;
	}
	
	// �Խñ� ��ü ���� ��������
	public int getBoardCount(SearchVO search) {
		int result = dao.getBoardCount(search);
		
		return result;
	}
	
	// �Խñ� �ҷ�����
	public BoardDTO getBoard(int no) throws BizNotFoundException{
		BoardDTO result = dao.getBoard(no);
		
		if(result == null) {
			throw new BizNotFoundException("API_001", "�ش� �� ��ȣ�� �������� ����");
		}
		
		return result;
	}
	
	
	
	// �Խñ� ���
	public int insertBoard(BoardDTO board) {
		int result = dao.insertBoard(board);
		
		return result;
	}
	
	// �Խñ� ����
	public int deleteBoard(int quNo) {
		int result = dao.deleteBoard(quNo);
		
		return result;
	}
	
	public int updateBoard(BoardDTO board) {
		int result = dao.updateBoard(board);
		
		return result;
	}
}
