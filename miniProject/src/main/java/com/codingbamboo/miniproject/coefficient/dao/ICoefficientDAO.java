package com.codingbamboo.miniproject.coefficient.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codingbamboo.miniproject.coefficient.dto.CoefficientDTO;

@Mapper
public interface ICoefficientDAO {
	public CoefficientDTO getCoefficient(int aec_no);
	public List<CoefficientDTO> getCoefficientList();
	public int updateCoefficient(CoefficientDTO updatedCoefficient);
}