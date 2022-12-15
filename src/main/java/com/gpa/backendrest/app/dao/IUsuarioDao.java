package com.gpa.backendrest.app.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gpa.backendrest.app.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

	/**
	 * Obtener usuario pasado el email
	 * 
	 * @param email
	 * @return
	 */
	public Usuario findByEmail(String email);

	/**
	 * Obtener usuario pasado el email y password
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	public Usuario findByEmailAndPassword(String email, String password);

	/**
	 * Obtener usuario pasado el id
	 * 
	 * @param id
	 * @return
	 */
	@Query("select u from Usuario u where u.id=?1")
	public Usuario findByIdSQL(Long id);

	/**
	 * Obtener usuario pasando el código de usuario
	 * 
	 * @param cusuario
	 * @return
	 */
	public Usuario findByCusuario(String cusuario);
	
	public void delete(Usuario usuario);

}
