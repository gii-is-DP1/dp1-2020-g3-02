package org.springframework.samples.petclinic.controller;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.component.JugadorValidator;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.controller.form.JugadorForm;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.service.JugadorService;
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
@RequestMapping("/jugadores")
public class JugadorController {
		
	private static final Log LOG = LogFactory.getLog(JugadorController.class);
	
	@Autowired
	private JugadorValidator jugadorFormValidator;
	
	@Autowired
	private JugadorService jugadorService;
	
	@GetMapping("/showjugadores")
	public ModelAndView listadoJugadores() {
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_JUGADOR);
		return mav;
	}
	
	@GetMapping("/jugadorform")
	public String redirectJugadorForm(@RequestParam(name="id",required=false) Integer id, Model model) {
		Optional<Jugador> jugador = Optional.of(new Jugador());
		if(id != 0) {
			jugador = jugadorService.findById(id);
		}
		model.addAttribute("jugador", jugador);
		return ViewConstant.VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
	}
	
	
	@PostMapping("/addjugador")
	public String addJugador(@ModelAttribute(name="formJugador") JugadorForm form, Model model, final BindingResult result) {
		
		LOG.info("addjugador() -- PARAMETROS: "+ form.getJugador().toString());
		
//		ValidationUtils.invokeValidator(jugadorFormValidator, form, result);
//		
//		if (result.hasErrors()) {
//			model.addAttribute("formJugador", form);
//			return ViewConstant.VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
//		}else {
//		
		
		if(null != jugadorService.saveJugador(form.getJugador())) {
			model.addAttribute("result", 1);
			
		}else {
			model.addAttribute("result", 0); 
		}
		return "redirect:/jugadores/showjugadores";
	//}
	
}
	
}