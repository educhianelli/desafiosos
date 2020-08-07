package com.desafio.sos.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.desafio.sos.entity.MarcaEntity;
import com.desafio.sos.repository.PatrimonioRepository;
import com.desafio.sos.service.MarcaService;
import com.desafio.sos.service.PatrimonioService;

@Controller
public class PageController {

	@Autowired
	MarcaService marcaService;

	@Autowired
	PatrimonioService patrimonioService;
	
	@Autowired
	PatrimonioRepository patrimonioRepository;

	@GetMapping("/ativos")
	public String init() {
		return "patrimonio-add";
	}

	@GetMapping(value = "/modal")
	public String addStudentModal(ModelMap model) {
		return "modal";
	}

	@ModelAttribute("marcas")
	public List<MarcaEntity> getMarcas() {
		return marcaService.listarMarcas();
	}

	@GetMapping("/listPatrimonio")
	public String listaPatrimonios(ModelMap model) {
		model.addAttribute("patrimonios", patrimonioService.listarPatrimonios());
		return "patrimonio-list";
	}

	@GetMapping("/tabela")
	public String showTabela() {
		return "patrimonio-list";

	}
	
	@GetMapping("/tabela/server")
	public ResponseEntity<?> tabela(HttpServletRequest request){
		Map<String, Object> data = new TabelaPatrimonio().execute(patrimonioRepository,request);
		return ResponseEntity.ok(data);
	}
}
