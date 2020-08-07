package com.desafio.sos.web.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;

import com.desafio.sos.entity.PatrimonioEntity;
import com.desafio.sos.repository.PatrimonioRepository;

public class TabelaPatrimonio {

	private String[] cols = { "id", "nome", "marca", "descricao", "tombo" };

	public Map<String, Object> execute(PatrimonioRepository pRepository, HttpServletRequest request) {
		Map<String, Object> json = new LinkedHashMap<>();
		int start = Integer.parseInt(request.getParameter("start"));
		int length = Integer.parseInt(request.getParameter("length"));
		int draw = Integer.parseInt(request.getParameter("draw"));
		int current = currentPage(start, length);

		String column = columnName(request);
		Sort.Direction direction = orderBy(request);
		String search = searchBy(request);

		Pageable pageable = PageRequest.of(current, length, direction, column);
		Page<PatrimonioEntity> page = queryBy(search, pRepository, pageable);

		json.put("draw", null);
		json.put("recordsTotal", page.getTotalElements());
		json.put("recordsFiltered", page.getTotalElements());
		json.put("data", page.getContent());
		return json;
	}

	private Page<PatrimonioEntity> queryBy(String search, PatrimonioRepository pRepository, Pageable pageable) {
		if (search.isEmpty()) {
			return pRepository.findAll(pageable);
		}
		return pRepository.findByNomeContaining(search, pageable);

	}

	private Direction orderBy(HttpServletRequest request) {
		String order = request.getParameter("order[0][dir]");
		Sort.Direction sort = Sort.Direction.ASC;
		if (order.equalsIgnoreCase("desc"))
			sort = Sort.Direction.DESC;
		return sort;
	}

	private String searchBy(HttpServletRequest request) {

		return request.getParameter("search[value]").isEmpty() ? "" : request.getParameter("search[value]");
	}

	private String columnName(HttpServletRequest request) {
		int iCol = Integer.parseInt(request.getParameter("order[0][column]"));

		return cols[iCol];
	}

	private int currentPage(int start, int length) {
		// TODO Auto-generated method stub
		return start / length;
	}

}
