package com.gpa.backendrest.app.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.gpa.backendrest.app.constants.Constants;
import com.gpa.backendrest.app.entity.Utramit;
import com.gpa.backendrest.app.repository.UtramitRepository;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/protected")
public class UtramitController {
	
	@Autowired
	private UtramitRepository utramitRepository;
	
	/**
	 * Recibe la petición y devuelve un listado de todas las Unidades 
	 * Tramitadoras y sus atributos
	 * 
	 * @return Listado de Utramit
	 */
	@GetMapping("/unidades")
	public List<Utramit> listaUnidades(){
		return utramitRepository.findAll();
	}
	
	/**
	 * Recibe la petición y devuelve una Unidad Tramitadora con todos sus atributos.
	 * 
	 * @param cutramitIn
	 * @return Utramit
	 */
	@PostMapping("/unidad")
	public ResponseEntity<?> getUtramit(@RequestBody Utramit cutramitIn) {

		Utramit utramit = null;
		
		Map<String, Object> response = new HashMap<>();

		try {
			
			utramit = utramitRepository.findByCutramit(cutramitIn.getCutramit());
			
			
		} catch (DataAccessException e) {
			response.put("mensaje", Constants.ERROR_BD_GENERICO);
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (utramit == null) {
			response.put("mensaje", "La Unidad Tramitadora ".concat(cutramitIn.getCutramit()).concat(" no se ha encontrado"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Utramit>(utramit, HttpStatus.OK);
		}
	} 

}
