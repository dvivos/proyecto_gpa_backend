package com.gpa.backendrest.app.service;

import java.util.List;

import com.gpa.backendrest.app.entity.Entidad;

public interface IEntidadService {
	
	public Entidad save(Entidad entidad);
	
	public void delete(Long id);
	
	public Entidad findById(Long id);
	
	public Entidad findByCentidad(String centidad);
	
	public List<Entidad> findAll();

}
