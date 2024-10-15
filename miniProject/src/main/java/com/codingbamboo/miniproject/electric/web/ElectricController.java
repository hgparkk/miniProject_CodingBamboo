package com.codingbamboo.miniproject.electric.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ElectricController {
	
	@RequestMapping(value="/electricCalculation", method=RequestMethod.GET)
	public String electricCalculation() {
		return "electric/electricCalculationView";
	}
}