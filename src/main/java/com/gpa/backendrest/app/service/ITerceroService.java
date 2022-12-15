package com.gpa.backendrest.app.service;

import java.util.List;

import com.gpa.backendrest.app.entity.Tercero;

public interface ITerceroService {
	
	public Tercero save(Tercero tercero);
	
	public void delete(Long id);
	
	public Tercero findById(Long id);
	
	public Tercero findByCdocumento(String cdocumento);
	
	public List<Tercero> findAll();

}
