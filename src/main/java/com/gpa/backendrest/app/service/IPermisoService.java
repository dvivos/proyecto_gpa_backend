package com.gpa.backendrest.app.service;

import java.util.List;

import com.gpa.backendrest.app.entity.Permiso;

public interface IPermisoService {
	
	public Permiso save(Permiso permiso);

	public void delete(Long id);

	/**
	 * 
	 * @param cpermiso
	 * @return
	 */
	public List<Permiso> findByCpermiso(String cpermiso);
	
	

}
