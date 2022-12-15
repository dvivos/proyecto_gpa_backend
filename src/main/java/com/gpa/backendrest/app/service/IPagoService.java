package com.gpa.backendrest.app.service;

import java.util.List;

import com.gpa.backendrest.app.entity.Pago;

public interface IPagoService {
	
	public Pago findById(Long id);
	
	public Pago save(Pago pago);
	
	public List<Pago> findAll();
	
	public Pago pagosByCentidadAndYmandpagAndNmandpag(String centidad,int ymandpag,int nmandpag);

}
