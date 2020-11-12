package org.springframework.samples.petclinic.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	
	private static final String VIEWS_EQUIPO_CREATE_OR_UPDATE_FORM = "EquipoForm";
	private static final String VIEW_EQUIPO = "listadoEquipos";
	
	@Autowired
	private EquipoService equipoService;
	
	@GetMapping("/showequipos")
	public ModelAndView listadoEquipos() {
		
		ModelAndView mav = new ModelAndView(VIEW_EQUIPO);
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
		return VIEWS_EQUIPO_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping("/addequipo")
	public String addEquipo(@Valid @ModelAttribute(name="equipo") Equipo equipo, BindingResult bindResult, Model model) {
		
		LOG.info("addequipo() -- PARAMETROS: "+ equipo.toString());
		
		Equipo equipoSave = equipoService.saveTeam(equipo);
		
		LOG.info(equipoSave.getPosicionLiga());
		
		if(null != equipoSave) {
			model.addAttribute("result", 1);
			
		}else {
			model.addAttribute("result", 0); 
		}
		return "redirect:/equipos/showequipos";
		
	}
	
	@GetMapping("/eliminarequipo")
	public ModelAndView eliminarEquipo(@RequestParam(name="id",required=true) int id) {
		
		equipoService.deleteTeam(id);
		
		return listadoEquipos();
		
	}

}
