package com.desafio.sos.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.desafio.sos.entity.MarcaEntity;
import com.desafio.sos.entity.PatrimonioEntity;
import com.desafio.sos.repository.PatrimonioRepository;
import com.desafio.sos.restcontroller.PatrimonioRestController;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class PatrimonioService {

	@Autowired
	private PatrimonioRepository repository;

	@Transactional
	public PatrimonioEntity cadastrarPatrimonio(@RequestBody PatrimonioEntity patrimonio) {
		patrimonio.setTombo(String.valueOf(new Date().getTime()));
		PatrimonioEntity patrimonioPersist = repository.save(patrimonio);
		return patrimonioPersist;
	}

	public PatrimonioEntity deletarPatrimonio(Long id) throws ObjectNotFoundException {
		PatrimonioEntity patrimonio = buscarPatrimonioPorId(id);
		repository.deleteById(id);
		return patrimonio;

	}

	public PatrimonioEntity buscarPatrimonioPorId(long id) throws ObjectNotFoundException {
		Optional<PatrimonioEntity> patrimonio = repository.findById(id);
		return patrimonio.orElseThrow(() -> new ObjectNotFoundException("Patrimônio: " + id + " não encontrado!"));
	}

	public PatrimonioEntity atualizarPatrimonio(PatrimonioEntity patrimonioEntity, Long id)
			throws ObjectNotFoundException {

		PatrimonioEntity patrimonio = buscarPatrimonioPorId(id);
		patrimonio.setNome(patrimonioEntity.getNome());
		patrimonio.setMarca(patrimonioEntity.getMarca());
		patrimonio.setDescricao(patrimonioEntity.getDescricao());
		patrimonio = repository.save(patrimonio);

		return patrimonio;
	}

	public List<PatrimonioEntity> buscarPatrimonioLikeDescricao(String descricao) {
		List<PatrimonioEntity> patrimonios = repository.findByDescricaoContaining(descricao);
		return patrimonios;
	}

	public List<PatrimonioEntity> listarPatrimonios() {
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		List<PatrimonioEntity> listaPatrimonios = repository.findAll(sort);
		return listaPatrimonios;
	}
	
	public List<PatrimonioEntity> buscarPatrimonioPorMarca(long id) {
		List<PatrimonioEntity> listaPatrimonios = repository.findPatrimoniosPorMarca(id);
		return listaPatrimonios;
	}
}
