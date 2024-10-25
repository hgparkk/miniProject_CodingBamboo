package com.codingbamboo.miniproject.notice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codingbamboo.miniproject.common.SearchVO;
import com.codingbamboo.miniproject.notice.dto.NoticeDTO;

@Mapper
public interface INoticeDAO {
	// �������� �Խñ� ��� ��������
	List<NoticeDTO> getNoticeList(SearchVO search);
	
	// �������� ž �Խñ� ��� ��������
	List<NoticeDTO> getNoticeTopList(SearchVO search);
	
	// �������� �Խñ� ��ü ���� ��������
	int getNoticeCount(SearchVO search);
	
	// �������� �ۼ�
	int insertNotice(NoticeDTO notice);
	
	// �������� ����
	NoticeDTO getNotice(int no);
	
	// �������� ����
	int deleteNotice(int noNo);

	// �������� ����
	int updateNotice(NoticeDTO notice);
	
	// �������� top�� ���
	int registTopNotice(int noNo);
	
	// �������� top���� ����
	int deleteTopNotice(int noNo);
	
	// �������� top ���� Ȯ��
	int getNoticeTopCount();
	
	// ���������� noNo�� �̸� Ȯ��
	int getNoticeNo();
}