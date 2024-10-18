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

	// 전력략 대비 탄소배출량 계산 페이지 이동
	@RequestMapping(value = "/electricCalculation", method = RequestMethod.GET)
	public String electricCalculation(Model model) {

		// 전력략 대비 탄소배출량 계산에 필요한 모든 계수 불러오기
		CoefficientDTO avgAreaElectricCoefficient = coefficientService.getCoefficient(4);
		CoefficientDTO electricEmissionCoefficient = coefficientService.getCoefficient(5);

		model.addAttribute("avgAreaElectricCoefficient", avgAreaElectricCoefficient.getAecCoefficient());
		model.addAttribute("electricEmissionCoefficient", electricEmissionCoefficient.getAecCoefficient());

		return "electric/electricCalculationView";
	}
}