package org.springframework.samples.petclinic.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.service.EntrenadorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/entrenadores")
public class EntrenadorController {
	
	private static final Log LOG = LogFactory.getLog(EntrenadorController.class);
	
	@Autowired
	private EntrenadorService entrenadorService;
	
	@GetMapping("/entrenadorform")
	public String redirectJugadorForm(@RequestParam(name="id",required=false) Integer id, Model model) {
		Entrenador entrenador = new Entrenador();
		if(id != 0) {
			entrenador = entrenadorService.findById(id).get();
		}
		model.addAttribute("entrenador", entrenador);
		return ViewConstant.VIEWS_ENTRENADOR_CREATE_OR_UPDATE_FORM;
	}

	
	@PostMapping("/addentrenador")
	public String addEntrenador(@ModelAttribute(name="entrenador") Entrenador entrenador, Model model, BindingResult result) {
		
		LOG.info("addentrenador() -- PARAMETROS: "+ entrenador);
		
		//ValidationUtils.invokeValidator(entrenadorValidator, jugador, result);
	
		if (result.hasErrors()) {
			model.addAttribute("entrenador", entrenador);
			return ViewConstant.VIEWS_ENTRENADOR_CREATE_OR_UPDATE_FORM;
		}else {
		
		
		if(null != entrenadorService.saveEntrenador(entrenador)) {
			model.addAttribute("result", 1);
			
		}else {
			model.addAttribute("result", 0); 
		}
		return "redirect:/home";
		}
	}

}
