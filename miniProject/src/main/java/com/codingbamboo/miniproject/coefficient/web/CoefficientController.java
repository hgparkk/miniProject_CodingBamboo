package com.codingbamboo.miniproject.coefficient.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codingbamboo.miniproject.coefficient.dto.CoefficientDTO;
import com.codingbamboo.miniproject.coefficient.service.CoefficientService;

@Controller
public class CoefficientController {
	
	@Autowired
	CoefficientService coefficientService;
	
	@RequestMapping(value="/adminCoefficientView", method=RequestMethod.GET)
	public String adminCoefficientView(Model model) {
		List<CoefficientDTO> coefficientList = coefficientService.getCoefficientList();
		model.addAttribute("coefficientList",coefficientList);
		return "admin/adminCoefficientView";
	}
	
	@RequestMapping(value="/updateCoefficient", method=RequestMethod.GET)
	public String updateCoefficient(int aecNo, double aecCoefficient, HttpServletRequest request) {
		CoefficientDTO updatedCoefficient = new CoefficientDTO(aecNo,"",aecCoefficient);
		coefficientService.updateCoefficient(updatedCoefficient);
		
		request.setAttribute("msg", "����� ����Ǿ����ϴ�");
		request.setAttribute("url", "/adminCoefficientView");
		return "alert";
	}
}