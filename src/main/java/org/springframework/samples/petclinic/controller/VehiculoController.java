package org.springframework.samples.petclinic.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.component.PersonalesValidator;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.model.Autorizacion;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Personales;
import org.springframework.samples.petclinic.service.PersonalesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/personales")
public class VehiculoController {
	@Autowired
	private PersonalesValidator personalValidator;
	
	@Autowired
	private PersonalesService personalService;
	
	private static final Log LOG = LogFactory.getLog(VehiculoController.class);
	
	@PostMapping("/addvehiculo")
	public String addVehiculo(@Valid @ModelAttribute(name="personal") Personales personal, BindingResult bindResult, Model model) {
		
		LOG.info("addvehiculo() -- PARAMETROS: "+ personal.toString());
		
		ValidationUtils.invokeValidator(personalValidator, personal, bindResult);
		
		if (bindResult.hasErrors()) {
			model.addAttribute("personal", personal);
			return ViewConstant.VIEWS_VEHICULO_CREATE_OR_UPDATE_FORM;
		}
		Personales personalSave = personalService.save(personal);
		return "redirect:/personales/showvehiculos";
		
	}
	@GetMapping("/showvehiculos")
	public ModelAndView listadoVehiculos() {
		
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_VEHICULO);
		mav.addObject("personales", personalService.findAll());
		return mav;
	}
	
	@GetMapping("/vehiculoform")
	public String redirectVehiculoForm(@RequestParam(name="id",required=false) int id, Model model) {
		Optional<Personales> personal = Optional.of(new Personales());
		if(id != 0) {
			personal = personalService.findById(id);
		}
		model.addAttribute("personal", personal);
		return ViewConstant.VIEWS_VEHICULO_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping("/removeVehiculo")
	public ModelAndView removeVehiculo(@PathVariable("id") int id, Model model) {
		personalService.deleteByIdSiExiste(id);
		
		return listadoVehiculos();

		
	}

	
	@GetMapping("/navbar")
	public String navbar() {
		return ViewConstant.VIEW_NAVBAR;
	}

	
}
