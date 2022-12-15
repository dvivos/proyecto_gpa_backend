package com.gpa.backendrest.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpa.backendrest.app.dao.IUsuarioDao;
import com.gpa.backendrest.app.entity.Usuario;
import com.gpa.backendrest.app.repository.UsuarioRepository;

@Service
public class IUsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired 
	private UsuarioRepository usuarioRepository;

	/**
	 * 
	 */
	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		return usuarioDao.save(usuario);

	}

	@Override
	@Transactional(readOnly = true)
	public Usuario checkUserLogin(Usuario usuario) {

		return (Usuario) usuarioDao.findByEmailAndPassword(usuario.getEmail(), usuario.getPassword());
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {

		return usuarioDao.findByIdSQL(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findUser(Usuario usuario) {

		return usuarioDao.findByEmail(usuario.getEmail());
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByCusuario(String cusuario) {

		return usuarioDao.findByCusuario(cusuario);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {

		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	@Transactional
	public void delete(Usuario usuario) {
		
		usuarioDao.delete(usuario);
		
	}
	
}
