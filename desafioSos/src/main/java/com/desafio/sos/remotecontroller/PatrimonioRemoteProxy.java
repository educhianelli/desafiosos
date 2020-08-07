package com.desafio.sos.remotecontroller;

import java.util.ArrayList;
import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.desafio.sos.entity.MarcaEntity;
import com.desafio.sos.entity.PatrimonioEntity;
import com.desafio.sos.entity.dto.PatrimonioDTO;
import com.desafio.sos.service.MarcaService;
import com.desafio.sos.service.PatrimonioService;

import javassist.tools.rmi.ObjectNotFoundException;

@RemoteProxy(name = "PatrimonioRemoteProxy")
public class PatrimonioRemoteProxy {

	@Autowired
	PatrimonioService patrimonioService;
	@Autowired
	MarcaService marcaService;

	@RemoteMethod
	public void salvar(PatrimonioDTO patrimonio) throws ObjectNotFoundException {
		MarcaEntity marcaEntity = marcaService.buscarMarcaPorId(patrimonio.getMarca());
		ModelMapper modelMapper = new ModelMapper();
		PatrimonioEntity patrimonioPersist = modelMapper.map(patrimonio, PatrimonioEntity.class);
		patrimonioPersist.setMarca(marcaEntity);

		patrimonioService.cadastrarPatrimonio(patrimonioPersist);

	}

	@RemoteMethod
	public void excluirPatrimonio(long id) throws ObjectNotFoundException {
		System.out.println("Id: " + id);
		patrimonioService.deletarPatrimonio(id);
	}
	
	
	
	@RemoteMethod
	public List<PatrimonioDTO> listarPatrimonio() {
		List<PatrimonioDTO> ret = new ArrayList<>();
		List<PatrimonioEntity> listEntity = patrimonioService.listarPatrimonios();
		for (PatrimonioEntity pat : listEntity) {
			PatrimonioDTO dto = new PatrimonioDTO();
			dto.setMarca(pat.getMarca().getId());
			ret.add(dto);
		}
		return ret;
	}
	
	

}
