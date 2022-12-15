package com.gpa.backendrest.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gpa.backendrest.app.entity.Pago;

public interface IPagoDao extends CrudRepository<Pago, Long> {
	
	public Optional<Pago> findById(Long id);
	
	@Query("select p from Pago p where p.id=?1")
	public Pago findByIdSQL(Long id);
	
	@Query(value = "select p from Pago p where p.centidad.centidad = :centidad and p.ymandpag = :ymandpag and p.nmandpag = :nmandpag")
	public Pago pagosByCentidadAndYmandpagAndNmandpag(@Param("centidad") String centidad, 
											   @Param("ymandpag") int ymandpag,
											   @Param("nmandpag") int nmandpag);

}
