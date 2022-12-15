package com.gpa.backendrest.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gpa.backendrest.app.entity.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long> {
	
	public List<Pago> findAll();
	
	@Query(value = "select p from Pago p where p.centidad.centidad = :centidad and p.ymandpag = :ymandpag and p.nmandpag = :nmandpag")
	public Pago pagosByCentidadAndYmandpagAndNmandpag(@Param("centidad") String centidad, 
											   @Param("ymandpag") int ymandpag,
											   @Param("nmandpag") int nmandpag);

}
