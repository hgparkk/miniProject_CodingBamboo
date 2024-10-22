package com.codingbamboo.miniproject.part.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codingbamboo.miniproject.part.dto.PartDTO;

@Mapper
public interface IPartDAO {

	List<String> getMiddleField(PartDTO part);
	
	List<String> getSmallField(PartDTO part);
	
	List<String> getPart(PartDTO part);
	
	List<PartDTO> getResult(PartDTO part);
	
}