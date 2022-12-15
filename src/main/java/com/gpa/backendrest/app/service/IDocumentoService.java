package com.gpa.backendrest.app.service;

import java.util.List;


import com.gpa.backendrest.app.entity.Documento;

public interface IDocumentoService {
	
	public void deleteById(Long id);
	
	public void delete(Documento documento);
	
	public Documento findById(Long id);
	
	public List<Documento> findAll();
	
	public Documento save(Documento documento);
	
	public Documento documentoByCentidadAndYejecontAndYdocontaAndNdoconta(String centidad,
			   int yejecont,
			   int ydoconta,
			   Long ndoconta);

}
