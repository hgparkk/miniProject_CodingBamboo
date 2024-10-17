package com.codingbamboo.miniproject.electric.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ElectricController {
	
	// 전력략 대비 탄소배출량 계산 페이지 이동
	@RequestMapping(value="/electricCalculation", method=RequestMethod.GET)
	public String electricCalculation() {
		return "electric/electricCalculationView";
	}
}