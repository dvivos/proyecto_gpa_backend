package com.gpa.backendrest.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gpa.backendrest.app.constants.Constants;
import com.gpa.backendrest.app.entity.Entidad;
import com.gpa.backendrest.app.repository.EntidadRepository;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/protected")
public class EntidadController {
	
	@Autowired
	EntidadRepository entidadRepository;
	
	/**
	 * Recibe la petición y devuelve un listado de todas las entidades con sus atributos.
	 * 
	 */
	@GetMapping("/entidades")
	public ResponseEntity<List<Entidad>> listaEntidades(@RequestParam(required = false) String entidad) {
		try {
			List<Entidad> entidades = new ArrayList<Entidad>();

			entidadRepository.findAll().forEach(entidades::add);

			if (entidades.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(entidades, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/**
	 * 
	 * @param entidadIn
	 * @return
	 */
	@PostMapping("/entidad")
	public ResponseEntity<?> getEntidad(@RequestBody Entidad entidadIn) {

		Entidad entidad = null;
		
		Map<String, Object> response = new HashMap<>();

		try {
			
			entidad = entidadRepository.findByCentidad(entidadIn.getCentidad());
			
			
		} catch (DataAccessException e) {
			response.put("mensaje", Constants.ERROR_BD_GENERICO);
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (entidad == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Entidad>(entidad, HttpStatus.OK);
		}
	}
	
	

}
