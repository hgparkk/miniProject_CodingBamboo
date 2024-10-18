package com.codingbamboo.miniproject.electric.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codingbamboo.miniproject.coefficient.dto.CoefficientDTO;
import com.codingbamboo.miniproject.coefficient.service.CoefficientService;

@Controller
public class ElectricController {

	@Autowired
	CoefficientService coefficientService;

	// ���·� ��� ź�ҹ��ⷮ ��� ������ �̵�
	@RequestMapping(value = "/electricCalculation", method = RequestMethod.GET)
	public String electricCalculation(Model model) {

		// ���·� ��� ź�ҹ��ⷮ ��꿡 �ʿ��� ��� ��� �ҷ�����
		CoefficientDTO avgAreaElectricCoefficient = coefficientService.getCoefficient(4);
		CoefficientDTO electricEmissionCoefficient = coefficientService.getCoefficient(5);

		model.addAttribute("avgAreaElectricCoefficient", avgAreaElectricCoefficient.getAecCoefficient());
		model.addAttribute("electricEmissionCoefficient", electricEmissionCoefficient.getAecCoefficient());

		return "electric/electricCalculationView";
	}
}