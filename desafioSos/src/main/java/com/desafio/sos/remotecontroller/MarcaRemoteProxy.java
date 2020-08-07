package com.desafio.sos.remotecontroller;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.desafio.sos.entity.MarcaEntity;
import com.desafio.sos.entity.dto.MarcaDTO;
import com.desafio.sos.service.MarcaService;

@RemoteProxy(name = "MarcaRemoteProxy")
public class MarcaRemoteProxy {

	@Autowired
	MarcaService marcaService;

	@RemoteMethod
	public void salvar(MarcaDTO marca) throws Exception {
		ModelMapper modelMapper = new ModelMapper();
		MarcaEntity marcaPersist = modelMapper.map(marca, MarcaEntity.class);
		marcaService.cadastrarMarca(marcaPersist);

	}

}
