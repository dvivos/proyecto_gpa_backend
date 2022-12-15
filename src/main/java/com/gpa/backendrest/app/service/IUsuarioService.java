package com.gpa.backendrest.app.service;

import java.util.List;

import com.gpa.backendrest.app.entity.Usuario;

public interface IUsuarioService {

	/**
	 * Metodo para guardar usuario cuando se pasa el user
	 * 
	 * @param user
	 * @return
	 */
	public Usuario save(Usuario usuario);

	/**
	 * Método que comprueba si el usuario está logueado
	 * 
	 * @param user
	 * @return
	 */
	public Usuario checkUserLogin(Usuario usuario);

	/**
	 * Metodo que devuelve un usuario al pasar el id
	 * 
	 * @param id
	 * @return
	 */
	public Usuario findById(Long id);

	/**
	 * Metodo que devuelve un usuario al pasar el user
	 * 
	 * @param user
	 * @return
	 */
	public Usuario findUser(Usuario usuario);

	/**
	 * Método que devuelve un usuario por su código de usuario (cusuario)
	 * 
	 * @param cusuario
	 * @return
	 */
	public Usuario findByCusuario(String cusuario);

	/**
	 * Método que obtiene un listado de los usuarios
	 * 
	 * @return
	 */
	public List<Usuario> findAll();
	
	public void delete(Usuario usuario);


}
