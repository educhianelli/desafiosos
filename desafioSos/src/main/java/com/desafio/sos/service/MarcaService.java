package com.desafio.sos.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.desafio.sos.entity.MarcaEntity;
import com.desafio.sos.repository.MarcaRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class MarcaService {

	@Autowired
	private MarcaRepository repository;

	@Transactional
	public MarcaEntity cadastrarMarca(@RequestBody MarcaEntity marca) throws Exception {
		MarcaEntity marcaEntity = buscarMarcaPeloNome(marca.getNome());
		if(marcaEntity != null) {
			throw new Exception("Marca " + marcaEntity.getNome() + " já existe!");
		}else {
			marcaEntity = new MarcaEntity();
			marcaEntity = repository.save(marca);

		}
		return marcaEntity;
	}

	public MarcaEntity deletarMarca(Long id) throws ObjectNotFoundException {
		MarcaEntity marca = buscarMarcaPorId(id);
		repository.deleteById(id);
		return marca;

	}

	public MarcaEntity buscarMarcaPorId(long id) throws ObjectNotFoundException {
		Optional<MarcaEntity> marca = repository.findById(id);
		return marca.orElseThrow(() -> new ObjectNotFoundException("Marca: " + id + " não encontrado!"));
	}

	public MarcaEntity atualizarMarca(MarcaEntity marcaEntity, Long id) throws ObjectNotFoundException {

		MarcaEntity marca = buscarMarcaPorId(id);
		marca.setNome(marcaEntity.getNome());
		marca = repository.save(marca);

		return marca;
	}

	public MarcaEntity buscarMarcaPeloNome(String nome) {
		MarcaEntity marca = repository.findByNome(nome);
		return marca;
	}

	public List<MarcaEntity> listarMarcas() {
		List<MarcaEntity> listaMarcas = repository.findAll();
		return listaMarcas;
	}
}
