package com.gpa.backendrest.app.model;

import java.time.LocalDate;
import java.util.Date;

import com.gpa.backendrest.app.entity.Entidad;
import com.gpa.backendrest.app.entity.Tercero;

public class DocumentoDto {
	
	private Long id;

	private Entidad centidad;

	private int yejecont;
	private int ydoconta;
	private Long ndoconta;
	private int noperdia;
	
	private LocalDate facuerop;
	
	private LocalDate foperdia;
	
	private Double importe;
	
	private String descripcion;
	
	private Tercero cacredeu;
	
	private int yreldoco;
	private int nreldoco;
	
	private String codfirma;
	
	private Date createAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Entidad getCentidad() {
		return centidad;
	}

	public void setCentidad(Entidad centidad) {
		this.centidad = centidad;
	}

	public int getYejecont() {
		return yejecont;
	}

	public void setYejecont(int yejecont) {
		this.yejecont = yejecont;
	}

	public int getYdoconta() {
		return ydoconta;
	}

	public void setYdoconta(int ydoconta) {
		this.ydoconta = ydoconta;
	}

	public Long getNdoconta() {
		return ndoconta;
	}

	public void setNdoconta(Long ndoconta) {
		this.ndoconta = ndoconta;
	}

	public int getNoperdia() {
		return noperdia;
	}

	public void setNoperdia(int noperdia) {
		this.noperdia = noperdia;
	}

	public LocalDate getFacuerop() {
		return facuerop;
	}

	public void setFacuerop(LocalDate facuerop) {
		this.facuerop = facuerop;
	}

	public LocalDate getFoperdia() {
		return foperdia;
	}

	public void setFoperdia(LocalDate foperdia) {
		this.foperdia = foperdia;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Tercero getCacredeu() {
		return cacredeu;
	}

	public void setCacredeu(Tercero cacredeu) {
		this.cacredeu = cacredeu;
	}

	public int getYreldoco() {
		return yreldoco;
	}

	public void setYreldoco(int yreldoco) {
		this.yreldoco = yreldoco;
	}

	public int getNreldoco() {
		return nreldoco;
	}

	public void setNreldoco(int nreldoco) {
		this.nreldoco = nreldoco;
	}

	public String getCodfirma() {
		return codfirma;
	}

	public void setCodfirma(String codfirma) {
		this.codfirma = codfirma;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	

}
