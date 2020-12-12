package org.springframework.samples.petclinic.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.component.EquipoValidator;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.ediciones.PartidoEdit;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@Autowired
	private EquipoValidator equipoValidator;
	
	@GetMapping("/showequipos")
	public ModelAndView listadoEquipos() {
		
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_EQUIPO);
		mav.addObject("equipos", equipoService.findAll());
		return mav;
	}
	
	@GetMapping("/showjugadores")
	public ModelAndView listadoJugadores(@RequestParam(name="id",required=true) int id, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String username = "";
									
		if(principal == null) {
			username = "";
//			ModelAndView mav = new ModelAndView("/login");
//			return mav;
		}else {
			username =  principal.getName();
		}
		
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_JUGADOR);
		mav.addObject("username", username);
		mav.addObject("jugadores", jugadorService.findByEquipo(id));
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
	
	@RequestMapping(value = "getallteams", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> findEquipos() {
		try {
			List<String> equipos = equipoService.findCategoria();
			return new ResponseEntity<List<String>>(equipos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@PostMapping("/addequipo")
	public String addEquipo(@Valid @ModelAttribute(name="equipo") Equipo equipo, BindingResult bindResult, Model model) {
		
		LOG.info("addequipo() -- PARAMETROS: "+ equipo.toString());
		
		ValidationUtils.invokeValidator(equipoValidator, equipo, bindResult);
		
		if (bindResult.hasErrors()) {
			model.addAttribute("equipo", equipo);
			return ViewConstant.VIEWS_EQUIPO_CREATE_OR_UPDATE_FORM;
		}
		Equipo equipoSave = equipoService.save(equipo);
		return "redirect:/equipos/showequipos";
		
	}
	
	@GetMapping("/navbar")
	public String navbar() {
		return ViewConstant.VIEW_NAVBAR;
	}
	
	@GetMapping("/eliminarequipo")
	public ModelAndView eliminarEquipo(@RequestParam(name="id",required=true) int id) {
		
		equipoService.deleteByIdSiExiste(id);
		
		return listadoEquipos();
		
	}

}
