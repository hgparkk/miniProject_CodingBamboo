package com.codingbamboo.miniproject.coefficient.dao;

import org.apache.ibatis.annotations.Mapper;

import com.codingbamboo.miniproject.coefficient.dto.CoefficientDTO;

@Mapper
public interface ICoefficientDAO {
	public CoefficientDTO getCoefficient(int aec_no);
}