package com.gpa.backendrest.app.model;

import java.util.Date;

public class TerceroDto {

	private Long id;
	private Long cacredeu;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String cdocumento;
	private String codigocif;
	private String letradni;

	private int iadfisju;

	private Boolean embargado;

	private int baja;

	private Date createAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCacredeu() {
		return cacredeu;
	}

	public void setCacredeu(Long cacredeu) {
		this.cacredeu = cacredeu;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getCdocumento() {
		return cdocumento;
	}

	public void setCdocumento(String cdocumento) {
		this.cdocumento = cdocumento;
	}

	public String getCodigocif() {
		return codigocif;
	}

	public void setCodigocif(String codigocif) {
		this.codigocif = codigocif;
	}

	public String getLetradni() {
		return letradni;
	}

	public void setLetradni(String letradni) {
		this.letradni = letradni;
	}

	public int getIadfisju() {
		return iadfisju;
	}

	public void setIadfisju(int iadfisju) {
		this.iadfisju = iadfisju;
	}

	public Boolean getEmbargado() {
		return embargado;
	}

	public void setEmbargado(Boolean embargado) {
		this.embargado = embargado;
	}

	public int getBaja() {
		return baja;
	}

	public void setBaja(int baja) {
		this.baja = baja;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

}
