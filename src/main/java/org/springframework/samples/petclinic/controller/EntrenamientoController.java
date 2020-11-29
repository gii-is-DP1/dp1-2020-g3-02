package org.springframework.samples.petclinic.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.controller.form.JugadorForm;
import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.service.EntrenamientoService;
import org.springframework.samples.petclinic.service.EstadisticaPersonalEntrenamientoService;
import org.springframework.samples.petclinic.service.EstadisticaPersonalPartidoService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;




@Controller
@RequestMapping("/entrenamientos")
public class EntrenamientoController {
		
	private static final Log LOG = LogFactory.getLog(EntrenamientoController.class);
	
	@Autowired
	private EstadisticaPersonalEntrenamientoService estadisService;
	
	@Autowired
	private EntrenamientoService entrenamientoService;
	
	@GetMapping("/showentrenamientos")
	public ModelAndView listadoJugadores() {
		
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_ENTRENAMIENTOS);
		mav.addObject("entrenamientos", entrenamientoService.findAll());
		return mav;
	}
	 
	@GetMapping("/showentrenamiento")
	public Entrenamiento entrenamiento(int id) {
		Optional<Entrenamiento> entrenamiento = entrenamientoService.findById(id);
		return entrenamiento.get();
	}
	
	@GetMapping("/showestadisiticasJugadores")
	public ModelAndView vistaEstadísticas(int id) {
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_ESTADISTICAS_JUGADOR_POR_ENTRENAMIENTO);
		mav.addObject("estadisticas", estadisService.findByJugador(id));
		
		return mav;
	}
	
	@GetMapping("/navbar")
	public String navbar() {
		return ViewConstant.VIEW_NAVBAR;
	}
	
	
	@GetMapping("/entrenamientoform")
	public String redirectEntrenamientoForm(@RequestParam(name="id",required=false) Integer id, Model model) {
		Optional<Entrenamiento> entrenamiento = Optional.of(new Entrenamiento());
		if(id != 0) {
			entrenamiento = entrenamientoService.findById(id);
		}
		model.addAttribute("entrenamiento", entrenamiento);
		return ViewConstant.VIEWS_ENTRENAMIENTO_CREATE_OR_UPDATE_FORM;
	}
	
	
	@PostMapping("/addentrenamiento")
	public String addJugador(@ModelAttribute(name="Entrenamiento") Entrenamiento entrenamiento, Model model, final BindingResult result) {
		
		//LOG.info("addpartido() -- PARAMETROS: "+ form.getJugador().toString());
		
//		ValidationUtils.invokeValidator(jugadorFormValidator, form, result);
//		
//		if (result.hasErrors()) {
//			model.addAttribute("formJugador", form);
//			return ViewConstant.VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
//		}else {
		
		Entrenamiento training = entrenamientoService.saveEntrenamiento(entrenamiento);
		return "redirect:/entrenamientos/showEntrenamientos";
	//}
	}
	
}