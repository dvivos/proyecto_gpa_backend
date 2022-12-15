package com.gpa.backendrest.app.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gpa.backendrest.app.entity.Entidad;
import com.gpa.backendrest.app.entity.Permiso;
import com.gpa.backendrest.app.entity.Usuario;
import com.gpa.backendrest.app.entity.Utramit;
import com.gpa.backendrest.app.model.PermisoDto;
import com.gpa.backendrest.app.model.UsuarioDto;
import com.gpa.backendrest.app.repository.EntidadRepository;
import com.gpa.backendrest.app.repository.UsuarioRepository;
import com.gpa.backendrest.app.repository.UtramitRepository;
import com.gpa.backendrest.app.service.IPermisoService;
import com.gpa.backendrest.app.service.IUsuarioService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/protected")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IPermisoService permisoService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EntidadRepository entidadRepository;
	
	@Autowired
	private UtramitRepository utramitRepository;
	
	/**
	 * Recibe como parámetro el código de usuario y devuelve todos los atributos del usuario.
	 * 
	 * @param usuarioIn Usuarion con el código de usuario informado.
	 * @return Usuario con todos sus atributos.
	 */
	@PostMapping("/usuario")
	public ResponseEntity<?> getUsuario(@RequestBody Usuario usuarioIn) {

		Usuario usuario = null;

		try {
			
			usuario = usuarioRepository.findByCusuario(usuarioIn.getCusuario());
			

		} catch (DataAccessException e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (usuario == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		}
	}
	
	/**
	 * Devuelve un listado de todos los usuarios con sus atributos.
	 * 
	 * @return listado de Usuarios con todos sus atributos.
	 */
	@GetMapping("/usuarios")
	public List<Usuario> listaUsuarios() {
		return usuarioRepository.findAll();
	}
	
	/**
	 * Devuelve un listado de los usuarios que no están activos
	 * 
	 * @return listado Usuarios
	 */
	@GetMapping("/inactivos")
	public List<Usuario> listaInactivos() {
		return usuarioRepository.findNoActivos();
	}
	
	
	
	
	/**
	 * Recibe como parámetro el usuario con sus atributos y sobreescribe 
	 * los datos del usuario guardado en la base de datos con los recibidos. 
	 *  
	 * @param usuario 
	 * @param result
	 * @return mensaje con la información de la operación.
	 */
	@PutMapping("/usuario/actualizar")
	public ResponseEntity<?> updateCusuario(@Valid @RequestBody UsuarioDto usuario, BindingResult result) {
	  
		//Usuario usuarioAct = null;
		int sqlErrorCode = 0;
		Usuario usuarioBd = null;
		Entidad entidad = new Entidad();
		Utramit utramit = new Utramit();
		//boolean activo = false;
		
		Map<String, Object> response = new HashMap<>();

		try {
			
			//usuarioAct = usuarioService.findByCusuario(usuario.getCusuario());
			
			usuarioBd = usuarioService.findById(usuario.getId());
			
			if (usuarioBd != null) {
				
				// Actuliazamos la entidad del usuario
				if (usuario.centidad != null) {
					entidad = entidadRepository.findByCentidad(usuario.getCentidad().getCentidad());
					//usuarioAct.setCentidad(entidad);
					usuarioBd.setCentidad(entidad);
				}
				// Actualizamos la UT del usuario
				if (usuario.cutramit != null) {
					utramit = utramitRepository.findByCutramit(usuario.getCutramit().getCutramit());
					//usuarioAct.setCutramit(utramit);
					usuarioBd.setCutramit(utramit);
				}
				// Actualizamos el código de usuario si no es igual que el usuario de la bd
				if (usuario.getCusuario() != null && !usuarioBd.getCusuario().equals(usuario.getCusuario())) {
					usuarioBd.setCusuario(usuario.getCusuario());
				}
				// Cambiamos el estado del usuario si no es igual que el usuario de la bd
				if (usuario.getActivo() != null && !usuarioBd.getActivo().equals(usuario.getActivo())) {
					usuarioBd.setActivo(usuario.getActivo());
				}
				// Creamos un Set (colección) para los permisos	
				Set<Permiso> permisos = new HashSet<>();
				// Añadimos a la colección los permisos 
				for(PermisoDto permiso : usuario.getPermisos()) {
					permisos.addAll(permisoService.findByCpermiso(permiso.getCpermiso()));
				}
				// Actualizamos los permisos
				usuarioBd.setPermisos(permisos);	
				try {
					// Guaramos el usuario
					usuarioService.save(usuarioBd);
				} catch (DataAccessException e) {
					response.put("mensaje", "Error al realizar la actualización del usuario");
					if( e.getRootCause() instanceof SQLException) {
				        SQLException sqlEx = (SQLException) e.getRootCause();
				        sqlErrorCode = sqlEx.getErrorCode();
				    }
					//response.put("msgError", e.getMostSpecificCause().getMessage());
					response.put("msgError", sqlErrorCode);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
				response.put("OK", "El usuario se ha actualizado.");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

			} else {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("msgError", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * 
	 * @param usuario
	 * @return
	 */
	@DeleteMapping("/usuario/baja")
	public ResponseEntity<?> delete(@RequestBody UsuarioDto usuario){
		
		Usuario usuarioBaja = null;
		
		Map<String, Object> respuesta = new HashMap<>();
		
		try {
			usuarioBaja = usuarioService.findById(usuario.getId());
		} catch (DataAccessException e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (usuarioBaja == null) {
			respuesta.put("error", "No se ha encontrado el usuario");
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
		} else {
			
			try {
				usuarioService.delete(usuarioBaja);
			} catch (DataAccessException e) {
				respuesta.put("Error", "Error al eliminar el usuario en la base de datos");
				respuesta.put("msgError",e.getMostSpecificCause().getMessage());
				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			respuesta.put("OK", "El usuario ha sido eliminado");
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		}
		
		
	}
	

}
