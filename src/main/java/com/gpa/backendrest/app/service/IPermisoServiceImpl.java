package com.gpa.backendrest.app.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpa.backendrest.app.dao.IPermisoDao;
import com.gpa.backendrest.app.entity.Permiso;

@Service
public class IPermisoServiceImpl implements IPermisoService {

	@Autowired
	private IPermisoDao permisoDao;

	@Override
	@Transactional
	public Permiso save(Permiso permiso) {

		return permisoDao.save(permiso);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		permisoDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Permiso> findByCpermiso(String cpermiso) {

		return (List<Permiso>) permisoDao.findByCpermiso(cpermiso);
	}

}
