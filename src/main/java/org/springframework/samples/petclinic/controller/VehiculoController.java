package org.springframework.samples.petclinic.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.component.PersonalesValidator;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.model.Personales;
import org.springframework.samples.petclinic.service.PersonalesService;
import org.springframework.samples.petclinic.service.ViajeService;
import org.springframework.samples.petclinic.service.impl.AuthoritiesService;
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
	private ViajeService viajeService;
	
	@Autowired
	private PersonalesService personalService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	private static final Log LOG = LogFactory.getLog(VehiculoController.class);
	
	@GetMapping("/showvehiculos")
	public ModelAndView listadoVehiculos() {
		
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_VEHICULO);
		mav.addObject("personales", personalService.findAll());
		return mav;
	}
	
	@GetMapping("/vehiculoform")
	public String redirectVehiculoForm(@RequestParam(name="id",required=true) int id, Model model,HttpServletRequest request) {
		Personales personal= new Personales();
		String username = request.getUserPrincipal().getName();
		boolean autoridad = authoritiesService.hasAuthority("jugador", username);
		
		if(autoridad == false) {
			return "redirect:/login";
		}else { 
		if(id != 0) {
			personal = personalService.findById(id).get();
		}}
		model.addAttribute("personal", personal);
		return ViewConstant.VIEWS_VEHICULO_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping("/addvehiculo")
	public String addVehiculo(@ModelAttribute(name="personal") Personales personal, BindingResult bindResult,
			Model model, HttpServletRequest request) {
		LOG.info("addvehiculo() -- PARAMETROS: "+ personal);
		
			ValidationUtils.invokeValidator(personalValidator, personal, bindResult);
			
			
			if (bindResult.hasErrors()) {
				model.addAttribute("personal", personal);
				return ViewConstant.VIEWS_VEHICULO_CREATE_OR_UPDATE_FORM;
			}
			try {
				Personales personalSave = personalService.save(personal);
				LOG.info("Se ha guardado el vehículo con éxito: " + personalSave);
			} catch (Exception e) {
				LOG.error("No se ha podido guardar el vehículo");
			}
		
		return "redirect:/personales/showvehiculos";
		
	}

	/*@RequestMapping(value = "eliminarVehiculo/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<List<ObjectError>> eliminarVehiculo(@PathVariable("id") Integer id) {
		try {
			LOG.info("Se procede a borrar el vehiculo con id: " + id);
			
			personalService.deleteById(id);
			
			return new ResponseEntity<List<ObjectError>>(HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error al eliminar el vehiculo");
			return new ResponseEntity<List<ObjectError>>(HttpStatus.BAD_REQUEST);
		}
		
	}*/
	
	@PostMapping("/eliminarVehiculo/{id}")
    public String eliminarVehiculo(@PathVariable Integer id){
		viajeService.deleteAll(viajeService.findByPersonal(personalService.findById(id).get()));
		personalService.deleteById(id);
		return "redirect:/personales/showvehiculos";
    }
	
	@GetMapping("/navbar")
	public String navbar() {
		return ViewConstant.VIEW_NAVBAR;
	}

	
}
