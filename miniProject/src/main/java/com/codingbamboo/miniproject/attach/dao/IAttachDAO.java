package com.codingbamboo.miniproject.attach.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codingbamboo.miniproject.attach.dto.AttachDTO;

@Mapper
public interface IAttachDAO {
	int insertAttach(AttachDTO attach);
	List<AttachDTO> getAttachList(int noNo);
}
