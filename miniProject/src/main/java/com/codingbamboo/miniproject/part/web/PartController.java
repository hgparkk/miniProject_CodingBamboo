package com.codingbamboo.miniproject.part.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codingbamboo.miniproject.part.dto.PartDTO;
import com.codingbamboo.miniproject.part.service.PartService;

@Controller
public class PartController {
	@Autowired
	PartService partservice;
	
	@RequestMapping("/partView")
	public String partView() {	
		return "part/partView";
	}
	
	@ResponseBody
	@RequestMapping(value="/getMiddleField", method = RequestMethod.POST)
	public List<String> getMiddleField(String peLargeField){
		PartDTO part = new PartDTO();
		part.setPeLargeField(peLargeField);
		return partservice.PE_middleField(part);
	}
	
	@ResponseBody
	@RequestMapping(value="/getSmallField", method = RequestMethod.POST)
	public List<String> getSmallField(String peLargeField, String peMiddleField){
		PartDTO part = new PartDTO();
		part.setPeLargeField(peLargeField);
		part.setPeMiddleField(peMiddleField);
		return partservice.PE_smallField(part);
	}
	
	@ResponseBody
	@RequestMapping(value="/getPart", method = RequestMethod.POST)
	public List<String> getPart(String peLargeField, String peMiddleField, String peSmallField){
		PartDTO part = new PartDTO();
		part.setPeLargeField(peLargeField);
		part.setPeMiddleField(peMiddleField);
		part.setPeSmallField(peSmallField);
		return partservice.PE_smallField(part);
	}
	
	@ResponseBody
	@RequestMapping(value="/getresult", method = RequestMethod.POST)
	public List<PartDTO> getresult(PartDTO part){
		return partservice.result(part);
	}
	
	
	
	
}


