package org.springframework.samples.petclinic.controller;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Jugador;
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
@RequestMapping("/jugadores")
public class JugadorController {
		
	private static final Log LOG = LogFactory.getLog(JugadorController.class);
	
	private static final String VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM = "jugadorform";
	private static final String VIEW_JUGADOR = "listadojugadores";
	
	@Autowired
	private JugadorService jugadorService;
	
	@GetMapping("/showjugadores")
	public ModelAndView listadoJugadores(Model model) {
		
		ModelAndView mav = new ModelAndView(VIEW_JUGADOR);
		mav.addObject("jugadores", jugadorService.findAll());
		return mav;
	}
	
	
	@GetMapping("/jugadorform")
	public String redirectJugadorForm(@RequestParam(name="id",required=false) int id, Model model) {
		Optional<Jugador> jugador = Optional.of(new Jugador());
		if(id != 0) {
			jugador = jugadorService.findById(id);
		}
		model.addAttribute("jugador", jugador);
		return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
	}
	
	
	@PostMapping("/addjugador")
	public String addJugador(@ModelAttribute(name="jugador") Jugador jugador, Model model) {
		
		LOG.info("addjugador() -- PARAMETROS: "+ jugador.toString());
		
		if(null != jugadorService.saveJugador(jugador)) {
			model.addAttribute("result", 1);
			
		}else {
			model.addAttribute("result", 0); 
		}
		return "redirect:/jugadores/showjugadores";
	}
	
}
