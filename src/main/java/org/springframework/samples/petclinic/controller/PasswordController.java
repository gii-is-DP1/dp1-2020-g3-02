package org.springframework.samples.petclinic.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.component.UserValidator;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.impl.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class PasswordController {
	private static final Log LOG = LogFactory.getLog(JugadorController.class);
	public static final String TEMPLATE_MODAL_PASSWORD = "/users/modalPassword";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidator userValidator;
	
	@PostMapping("/postpassword")
	public ResponseEntity<List<ObjectError>> postPassword(@ModelAttribute(name="user") User user, BindingResult bindResult,
			Model model, HttpServletRequest request) {
			
		
			String username = request.getUserPrincipal().getName();
			try {
					int id = Integer.parseInt(request.getParameter("id"));
					LOG.info("Estamos editando el usuario con el id: " + id);
					user = userService.findByUsername(username);
					user.setPassword(request.getParameter("newPassword"));
				
				ValidationUtils.invokeValidator(userValidator, user, bindResult);
				if (bindResult.hasErrors()) {
					LOG.warn("Se han encontrado " + bindResult.getErrorCount() + " errores de validación");
					return new ResponseEntity<List<ObjectError>>(bindResult.getAllErrors(), HttpStatus.BAD_REQUEST);
				}
				userService.saveUser(user);
				LOG.info("Se ha guardado el vehículo con éxito: " + user);
				return new ResponseEntity<List<ObjectError>>(HttpStatus.CREATED);
			} catch (Exception e) {
				LOG.error("No se ha podido guardar el vehículo");
				return new ResponseEntity<List<ObjectError>>(HttpStatus.BAD_REQUEST);
			}
		
		
	}
	
	/*@RequestMapping(value = "findedituser/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonalEdit> findPersonal(@PathVariable("id") int id) {
		try {
			LOG.info("Buscamos el vehículo con el id: " + id);
			personalO = personalService.findById(id);
			Personales personal = personalO.get();
			//PersonalEdit edit = personalConverter.convertPersonalToPersonalEdit(personal);
			return new ResponseEntity<PersonalEdit>(edit, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Excepción encontrando el vehículo para editar");
			return new ResponseEntity<PersonalEdit>(HttpStatus.BAD_REQUEST);
		}	
	}*/
}
