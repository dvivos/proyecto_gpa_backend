package com.gpa.backendrest.app.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gpa.backendrest.app.entity.Documento;
import com.gpa.backendrest.app.service.IDocumentoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/protected")
public class DocumentoController {
	
	/**
	 * Inyectamos el servicio de documentos
	 */
	@Autowired
	private IDocumentoService documentoService;
	
	
	/**
	 * Método para obtener listado de documentos
	 * @return List<Documentos>
	 */
	@GetMapping("/documentos")
	public List<Documento> listaDocumentos(){
		return documentoService.findAll();
	}
	
	/**
	 * Método para obtener un documento
	 * 
	 * @param documentoIn
	 * @return Documento
	 */
	@PostMapping("/documento")
	public ResponseEntity<?> getDocumento(@RequestBody Documento documentoIn){
		
		Documento documento = null;
		
		try {
			documento = documentoService.documentoByCentidadAndYejecontAndYdocontaAndNdoconta(documentoIn.getCentidad().getCentidad(), 
																							      documentoIn.getYejecont(), 
																							      documentoIn.getYdoconta(), 
																							      documentoIn.getNdoconta());
			
		} catch (DataAccessException e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (documento == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Documento>(documento, HttpStatus.OK);
		}
	}
	
	/**
	 * Recibe un Documento con los atributos necesarios y elimina el Documento
	 * @param documentoIn
	 * @return Resultado de la operación.
	 */
	@DeleteMapping("/documento/baja")
	public ResponseEntity<?> delete(@RequestBody Documento documentoIn){
		
		int sqlErrorCode = 0;
		Documento documento = null;
		
		Map<String, Object> respuesta = new HashMap<>();
		
		try {

			documento = documentoService.findById(documentoIn.getId());
			
		} catch (DataAccessException e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (documento == null) {
			respuesta.put("error", "No se ha encontrado el documento");
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
		} else {
			
			try {
				documentoService.delete(documento);
			} catch (DataAccessException e) {
				respuesta.put("Error", "Error al eliminar el documento en la base de datos");
				
				if( e.getRootCause() instanceof SQLException) {
			        SQLException sqlEx = (SQLException) e.getRootCause();
			        sqlErrorCode = sqlEx.getErrorCode();
			    }
				respuesta.put("msgError", sqlErrorCode);
				
				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			respuesta.put("OK", "El documento contable ha sido eliminado");
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		}
		
		
	}

}
