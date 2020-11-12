package org.springframework.samples.petclinic.controller;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/equipos")
public class EquipoController {
	
	private static final Log LOG = LogFactory.getLog(EquipoController.class);
	
	@Autowired
	private EquipoService equipoService;
	
	@Autowired
	private JugadorService jugadorService;
	
	@GetMapping("/showequipos")
	public ModelAndView listadoEquipos(Model model) {
		
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_EQUIPO);
		mav.addObject("equipos", equipoService.findAll());
		return mav;
	}
	
	@GetMapping("/equipoform")
	public String redirectEquipoForm(@RequestParam(name="id",required=false) int id, Model model) {
		Optional<Equipo> equipo = Optional.of(new Equipo());
		if(id != 0) {
			equipo = equipoService.findById(id);
		}
		model.addAttribute("equipo", equipo);
		return ViewConstant.VIEWS_EQUIPO_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping("/addequipo")
	public String addEquipo(@ModelAttribute(name="equipo") Equipo equipo, Model model) {
		
		LOG.info("addequipo() -- PARAMETROS: "+ equipo.toString());
		
		if(null != equipoService.saveTeam(equipo)) {
			model.addAttribute("result", 1);
			
		}else {
			model.addAttribute("result", 0); 
		}
		return "redirect:/equipos/showequipos";
		
	}

}
