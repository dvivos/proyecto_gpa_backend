package com.gpa.backendrest.app.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import com.gpa.backendrest.app.entity.Documento;
import com.gpa.backendrest.app.entity.Entidad;
import com.gpa.backendrest.app.entity.FormaPago;
import com.gpa.backendrest.app.entity.Tercero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PagoDto implements Serializable {

	
	private Long id;
	private Entidad centidad;
	private int ymandpag;
	private int nmandpag;
	private String descripcion;
	private Double importe;
	private LocalDate faprobacion;
	private Documento documento;
	private int yrelorpg;
	private int nrelorpg;
	private Tercero cacredeu;
	private LocalDate fordenpago;
	private LocalDate faprobpago;
	private LocalDate fanulacion;
	private FormaPago tfpgoper;
	private Date createAt;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	

}
