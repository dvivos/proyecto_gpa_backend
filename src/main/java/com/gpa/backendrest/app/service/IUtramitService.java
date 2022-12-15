package com.gpa.backendrest.app.service;

import java.util.List;

import com.gpa.backendrest.app.entity.Utramit;

public interface IUtramitService {
	
	public Utramit save(Utramit utramit);

	public void delete(Long id);
	
	public Utramit findByCutramit(String cutramit);

	public List<Utramit> findAll();

	public List<Utramit> findAllByOrderByDescortaAsc();
	
	public List<Utramit> findAllByOrderByCutramitAsc();

}
