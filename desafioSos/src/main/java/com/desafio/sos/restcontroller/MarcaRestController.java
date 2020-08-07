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

import com.desafio.sos.entity.MarcaEntity;
import com.desafio.sos.service.MarcaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/marcas")
@CrossOrigin(origins = "*")
@Api(value = "API REST MARCAS")
public class MarcaRestController {
	
	@Autowired
	private MarcaService marcaService;
	
	@GetMapping
	@ApiOperation(value = "Retorna uma lista de todas as Marcas")
	private ResponseEntity<List<MarcaEntity>> listarMarcas() {
		return new ResponseEntity<List<MarcaEntity>>(marcaService.listarMarcas(), HttpStatus.OK);
	}

	@GetMapping(path = "{id}")
	@ApiOperation(value = "Busca uma MarcaDTO pelo ID")
	private ResponseEntity<MarcaEntity> buscarMarcaPorId(@PathVariable(value = "id") long id)
			throws Exception {
		MarcaEntity marca = marcaService.buscarMarcaPorId(id);
		if (marca == null)
			throw new Exception("MarcaDTO não encontrada!");

		return new ResponseEntity<MarcaEntity>(marca, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Insere uma nova MarcaDTO")
	public ResponseEntity<MarcaEntity> cadastrarMarca(@Valid @RequestBody MarcaEntity marca) throws Exception {
		return new ResponseEntity<MarcaEntity>(marcaService.cadastrarMarca(marca), HttpStatus.CREATED);
	}

	@DeleteMapping(path = "{id}")
	@ApiOperation(value = "Deleta uma MarcaDTO")
	public ResponseEntity<MarcaEntity> deletaMarca(@PathVariable(value = "id") long id) throws Exception {
		return new ResponseEntity<MarcaEntity>(marcaService.deletarMarca(id), HttpStatus.NO_CONTENT);
	}

	@PutMapping(path = "{id}")
	@ApiOperation(value = "Altera os dados de uma MarcaDTO")
	public ResponseEntity<MarcaEntity> atualizaMarca(@Valid @RequestBody MarcaEntity marca,
			@PathVariable(value = "id") long id) throws Exception {
		return new ResponseEntity<MarcaEntity>(marcaService.atualizarMarca(marca, id), HttpStatus.OK);
	}

}
