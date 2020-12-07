package org.springframework.samples.petclinic.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.converter.RealizaEjercicioConverter;
import org.springframework.samples.petclinic.model.RealizaEjercicio;
import org.springframework.samples.petclinic.model.auxiliares.DataTableResponse;
import org.springframework.samples.petclinic.model.auxiliares.RealizaEjercicioDTO;
import org.springframework.samples.petclinic.service.EjercicioIndividualService;
import org.springframework.samples.petclinic.service.RealizaEjercicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ejercicios")
public class EjercicioIndividualController {
	
	private static final Log LOG = LogFactory.getLog(EjercicioIndividualController.class);
	
	@Autowired
	private RealizaEjercicioService realizaEjercicioService;
	
	@Autowired
	private RealizaEjercicioConverter realizaEjercicioConverter;
	
	@Autowired
	private EjercicioIndividualService ejercicioIndividual;
	
	@GetMapping("/navbar")
	public String navbar() {
		return ViewConstant.VIEW_NAVBAR;
	}
	
	@GetMapping("/showejercicios")
	public String inicio(Model model) {
		return ViewConstant.VIEW_EJERCICIOS;
	}
	
	@RequestMapping(value = "/tablaRealizados", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataTableResponse<RealizaEjercicioDTO>> tablaRealizados(){
		try {
			List<RealizaEjercicio> realizaEjercicios = realizaEjercicioService.findAll();
			DataTableResponse<RealizaEjercicioDTO> data = new DataTableResponse<RealizaEjercicioDTO>(realizaEjercicioConverter.converListEntityToListDTO(realizaEjercicios));
			return new ResponseEntity<DataTableResponse<RealizaEjercicioDTO>>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DataTableResponse<RealizaEjercicioDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

}
