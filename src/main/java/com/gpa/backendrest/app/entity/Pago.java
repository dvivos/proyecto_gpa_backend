package com.gpa.backendrest.app.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "pagos", uniqueConstraints = {
		@UniqueConstraint(name = "idx_pagos", columnNames = { "centidad", "ymandpag", "nmandpag" }) })
public class Pago implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "centidad", referencedColumnName = "centidad")
	private Entidad centidad;

	private int ymandpag;
	private int nmandpag;

	@Column(length = 200)
	private String descripcion;

	private Double importe;

	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate faprobacion;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumns({
    	@JoinColumn(name="yejecont", referencedColumnName="yejecont"),
	    @JoinColumn(name="ydoconta", referencedColumnName="ydoconta"),
	    @JoinColumn(name="ndoconta", referencedColumnName="ndoconta")
	  })
	private Documento documento;
	
	private int yrelorpg;
	private int nrelorpg;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cacredeu", referencedColumnName = "cacredeu")
	private Tercero cacredeu;

	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate fordenpago;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate faprobpago;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate fanulacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tfpgoper", referencedColumnName = "tfpgoper")
	private FormaPago tfpgoper;

	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
