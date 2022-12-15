package com.gpa.backendrest.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gpa.backendrest.app.entity.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
	
	public List<Documento> findAll();
	
	@Query(value = "select d from Documento d where d.centidad.centidad = :centidad and d.yejecont = :yejecont and d.ydoconta = :ydoconta and d.ndoconta = :ndoconta")
	public Documento documentoByCentidadAndYejecontAndYdocontaAndNdoconta(@Param("centidad") String centidad,
																   @Param("yejecont") int yejecont,
																   @Param("ydoconta") int ydoconta,
																   @Param("ndoconta") Long ndoconta);

	public void deleteById(Long id);

}
