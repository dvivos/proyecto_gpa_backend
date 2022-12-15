package com.gpa.backendrest.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "terceros")
public class Tercero implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	@NaturalId
	private Long cacredeu;

	@Column(length = 40)
	private String nombre;
	@Column(length = 40)
	private String apellido1;
	@Column(length = 40)
	private String apellido2;
	@Column(length = 30)
	private String cdocumento;
	@Column(length = 1)
	private String codigocif;
	@Column(length = 1)
	private String letradni;

	private int iadfisju;

	@Column(columnDefinition = "boolean default true")
	private Boolean embargado;

	private int baja;

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
