package com.gpa.backendrest.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gpa.backendrest.app.entity.Utramit;

public interface UtramitRepository extends JpaRepository<Utramit, Long> {
	
	public List<Utramit> findAll();
	
	@Query("select u from Utramit u where u.cutramit=:cutramit ")
	public Utramit findByCutramit(@Param("cutramit") String cutramit);
	
	

}
