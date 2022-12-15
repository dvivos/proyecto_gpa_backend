package com.gpa.backendrest.app.model;

import java.util.Date;

public class EntidadDto {

	private Long id;

	private String centidad;

	private String xentidad;

	private Date createAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCentidad() {
		return centidad;
	}

	public void setCentidad(String centidad) {
		this.centidad = centidad;
	}

	public String getXentidad() {
		return xentidad;
	}

	public void setXentidad(String xentidad) {
		this.xentidad = xentidad;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public EntidadDto() {
		// TODO Auto-generated constructor stub
	}

	public EntidadDto(Long id, String centidad, String xentidad, Date createAt) {
		super();
		this.id = id;
		this.centidad = centidad;
		this.xentidad = xentidad;
		this.createAt = createAt;
	}
	
	

}
