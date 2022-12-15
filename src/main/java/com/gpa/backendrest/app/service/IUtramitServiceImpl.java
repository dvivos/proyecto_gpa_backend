package com.gpa.backendrest.app.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpa.backendrest.app.dao.IUtramitDao;
import com.gpa.backendrest.app.entity.Utramit;

@Service
public class IUtramitServiceImpl implements IUtramitService {

	@Autowired
	private IUtramitDao utramitDao;
	
	@Override
	@Transactional
	public Utramit save(Utramit utramit) {
		return utramitDao.save(utramit);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		utramitDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Utramit> findAll() {
		
		return (List<Utramit>) utramitDao.findAll();
	}


	@Override
	@Transactional
	public Utramit findByCutramit(String cutramit) {
		
		return utramitDao.findByCutramit(cutramit);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Utramit> findAllByOrderByDescortaAsc() {
		
		return (List<Utramit>) utramitDao.findAllByOrderByDescortaAsc();
	}
	
	@Override
	@Transactional
	public List<Utramit> findAllByOrderByCutramitAsc() {
		// TODO Auto-generated method stub
		return (List<Utramit>) utramitDao.findAllByOrderByCutramitAsc();
	}

}
