package com.gpa.backendrest.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpa.backendrest.app.dao.IEntidadDao;
import com.gpa.backendrest.app.entity.Entidad;

@Service
public class IEntidadServiceImpl implements IEntidadService {

	@Autowired
	private IEntidadDao entidadDao;
	
	@Override
	@Transactional
	public Entidad save(Entidad entidad) {
		
		return entidadDao.save(entidad);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		entidadDao.deleteById(id);

	}


	@Override
	@Transactional(readOnly = true)
	public Entidad findByCentidad(String centidad) {
		
		return entidadDao.findByCentidad(centidad);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Entidad> findAll() {
		
		return (List<Entidad>) entidadDao.findAll();
	}

	@Override
	public Entidad findById(Long id) {
		
		return entidadDao.findByIdSQL(id);
	}

}
