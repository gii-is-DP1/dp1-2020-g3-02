package org.springframework.samples.petclinic.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.service.EntrenadorService;
import org.springframework.samples.petclinic.service.EstadisticoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cuerpotecnico")
public class CuerpoTecnicoController {
	private static final Log LOG = LogFactory.getLog(CuerpoTecnicoController.class);
	
	@Autowired
	private EntrenadorService entrenadorService;
	
	@Autowired
	private EstadisticoService estadisticoService;
	
	@GetMapping("/showcuerpotecnico")
	public ModelAndView listadoCuerpoTecnico() {
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_CUERPO_TECNICO);
		mav.addObject("entrenadores", entrenadorService.findAll());
		mav.addObject("estadisticos", estadisticoService.findAll());
		return mav;
	}
	
	@GetMapping("/navbar")
	public String navbar() {
		return ViewConstant.VIEW_NAVBAR;
	}

}
