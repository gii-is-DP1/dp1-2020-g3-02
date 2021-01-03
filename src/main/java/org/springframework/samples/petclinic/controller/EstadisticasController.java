package org.springframework.samples.petclinic.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/estadisticas")
public class EstadisticasController {
	
	
	@Autowired
	private PartidoService partidoService;
	
	private static final Log LOG = LogFactory.getLog(EstadisticasController.class);
	
	@GetMapping("/estadisticasPartidoForm")
	public ModelAndView formularioEstadisticasPartido(int partido_id) {
		
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_ESTADISTICAS_PARTIDO_FORM);
		mav.addObject("partido", partidoService.findById(partido_id));
		return mav;
	}
	

	
	@GetMapping("/navbar")
	public String navbar() {
		return ViewConstant.VIEW_NAVBAR;
	}

	
}
