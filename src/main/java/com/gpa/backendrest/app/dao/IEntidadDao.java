package com.gpa.backendrest.app.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gpa.backendrest.app.entity.Entidad;

public interface IEntidadDao extends CrudRepository<Entidad, Long> {
	
	public Entidad findByCentidad(String centidad);
	
	@Query("select e from Entidad e where e.id=?1")
	public Entidad findByIdSQL(Long id);
	
	

}
