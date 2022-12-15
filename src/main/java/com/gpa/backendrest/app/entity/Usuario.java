package com.gpa.backendrest.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 8, unique = true)
	private String cusuario;

	@NotEmpty(message = "Debe informar el nombre")
	@Size(min = 3, max = 20)
	@Column(nullable = false)
	private String nombre;

	@NotEmpty(message = "Debe informar los apellidos")
	@Column(length = 40, nullable = false)
	@Size(min = 2, max = 40)
	private String apellidos;

	@NotEmpty(message = "Debe informar el correo")
	@Email(message = "El formato del correo no es correcto")
	@Column(nullable = false, unique = true)
	private String email;

	@NotEmpty(message = "Debe informar la contraseña")
	@Column(nullable = false)
	private String password;

	@Column(columnDefinition = "boolean default true")
	private Boolean activo;

	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "permisos_usuarios", joinColumns = { @JoinColumn(name = "usuario_id") }, inverseJoinColumns = {
			@JoinColumn(name = "permiso_id") })
	private Set<Permiso> permisos = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "centidad", referencedColumnName = "centidad")
	private Entidad centidad;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cutramit", referencedColumnName = "cutramit")
	private Utramit cutramit;

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
