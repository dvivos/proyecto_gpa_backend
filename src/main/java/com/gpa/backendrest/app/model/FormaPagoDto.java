package com.gpa.backendrest.app.model;

import java.util.Date;

public class FormaPagoDto {

	private Long id;

	private String tfpgoper;

	private String descripcion;

	private Date createAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTfpgoper() {
		return tfpgoper;
	}

	public void setTfpgoper(String tfpgoper) {
		this.tfpgoper = tfpgoper;
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
