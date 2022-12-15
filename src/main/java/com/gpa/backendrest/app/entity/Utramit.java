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
@Table(name = "utramit")
public class Utramit implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 6, unique = true, nullable = false)
	@NaturalId
	private String cutramit;

	@Column(name = "dutramit", length = 120, nullable = false)
	private String descorta;

	@Column(name = "xutramit", length = 120, nullable = false)
	private String descripcion;

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
