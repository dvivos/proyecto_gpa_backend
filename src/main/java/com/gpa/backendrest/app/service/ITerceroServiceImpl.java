package com.gpa.backendrest.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpa.backendrest.app.dao.ITerceroDao;
import com.gpa.backendrest.app.entity.Tercero;

@Service
public class ITerceroServiceImpl implements ITerceroService {

	@Autowired
	private ITerceroDao terceroDao;
	
	@Override
	@Transactional
	public Tercero save(Tercero tercero) {
		
		return terceroDao.save(tercero);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		terceroDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Tercero findById(Long id) {
		
		return terceroDao.findByIdSQL(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Tercero findByCdocumento(String cdocumento) {
		
		return terceroDao.findByCdocumento(cdocumento);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tercero> findAll() {
		
		return (List<Tercero>) terceroDao.findAll();
	}

}
