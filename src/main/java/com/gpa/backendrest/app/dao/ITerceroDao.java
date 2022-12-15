package com.gpa.backendrest.app.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gpa.backendrest.app.entity.Tercero;

public interface ITerceroDao extends CrudRepository<Tercero, Long> {
	
	public Tercero findByCdocumento(String cdocumento);
	
	@Query("select t from Tercero t where t.id=?1")
	public Tercero findByIdSQL(Long id);

}
