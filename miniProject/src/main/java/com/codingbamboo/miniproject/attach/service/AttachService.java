package com.codingbamboo.miniproject.attach.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingbamboo.miniproject.attach.dao.IAttachDAO;
import com.codingbamboo.miniproject.attach.dto.AttachDTO;

@Service
public class AttachService {
	
	@Autowired
	IAttachDAO dao;
	
	public int insertAttach(AttachDTO attach) {
		int result = dao.insertAttach(attach);
		return result;
	}
	
	public List<AttachDTO> getAttachList(int noNo){
		List<AttachDTO> result = dao.getAttachList(noNo);
		return result;
	}
	
}
