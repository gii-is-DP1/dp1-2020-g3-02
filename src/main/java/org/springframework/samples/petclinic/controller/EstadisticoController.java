package org.springframework.samples.petclinic.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.component.EstadisticoValidator;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.model.Estadistico;
import org.springframework.samples.petclinic.service.EstadisticoService;
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
@RequestMapping("/estadisticos")
public class EstadisticoController {

private static final Log LOG = LogFactory.getLog(EstadisticoController.class);
	
	@Autowired
	private EstadisticoService estadisticoService;
	
	@Autowired
	private EstadisticoValidator estadisticoValidator;
	
	@GetMapping("/estadisticoform")
	public String redirectJugadorForm(@RequestParam(name="id",required=false) Integer id, Model model) {
		Estadistico estadistico = new Estadistico();
		if(id != 0) {
			estadistico = estadisticoService.findById(id).get();
		}
		model.addAttribute("estadistico", estadistico);
		return ViewConstant.VIEWS_ESTADISTICO_CREATE_OR_UPDATE_FORM;
	}

	
	@PostMapping("/addestadistico")
	public String addEntrenador(@ModelAttribute(name="estadistico") Estadistico estadistico, Model model, BindingResult result) {
		
		LOG.info("addestadistico() -- PARAMETROS: "+ estadistico);
		
		ValidationUtils.invokeValidator(estadisticoValidator, estadistico, result);
	
		if (result.hasErrors()) {
			LOG.warn("Se han obtenido " + result.getErrorCount() + " errores de validación");
			model.addAttribute("estadistico", estadistico);
			return ViewConstant.VIEWS_ESTADISTICO_CREATE_OR_UPDATE_FORM;
		}else {
		
			try {
				LOG.info("Se procede a guardar el estadístico");
				Estadistico estadisticoSave = estadisticoService.save(estadistico);
				LOG.info("Se ha guardado el estadístico con éxito: " + estadisticoSave);
				model.addAttribute("result", 1);
			} catch (Exception e) {
				LOG.error("No se ha podido guardar el estadístico");
				model.addAttribute("result", 0); 
			}
			return "redirect:/home";
		}
	}

}
