package com.gpa.backendrest.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gpa.backendrest.app.entity.Entidad;

public interface EntidadRepository extends JpaRepository<Entidad, Long> {
	
	
	@Query("select e from Entidad e where e.centidad=:centidad")
	public Entidad findByCentidad(@Param("centidad") String centidad);


}
