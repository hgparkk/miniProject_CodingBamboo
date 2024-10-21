package com.codingbamboo.miniproject.part.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codingbamboo.miniproject.part.dto.PartDTO;

@Mapper
public interface IPartDAO {

	List<String> PE_middleField(PartDTO part);
	
	List<String> PE_smallField(PartDTO part);
	
	List<String> PE_PART(PartDTO part);
	
	List<PartDTO> result(PartDTO part);
	
}
