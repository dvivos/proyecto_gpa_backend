package com.gpa.backendrest.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gpa.backendrest.app.entity.Documento;

public interface IDocumentoDao extends CrudRepository<Documento, Long> {
	
	public Optional<Documento> findById(Long id);
	
	@Query("select d from Documento d where d.id=?1")
	public Documento findByIdSQL(Long id);
	
	@Query(value = "select d from Documento d where d.centidad.centidad = :centidad and d.yejecont = :yejecont and d.ydoconta = :ydoconta and d.ndoconta = :ndoconta")
	public Documento documentoByCentidadAndYejecontAndYdocontaAndNdoconta(@Param("centidad") String centidad,
																   @Param("yejecont") int yejecont,
																   @Param("ydoconta") int ydoconta,
																   @Param("ndoconta") Long ndoconta);
	
	public void delete(Documento documento);


}
