package com.gpa.backendrest.app.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "documentos", uniqueConstraints = {
		@UniqueConstraint(name = "idx_documentos", columnNames = { "centidad", "yejecont", "ydoconta","ndoconta" }) })
public class Documento implements Serializable {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "centidad", referencedColumnName = "centidad")
	private Entidad centidad;

	private int yejecont;
	private int ydoconta;
	private Long ndoconta;
	private int noperdia;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate facuerop;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate foperdia;
	
	private Double importe;
	
	@Column(length = 250)
	private String descripcion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cacredeu", referencedColumnName = "cacredeu")
	private Tercero cacredeu;
	
	private int yreldoco;
	private int nreldoco;
	
	@Column(length = 8)
	private String codfirma;
	
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
