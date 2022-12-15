package com.gpa.backendrest.app.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gpa.backendrest.app.entity.Permiso;

public interface IPermisoDao extends CrudRepository<Permiso, Long>{
	
	public List<Permiso> findByCpermiso(String cpermiso);

}
