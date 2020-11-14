package org.springframework.samples.petclinic.controller;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.component.JugadorValidator;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.controller.form.JugadorForm;
import org.springframework.samples.petclinic.converter.JugadorConverter;
import org.springframework.samples.petclinic.model.EstadisticaPersonalPartido;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.estadisticas.JugadorStats;
import org.springframework.samples.petclinic.service.EstadisticaPersonalPartidoService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;




@Controller
@RequestMapping("/jugadores")
public class JugadorController {
		
	private static final Log LOG = LogFactory.getLog(JugadorController.class);
	
	@Autowired
	private JugadorValidator jugadorFormValidator;
	
	@Autowired
	private JugadorService jugadorService;
	
	@Autowired
	private EstadisticaPersonalPartidoService estadisService;
	
	@Autowired
	private JugadorConverter jugadorConverter;
	
	@GetMapping("/showjugadores")
	public ModelAndView listadoJugadores() {
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_JUGADOR);
		mav.addObject("jugadores", jugadorService.findAll());
		return mav;
	}
	
	@GetMapping("/showestadisiticasJugadores")
	public ModelAndView vistaEstadísticas(int id) {
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_ESTADISTICAS_JUGADOR_POR_PARTIDO);
		mav.addObject("estadisticas", estadisService.findByJugador(id));
		return mav;
	}
	
	@RequestMapping(value = "findjugador/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<JugadorStats> graficoEstadisticasJugador(@PathVariable("id") int id) {
		try {
			Optional<Jugador> jugadorO = jugadorService.findById(id);
			Jugador jugador = jugadorO.get();
			JugadorStats stats = jugadorConverter.convertToEstadisticas(jugador);
			return new ResponseEntity<JugadorStats>(stats, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<JugadorStats>(HttpStatus.BAD_REQUEST);
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
	public String addJugador(@ModelAttribute(name="formJugador") Jugador jugador, Model model, final BindingResult result) {
		
		LOG.info("addjugador() -- PARAMETROS: "+ jugador);
		
//		ValidationUtils.invokeValidator(jugadorFormValidator, form, result);
//		
//		if (result.hasErrors()) {
//			model.addAttribute("formJugador", form);
//			return ViewConstant.VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
//		}else {
//		
		
		if(null != jugadorService.saveJugador(jugador)) {
			model.addAttribute("result", 1);
			
		}else {
			model.addAttribute("result", 0); 
		}
		return "redirect:/jugadores/showjugadores";
	//}
	
}
	
}