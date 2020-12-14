package org.springframework.samples.petclinic.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.component.PruebaCondicionFisicaValidator;
import org.springframework.samples.petclinic.converter.DataPruebaConverter;
import org.springframework.samples.petclinic.converter.PruebaConverter;
import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;
import org.springframework.samples.petclinic.enumerate.TipoPrueba;
import org.springframework.samples.petclinic.model.Autorizacion;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.PruebaCondicionFisica;
import org.springframework.samples.petclinic.model.auxiliares.DataPruebaCondicion;
import org.springframework.samples.petclinic.model.auxiliares.PruebasSinJugador;
import org.springframework.samples.petclinic.model.ediciones.JugadorEdit;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.PruebaCondicionFisicaService;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/pruebas")
public class PruebaCondicionFisicaController {
	
	private static final Log LOG = LogFactory.getLog(PruebaCondicionFisicaController.class);
	
	@Autowired
	private PruebaCondicionFisicaValidator pruebaValidator;
	
	@Autowired
	private PruebaCondicionFisicaService pruebaService;
	
	@Autowired
	private JugadorService jugadorService;
	
	@Autowired
	private DataPruebaConverter dataPruebaConverter;
	
	@Autowired
	private PruebaConverter pruebaConverter;
	
	@RequestMapping(value = "findPruebasCondicionFisicaJugador/{id}/{tipo}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataPruebaCondicion> findPruebasCondicionFisicaJugador(@PathVariable("id") int id,@PathVariable("tipo") String tipo) {
		try {
			Optional<Jugador> jugador = jugadorService.findById(id);
			List<PruebaCondicionFisica> pruebas = pruebaService.findByJugadorAndTipoPrueba(jugador.get(), TipoPrueba.fromNombre(tipo));
			List<PruebasSinJugador> pruebasSinJugador = new ArrayList<PruebasSinJugador>();
			for (int i = 0; i<pruebas.size();i++) {
				pruebasSinJugador.add(pruebaConverter.convertPruebaToPruebaSinJugador(pruebas.get(i)));
			}
			DataPruebaCondicion data = dataPruebaConverter.convertPruebaToDataPrueba(pruebasSinJugador);
			return new ResponseEntity<DataPruebaCondicion>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DataPruebaCondicion>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	
	@RequestMapping(value = "/addprueba/{id}/{tipoPrueba}/{dato}", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ObjectError>> addPrueba(@PathVariable("id") int id,@ModelAttribute(name="prueba") PruebasSinJugador prueba_, BindingResult result , @PathVariable("tipoPrueba") TipoPrueba tipoPrueba, @PathVariable("dato") String dato) {
		try {
			
		LOG.info(dato);
		PruebaCondicionFisica prueba= new PruebaCondicionFisica(); 
		Optional<Jugador> jug = jugadorService.findById(id);
		Jugador jugador= jug.get();
		prueba.setFecha(LocalDate.now());
		prueba.setJugador(jugador);
		prueba.setTipoPrueba(tipoPrueba);
		if(!dato.isEmpty()) {
			prueba.setDato(Double.parseDouble(dato));
		}else {
			prueba.setDato(null);
		}
		
		ValidationUtils.invokeValidator(pruebaValidator, prueba, result);
		
		if (result.hasErrors()) {
			ResponseEntity<List<ObjectError>> re = new ResponseEntity<List<ObjectError>>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
			return re;
		}else {
			PruebaCondicionFisica pruebaAñadida = pruebaService.save(prueba);
			return new ResponseEntity(HttpStatus.OK);
		}
		

		}catch (Exception e) {
		// TODO: handle exception
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value = "/eliminarprueba/{id}", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity eliminarAutorizacion(@PathVariable("id") int id) {
		try {
		pruebaService.deleteById(id);
		
			return new ResponseEntity(HttpStatus.OK);
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value = "/findPruebasCondicionFisicaById/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<PruebasSinJugador> editarPrueba(@PathVariable("id") int id) {
		try {
			
			PruebaCondicionFisica prueba = pruebaService.findById(id).get();
			PruebasSinJugador pruebaSinJugador = pruebaConverter.convertPruebaToPruebaSinJugador(prueba);
			return new ResponseEntity<PruebasSinJugador>(pruebaSinJugador, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<PruebasSinJugador>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@RequestMapping(value ="/updateprueba", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ObjectError>> updateJugador(HttpServletRequest request, @ModelAttribute(name="prueba") PruebasSinJugador prueba, BindingResult result) {
		try {
			
			int id = Integer.parseInt(request.getParameter("id"));
			PruebaCondicionFisica pruebaO = pruebaService.findById(id).get();
			
			
			if(!request.getParameter("fecha").isEmpty()) {
				pruebaO.setFecha(LocalDate.parse(request.getParameter("fecha"), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			} else {
				pruebaO.setFecha(null);
			}
			if(!request.getParameter("dato").isEmpty()) {
				pruebaO.setDato(Double.parseDouble(request.getParameter("dato")));
			} else {
				pruebaO.setDato(null);
			}
			
			ValidationUtils.invokeValidator(pruebaValidator, pruebaO, result);
			
			
			PruebasSinJugador pruebaSinJugador = pruebaConverter.convertPruebaToPruebaSinJugador(pruebaO);
			
			if (result.hasErrors()) {
				ResponseEntity<List<ObjectError>> re = new ResponseEntity<List<ObjectError>>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
				return re;
			}else {
				PruebaCondicionFisica pruebaF = pruebaService.save(pruebaO);
				return new ResponseEntity<List<ObjectError>>(HttpStatus.CREATED);
			}
			
		} catch(Exception e) {
			return new ResponseEntity<List<ObjectError>>(HttpStatus.BAD_REQUEST);
		}
		
	}

}
