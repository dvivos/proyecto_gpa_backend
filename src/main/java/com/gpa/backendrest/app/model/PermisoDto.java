package com.gpa.backendrest.app.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.gpa.backendrest.app.entity.Usuario;

public class PermisoDto {

	private Long id;

	private String cpermiso;

	private String descripcion;

	private Date createAt;

	private Set<Usuario> usuarios = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpermiso() {
		return cpermiso;
	}

	public void setCpermiso(String cpermiso) {
		this.cpermiso = cpermiso;
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

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
