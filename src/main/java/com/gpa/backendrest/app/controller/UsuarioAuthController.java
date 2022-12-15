package com.gpa.backendrest.app.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gpa.backendrest.app.entity.Entidad;
import com.gpa.backendrest.app.entity.Usuario;
import com.gpa.backendrest.app.entity.Utramit;
import com.gpa.backendrest.app.model.JwtUser;
import com.gpa.backendrest.app.repository.UsuarioRepository;
import com.gpa.backendrest.app.security.JwtGenerator;
import com.gpa.backendrest.app.service.IEntidadService;
import com.gpa.backendrest.app.service.IUsuarioService;
import com.gpa.backendrest.app.service.IUtramitService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/auth")
public class UsuarioAuthController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private IEntidadService entidadService;
	
	@Autowired
	private IUtramitService utramitService;

	@Autowired
	private JwtGenerator jwtGenerator;

	/**
	 * Método para el registro del usuario.
	 * 
	 * @param usuario datos del nuevo usuario
	 * @param result
	 * @return respuesta informando del alta del usuario o de los errores que se han producido.
	 */
	@PostMapping("/registro") // con @RequestBody recoge los datos en formato JSON
	public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario, BindingResult result) {

		// Variables del método
		Usuario usuarioNuevo = null;
		Entidad entidad = null;
		Utramit utramit = null;
		int sqlErrorCode = 0;
		int aleatorio;
        String prefijo = "USER";
        String codigoUsuario = null;
        Usuario codigoExistente = null;
        
        // Map para almacenar la respuesta
		Map<String, Object> response = new HashMap<>();
		// Comprobamos si los campos cumple con los requisitos de los atributos de la entidad
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("msgError", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			
			// Generamos un código de usuario. Usamos un bucle do-while ya que si el usuario generado
			// existe, volvemos a generar un nuevo código.
			do {
				
				aleatorio = (char) (Math.random()*9999);
		        String ale = String.valueOf(aleatorio);
		        codigoUsuario = prefijo + ale;
		        
		        // Comprobamos que no exista este código de usuario generado.
		        codigoExistente = usuarioRepository.findByCusuario(codigoUsuario);
			} while (codigoExistente != null);
			
			// Establecemos el código de usuario
			usuario.setCusuario(codigoUsuario);
			// Buscamos la entidad y unidad tramitadora que viene informada
			entidad = entidadService.findByCentidad(usuario.getCentidad().getCentidad());
			utramit = utramitService.findByCutramit(usuario.getCutramit().getCutramit());
			// Establecemos la entidad y UT
			usuario.setCentidad(entidad);
			usuario.setCutramit(utramit);
			
			// Guardamos el nuevo usuario
			try {
				usuarioNuevo = usuarioService.save(usuario);
			} catch (DataAccessException e) {
				response.put("mensaje", "Error al realizar la inserción del nuevo usuario la base de datos");
				if( e.getRootCause() instanceof SQLException) {
			        SQLException sqlEx = (SQLException) e.getRootCause();
			        sqlErrorCode = sqlEx.getErrorCode();
			    }
				response.put("msgError", sqlErrorCode);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			response.put("mensaje", "El usuario ha sido creado");
			response.put("usuario", usuarioNuevo);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
			
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la inserción con relaciones del usuario en la base de datos");
			response.put("msgError", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/**
	 * Método para el login del usuario. Valida el correo electrónico y 
	 * contraseña recibidos contra los datos almacenados. 
	 * 
	 * @param usuario datos del usuario necesarios para el login
	 * @param result
	 * @return Usuario con todos sus atributos y un token generado para el usuario.
	 */
	@PostMapping(value = "/login")
	public ResponseEntity<?> loginUser(@Valid @RequestBody Usuario usuario, BindingResult result) {

		
		Usuario usuarioLogin = null;
		Map<String, Object> response = new HashMap<>();

		try {
			usuarioLogin = usuarioService.checkUserLogin(usuario);

			if (usuarioLogin != null) {

				JwtUser jwtUser = new JwtUser();
				jwtUser.setId(usuarioLogin.getId());
				jwtUser.setUserName(usuarioLogin.getEmail());

				response.put("usuario", usuarioLogin);
				response.put("jwtToken", jwtGenerator.generate(jwtUser));
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
	
	
	

}
