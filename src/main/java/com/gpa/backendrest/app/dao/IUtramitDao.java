package com.gpa.backendrest.app.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gpa.backendrest.app.entity.Utramit;

public interface IUtramitDao extends CrudRepository<Utramit, Long> {
	
	public Utramit findByCutramit(String cutramit);
	
	public List<Utramit> findAllByOrderByDescortaAsc();
	
	public List<Utramit> findAllByOrderByCutramitAsc();

}
