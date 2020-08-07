package com.desafio.sos.restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.sos.entity.PatrimonioEntity;
import com.desafio.sos.service.PatrimonioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/patrimonios")
@CrossOrigin(origins = "*")
@Api(value = "API REST PATRIMÔNIOS")
public class PatrimonioRestController {

	@Autowired
	private PatrimonioService service;

	@GetMapping
	@ApiOperation(value = "Retorna uma lista de todos os Patrimônios.")
	private ResponseEntity<List<PatrimonioEntity>> listarPatrimonios() {
		return new ResponseEntity<List<PatrimonioEntity>>(service.listarPatrimonios(), HttpStatus.OK);
	}

	@GetMapping(path = "{id}")
	@ApiOperation(value = "Busca um Patrimônio pelo ID")
	private ResponseEntity<PatrimonioEntity> buscarPatrimonioPorId(@PathVariable(value = "id") long id)
			throws Exception {
		return new ResponseEntity<PatrimonioEntity>(service.buscarPatrimonioPorId(id), HttpStatus.OK);
	}

	@GetMapping(path = "{id}/marca")
	@ApiOperation(value = "Busca Patrimônios de uma marca específica")
	private ResponseEntity<List<PatrimonioEntity>> buscarPatrimonioDeUmaMarca(@PathVariable(value = "id") long id)
			throws Exception {
		return new ResponseEntity<List<PatrimonioEntity>>(service.buscarPatrimonioPorMarca(id), HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Insere um novo Patrimônio")
	public ResponseEntity<PatrimonioEntity> cadastrarPatrimonio(@Valid @RequestBody PatrimonioEntity patrimonio) {
		return new ResponseEntity<PatrimonioEntity>(service.cadastrarPatrimonio(patrimonio), HttpStatus.CREATED);
	}

	@DeleteMapping(path = "{id}")
	@ApiOperation(value = "Deleta um Patrimônio")
	public ResponseEntity<PatrimonioEntity> deletaPatrimonio(@PathVariable(value = "id") long id) throws Exception {
		return new ResponseEntity<PatrimonioEntity>(service.deletarPatrimonio(id), HttpStatus.NO_CONTENT);
	}

	@PutMapping(path = "{id}")
	@ApiOperation(value = "Altera os dados de um Patrimônio")
	public ResponseEntity<PatrimonioEntity> atualizaPatrimonio(@Valid @RequestBody PatrimonioEntity patrimonio,
			@PathVariable(value = "id") long id) throws Exception {
		return new ResponseEntity<PatrimonioEntity>(service.atualizarPatrimonio(patrimonio, id), HttpStatus.OK);
	}

}
