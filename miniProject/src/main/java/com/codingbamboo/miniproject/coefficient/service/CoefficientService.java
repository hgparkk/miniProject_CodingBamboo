package com.codingbamboo.miniproject.coefficient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingbamboo.miniproject.coefficient.dao.ICoefficientDAO;
import com.codingbamboo.miniproject.coefficient.dto.CoefficientDTO;

@Service
public class CoefficientService {
	
	@Autowired
	ICoefficientDAO dao;
	
	public CoefficientDTO getCoefficient(int aecNo) {
		return dao.getCoefficient(aecNo);
	}
}
