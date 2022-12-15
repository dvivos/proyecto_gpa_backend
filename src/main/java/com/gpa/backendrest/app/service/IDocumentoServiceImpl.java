package com.gpa.backendrest.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpa.backendrest.app.dao.IDocumentoDao;
import com.gpa.backendrest.app.entity.Documento;
import com.gpa.backendrest.app.repository.DocumentoRepository;

@Service
public class IDocumentoServiceImpl implements IDocumentoService {

	@Autowired
	DocumentoRepository documentoRepository;
	
	@Autowired
	IDocumentoDao documentoDao;
	
	@Override
	@Transactional
	public void deleteById(Long id) {
		documentoRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Documento findById(Long id) {
		return documentoDao.findByIdSQL(id);
	}

	@Override
	@Transactional
	public void delete(Documento documento) {
		documentoDao.delete(documento);
	}

	@Override
	@Transactional(readOnly = true)
	public Documento documentoByCentidadAndYejecontAndYdocontaAndNdoconta(String centidad, int yejecont, int ydoconta,
			Long ndoconta) {
		
		return documentoDao.documentoByCentidadAndYejecontAndYdocontaAndNdoconta(centidad, yejecont, ydoconta, ndoconta);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Documento> findAll() {
		
		return (List<Documento>) documentoDao.findAll();
	}

	@Override
	@Transactional
	public Documento save(Documento documento) {
		
		return documentoDao.save(documento);
	}
	

}
