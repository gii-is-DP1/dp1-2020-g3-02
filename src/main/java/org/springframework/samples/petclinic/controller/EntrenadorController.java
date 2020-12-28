package org.springframework.samples.petclinic.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.component.EntrenadorValidator;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.model.Entrenador;
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
	
	@Autowired
	private EntrenadorValidator entrenadorValidator;
	
	@GetMapping("/entrenadorform")
	public String redirectJugadorForm(@RequestParam(name="id",required=true) Integer id, Model model) {
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
		
		ValidationUtils.invokeValidator(entrenadorValidator, entrenador, result);
	
		if (result.hasErrors()) {
			model.addAttribute("entrenador", entrenador);
			return ViewConstant.VIEWS_ENTRENADOR_CREATE_OR_UPDATE_FORM;
		}
		try {
			Entrenador coach = entrenadorService.save(entrenador);
			LOG.info("Éxito al guardar el entrenador:" + coach);
		} catch (Exception e) {
			LOG.error("Error al guardar el entrenador: "+entrenador);
		}
		
		return "redirect:/home";
	}

}
