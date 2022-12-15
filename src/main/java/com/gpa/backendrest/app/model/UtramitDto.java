package com.gpa.backendrest.app.model;

import java.util.Date;

public class UtramitDto {

	private Long id;

	private String cutramit;

	private String descorta;

	private String descripcion;

	private Date createAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCutramit() {
		return cutramit;
	}

	public void setCutramit(String cutramit) {
		this.cutramit = cutramit;
	}

	public String getDescorta() {
		return descorta;
	}

	public void setDescorta(String descorta) {
		this.descorta = descorta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

}
