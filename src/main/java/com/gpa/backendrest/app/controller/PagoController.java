package com.gpa.backendrest.app.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gpa.backendrest.app.entity.Documento;
import com.gpa.backendrest.app.entity.Pago;
import com.gpa.backendrest.app.service.IDocumentoService;
import com.gpa.backendrest.app.service.IPagoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/protected")
public class PagoController {
	
	
	@Autowired
	private IPagoService pagoService;
	
	@Autowired
	private IDocumentoService documentoService;
	
	
	/**
	 * Recibe la petición y devuelve un listado de todos los pagos con sus atributos.
	 * 
	 * @return lista de Pagos.
	 */
	@GetMapping("/pagos")
	public List<Pago> listaPagos() {
		return pagoService.findAll();
	}
	
	/**
	 * Recibe la petición con los datos de consulta del pago (índice único formado 
	 * por la entidad, año y número de pago) y devuelve un pago con sus atributos.
	 * 
	 * @param pagoIn
	 * @return Pago
	 */
	@PostMapping("/pago")
	public ResponseEntity<?> getPago(@RequestBody Pago pagoIn) {

		Pago pago = null;

		try {
			pago = pagoService.pagosByCentidadAndYmandpagAndNmandpag(pagoIn.getCentidad().getCentidad(),
																	    pagoIn.getYmandpag(), 
																	    pagoIn.getNmandpag());

		} catch (DataAccessException e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (pago == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Pago>(pago, HttpStatus.OK);
		}
	}
	
	/**
	 * 
	 * @param pagoIn
	 * @return
	 */
	@PutMapping("/pago/actualizar")
	public ResponseEntity<?> updatePago(@Valid @RequestBody Pago pagoIn){
		
		int sqlErrorCode = 0;
		String key;
		String valor;
		
		Pago pagoAct = null;
		Documento docBd = null;
		
		Map<String, Object> respuesta = new HashMap<>();
		
		try {
			
			pagoAct = pagoService.findById(pagoIn.getId());
			docBd = documentoService.findById(pagoAct.getDocumento().getId());
			
			key = "error";
			
			if (pagoAct.getYrelorpg() > 0 || pagoAct.getNrelorpg() > 0) {
				valor = "El MP forma parte de una relación";
			} else if (pagoAct.getFanulacion() != null) {
				valor = "El MP está pendiente de anulación";
			} else if (pagoAct.getFordenpago() != null || pagoAct.getFaprobpago() != null) {
				valor = "El MP está ordenado al pago o tiene pago aprobado";
			} else if (pagoAct.getDocumento() == null) {
				valor = "No se encuentra el documento contable asociado al pago";
			} else if (pagoAct.getDocumento().getNoperdia() > 0) {
				valor = "El documento contable asociado al pago está contabilizado";
			} else if (pagoAct.getDocumento().getFacuerop() == null) {
				valor = "El documento contable asociado no tiene fecha de aprobación";
			} else {
				
				
				pagoAct.setDocumento(null);
				pagoAct.setFaprobacion(null);
								
				docBd.setCodfirma(null);
				docBd.setFacuerop(null);
				
				pagoService.save(pagoAct);
				documentoService.save(docBd);
				
				key = "OK";
				valor = "El pago ha sido actualizado";
				
			}
			
			respuesta.put(key, valor);
			
			if (key == "error") {
				
				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.LOCKED);
			} else {
				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
				
			}
			
		} catch (DataAccessException e) {
			respuesta.put("Error", "Error al actualizar pago en la base de datos");
			
			if( e.getRootCause() instanceof SQLException) {
		        SQLException sqlEx = (SQLException) e.getRootCause();
		        sqlErrorCode = sqlEx.getErrorCode();
		    }
			respuesta.put("msgError", sqlErrorCode);
			
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
