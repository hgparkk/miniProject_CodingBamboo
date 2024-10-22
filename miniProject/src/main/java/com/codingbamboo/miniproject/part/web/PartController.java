package com.codingbamboo.miniproject.part.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codingbamboo.miniproject.part.dto.PartDTO;
import com.codingbamboo.miniproject.part.service.PartService;

@Controller
public class PartController {
	@Autowired
	PartService partService;
	
	@RequestMapping("/partView")
	public String partView() {	
		return "part/partView";
	}
	
	@ResponseBody
	@RequestMapping(value="/getMiddleField", method = RequestMethod.POST)
	public List<String> getMiddleField(String peLargeField){
		PartDTO part = new PartDTO();
		part.setPeLargeField(peLargeField);
		return partService.getMiddleField(part);
	}
	
	@ResponseBody
	@RequestMapping(value="/getSmallField", method = RequestMethod.POST)
	public List<String> getSmallField(String peLargeField, String peMiddleField){
		PartDTO part = new PartDTO();
		part.setPeLargeField(peLargeField);
		part.setPeMiddleField(peMiddleField);
		return partService.getSmallField(part);
	}
	
	@ResponseBody
	@RequestMapping(value="/getPart", method = RequestMethod.POST)
	public List<String> getPart(String peLargeField, String peMiddleField, String peSmallField){
		PartDTO part = new PartDTO();
		part.setPeLargeField(peLargeField);
		part.setPeMiddleField(peMiddleField);
		part.setPeSmallField(peSmallField);
		return partService.getPart(part);
	}
	
	@ResponseBody
	@RequestMapping(value="/getResult", method = RequestMethod.POST)
	public List<PartDTO> getResult(PartDTO part){
		return partService.getResult(part);
	}
}