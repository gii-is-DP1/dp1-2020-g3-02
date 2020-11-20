package org.springframework.samples.petclinic.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.component.JugadorValidator;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.converter.JugadorConverter;
import org.springframework.samples.petclinic.converter.enumerate.EstadoConverter;
import org.springframework.samples.petclinic.converter.enumerate.PosicionConverter;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.ediciones.JugadorEdit;
import org.springframework.samples.petclinic.model.estadisticas.JugadorStats;
import org.springframework.samples.petclinic.service.AutorizacionService;
import org.springframework.samples.petclinic.service.EstadisticaPersonalPartidoService;
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
@RequestMapping("/jugadores")
public class JugadorController {
		
	private static final Log LOG = LogFactory.getLog(JugadorController.class);
	public static final String TEMPLATE_MODAL_GESTION_JUGADOR = "/jugadores/modalGestionJugador";
	
	
	@Autowired
	private JugadorValidator jugadorFormValidator;

	
	@Autowired
	private JugadorService jugadorService;
	
	@Autowired
	private AutorizacionService autorizacionService;
	
	@Autowired
	private EstadisticaPersonalPartidoService estadisService;
	
	@Autowired
	private EstadoConverter estadoConverter;
	
	@Autowired
	private PosicionConverter posicionConverter;
	
	@Autowired
	private JugadorConverter jugadorConverter;
	
	@Autowired
	private JugadorValidator jugadorValidator;
	
	
//	@InitBinder("jugador")
//	public void initJugadorBinder(WebDataBinder dataBinder) {
//	dataBinder.setValidator(new JugadorValidator());
//	}
	
	
	
	
	@GetMapping("/showjugadores")
	public ModelAndView listadoJugadores() {
		
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_JUGADOR);
		mav.addObject("jugadores", jugadorService.findAll());
		return mav;
	}
	
	@GetMapping("/showjugadoresaut")
	public ModelAndView listadoJugadoresAutorizacion() {
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_JUGADORES_AUTORIZACION);
		mav.addObject("jugadoresaut", jugadorService.findAll());
		return mav;
	}
	
	@RequestMapping(value = "findestadisticasjugador/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<JugadorStats> graficoEstadisticasJugador(@PathVariable("id") int id) {
		try {
			Optional<Jugador> jugadorO = jugadorService.findById(id);
			Jugador jugador = jugadorO.get();
			JugadorStats stats = jugadorConverter.convertJugadorToJugadorStats(jugador);
			return new ResponseEntity<JugadorStats>(stats, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<JugadorStats>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@RequestMapping(value = "findeditjugador/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<JugadorEdit> editarJugador(@PathVariable("id") int id) {
		try {
			Optional<Jugador> jugadorO = jugadorService.findById(id);
			Jugador jugador = jugadorO.get();
			JugadorEdit edit = jugadorConverter.convertJugadorToJugadorEdit(jugador);
			return new ResponseEntity<JugadorEdit>(edit, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<JugadorEdit>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@GetMapping("/navbar")
	public String navbar() {
		return ViewConstant.VIEW_NAVBAR;
	}
	
	@GetMapping("/jugadorform")
	public String redirectJugadorForm(@RequestParam(name="id",required=false) Integer id, Model model) {
		Jugador jugador = new Jugador();
		if(id != 0) {
			jugador = jugadorService.findById(id).get();
		}
		model.addAttribute("jugador", jugador);
		return ViewConstant.VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
	}
	
	
	@PostMapping("/addjugador")
	public String addJugador(@ModelAttribute(name="jugador") Jugador jugador, Model model, BindingResult result) {
		
		LOG.info("addjugador() -- PARAMETROS: "+ jugador);
		
		ValidationUtils.invokeValidator(jugadorValidator, jugador, result);
	
		if (result.hasErrors()) {
			model.addAttribute("jugador", jugador);
			return ViewConstant.VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
		}else {
		
		
		if(null != jugadorService.saveJugador(jugador)) {
			model.addAttribute("result", 1);
			
		}else {
			model.addAttribute("result", 0); 
		}
		return "redirect:/jugadores/showjugadores";
	}
	
	}
	
	@PostMapping("/updatejugador")
	public String updateJugador(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Optional<Jugador> jugadorO = jugadorService.findById(id);
		Jugador jugador = jugadorO.get();
		
		jugador.setFirstName(request.getParameter("firstName").trim());
		jugador.setLastName(request.getParameter("lastName").trim());
		jugador.setAltura(Integer.parseInt(request.getParameter("altura")));
		jugador.setPeso(Integer.parseInt(request.getParameter("peso")));
		jugador.setEstadoActual(estadoConverter.convertToEntityAttribute(request.getParameter("estadoActual")));
		jugador.setPosicionPrincipal(posicionConverter.convertToEntityAttribute(request.getParameter("posicionPrincipal")));
		jugador.setPosicionSecundaria(posicionConverter.convertToEntityAttribute(request.getParameter("posicionSecundaria")));
		
		Jugador player = jugadorService.updateJugador(jugador);
		
		return "redirect:/jugadores/showjugadores";	
	}
	
}