package com.desafio.sos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.desafio.sos.entity.PatrimonioEntity;

public interface PatrimonioRepository extends JpaRepository<PatrimonioEntity, Long> {

	List<PatrimonioEntity> findByDescricaoContaining(@Param("descricao") String descricao);

	Page<PatrimonioEntity> findByNomeContaining(@Param("search") String search, Pageable pageable);

	@Query("select p from PatrimonioEntity p where p.marca.id = :id ")
	List<PatrimonioEntity> findPatrimoniosPorMarca(@Param("id") long id);

}
