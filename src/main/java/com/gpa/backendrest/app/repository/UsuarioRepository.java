package com.gpa.backendrest.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gpa.backendrest.app.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	/**
	 * Encontrar usuario por id
	 * 
	 * @param id
	 * @return
	 */
	@Query("select u from Usuario u where u.id=?1")
	public Usuario findByIdSQL(Long id);
	
	/**
	 * Encontrar usuario por código de usuario 
	 * @param cusuario
	 * @return
	 */
	@Query("select u from Usuario u where u.cusuario=:cusuario ")
	public Usuario findByCusuario(@Param("cusuario") String cusuario);
	
	/**
	 * Obtiene todos los usuarios
	 */
	public List<Usuario> findAll();
	
	@Query("select u from Usuario u where u.activo= 0 ")
	public List<Usuario> findNoActivos();
	

}
