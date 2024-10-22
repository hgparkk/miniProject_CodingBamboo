package com.codingbamboo.miniproject.coefficient.service;

import java.util.List;

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
	
	public List<CoefficientDTO> getCoefficientList(){
		return dao.getCoefficientList();
	}
	
	public int updateCoefficient(CoefficientDTO updatedCoefficient) {
		return dao.updateCoefficient(updatedCoefficient);
	}
}
