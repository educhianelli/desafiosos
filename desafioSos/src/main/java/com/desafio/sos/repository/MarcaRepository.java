package com.desafio.sos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.desafio.sos.entity.MarcaEntity;

public interface MarcaRepository extends JpaRepository<MarcaEntity, Long> {

	MarcaEntity findByNome(@Param("nome") String nome);

}
