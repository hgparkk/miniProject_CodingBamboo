package com.codingbamboo.miniproject.material.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codingbamboo.miniproject.coefficient.dto.CoefficientDTO;
import com.codingbamboo.miniproject.coefficient.service.CoefficientService;
import com.codingbamboo.miniproject.material.dto.MaterialDTO;
import com.codingbamboo.miniproject.material.service.MaterialService;
import com.codingbamboo.miniproject.user.dto.UserDTO;
import com.google.gson.Gson;

@Controller
public class MaterialController {

	@Autowired
	MaterialService materialService;
	
	@Autowired
	CoefficientService coefficientService;

	// ���纰 ź�� ���ⷮ ��� ������ �̵�
	@RequestMapping(value = "/materialCalculation", method = RequestMethod.GET)
	public String materialCalculation(Model model) {

		// �������� ��� �ҷ�����
		List<MaterialDTO> materialList = materialService.selectAllMaterial("");

		model.addAttribute("materialList", materialList);
		
		// ���纰 ź�� ���ⷮ ��꿡 �ʿ��� ��� ��� �ҷ�����
		CoefficientDTO residentialCoefficient = coefficientService.getCoefficient(1);
		CoefficientDTO commercialCoefficient = coefficientService.getCoefficient(2);
		CoefficientDTO publicCoefficient = coefficientService.getCoefficient(3);
		
		model.addAttribute("residentialCoefficient",residentialCoefficient.getAecCoefficient());
		model.addAttribute("commercialCoefficient",commercialCoefficient.getAecCoefficient());
		model.addAttribute("publicCoefficient",publicCoefficient.getAecCoefficient());

		return "material/materialCalculationView";
	}

	// ���� �˻� ��� ������
	@ResponseBody
	@RequestMapping(value = "/materialSearch", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public String materialSearch(String word) {
		Gson gson = new Gson();
		String result = gson.toJson(materialService.selectAllMaterial(word));

		return result;
	}

	// ������ ź�� ���ⷮ �ҷ�����
	@ResponseBody
	@RequestMapping(value = "/getMaterial", method = RequestMethod.POST)
	public MaterialDTO getMaterial(int meNo) {
		return materialService.selectMaterial(meNo);
	}

	// ������ ���
	// ���� ���� ������ �̵�
	@RequestMapping("/adminMaterialView")
	public String adminMaterialView(Model model, String searchWord, HttpSession session) {
		
		UserDTO login = (UserDTO)session.getAttribute("login");
		if (login == null || !login.getUserId().equals("admin")) {
			return "redirect:/";
		}
		
		List<MaterialDTO> materialList = new ArrayList<>();

		if (searchWord == null || searchWord.isEmpty()) {
			materialList = materialService.getMaterialList("");
		} else {
			materialList = materialService.getMaterialList(searchWord);
		}
		model.addAttribute("keyMaterialList", materialList);
		
		return "admin/adminMaterialView";

	}

	// ���� ����
	@PostMapping("/deleteMaterial")
	public String deleteMaterial(HttpServletRequest request, int meNo) {

		materialService.deleteMaterial(meNo);

		request.setAttribute("msg", "������ �Ϸ�Ǿ����ϴ�.");
		request.setAttribute("url", "/adminMaterialView");
		return "alert";
	}

	// ���� ����
	@PostMapping("/updateMaterial")
	public String updateMaterial(HttpServletRequest request, MaterialDTO material) {

		materialService.updateMaterial(material);

		request.setAttribute("msg", "������ �Ϸ�Ǿ����ϴ�.");
		request.setAttribute("url", "/adminMaterialView");
		return "alert";
	}
	
	// ���� ����
	@PostMapping("/insertMaterial")
	public String insertMaterial(HttpServletRequest request, MaterialDTO material) {
		
		materialService.insertMaterial(material);
		
		request.setAttribute("msg", "���簡 �߰��Ǿ����ϴ�.");
		request.setAttribute("url", "/adminMaterialView");
		return "alert";
	}
}