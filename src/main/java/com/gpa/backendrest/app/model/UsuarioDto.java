package com.gpa.backendrest.app.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.gpa.backendrest.app.entity.Entidad;
import com.gpa.backendrest.app.entity.Utramit;

public class UsuarioDto {

	private Long id;

	@NotNull
	private String cusuario;

	private String nombre;

	private String apellidos;

	private String email;

	private String password;

	public Utramit cutramit;

	public Entidad centidad;

	private Boolean activo;

	private Date createAt;

	private Set<PermisoDto> permisos = new HashSet<>();

	public Set<PermisoDto> getPermisos() {
		return permisos;
	}

	public void setPermisos(Set<PermisoDto> permisos) {
		this.permisos = permisos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCusuario() {
		return cusuario;
	}

	public void setCusuario(String cusuario) {
		this.cusuario = cusuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Utramit getCutramit() {
		return cutramit;
	}

	public void setCutramit(Utramit cutramit) {
		this.cutramit = cutramit;
	}

	public Entidad getCentidad() {
		return centidad;
	}

	public void setCentidad(Entidad centidad) {
		this.centidad = centidad;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

}
