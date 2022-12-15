package com.gpa.backendrest.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpa.backendrest.app.dao.IPagoDao;
import com.gpa.backendrest.app.entity.Pago;

@Service
public class IPagoServiceImpl implements IPagoService {
	
	@Autowired
	private IPagoDao pagoDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public Pago findById(Long id) {
		
		return pagoDao.findByIdSQL(id);
	}

	@Override
	@Transactional
	public Pago save(Pago pago) {
		
		return pagoDao.save(pago);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pago> findAll() {
		
		return (List<Pago>) pagoDao.findAll();
	}

	@Override
	public Pago pagosByCentidadAndYmandpagAndNmandpag(String centidad, int ymandpag, int nmandpag) {
		
		return pagoDao.pagosByCentidadAndYmandpagAndNmandpag(centidad, ymandpag, nmandpag);
	}

}
