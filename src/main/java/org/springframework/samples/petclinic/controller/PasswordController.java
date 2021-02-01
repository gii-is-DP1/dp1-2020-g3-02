package org.springframework.samples.petclinic.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.component.UserValidator;
import org.springframework.samples.petclinic.converter.UserConverter;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.auxiliares.UserEdit;
import org.springframework.samples.petclinic.service.impl.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users")
public class PasswordController {
	private static final Log LOG = LogFactory.getLog(JugadorController.class);
	public static final String TEMPLATE_MODAL_PASSWORD = "/users/modalPassword";

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private UserConverter userConverter;

	@PostMapping("/postpassword")
	public ResponseEntity<List<ObjectError>> postPassword(@ModelAttribute(name = "userEdit") UserEdit userEdit,
			BindingResult bindResult, Model model, HttpServletRequest request) {

		String username = request.getUserPrincipal().getName();
		try {
			LOG.info("Estamos editando el usuario con el username: " + username);
			userEdit.setUsername(username);
			userEdit.setOldPassword(request.getParameter("oldPassword"));
			userEdit.setNewPassword(request.getParameter("newPassword"));
			userEdit.setConfirmPassword(request.getParameter("confirmPassword"));
			LOG.info("Se procede a comprobar la nueva contraseña del user: " + userEdit);
			ValidationUtils.invokeValidator(userValidator, userEdit, bindResult);
			if (bindResult.hasErrors()) {
				LOG.warn("Se han encontrado " + bindResult.getErrorCount() + " errores de validación");
				return new ResponseEntity<List<ObjectError>>(bindResult.getAllErrors(), HttpStatus.BAD_REQUEST);
			}
			LOG.info("Se procede a guardar la nueva contraseña del user: " + username);
			User user= userService.findByUsername(username);
			user.setPassword(userEdit.getNewPassword());
			userService.saveUser(user);
			LOG.info("Se ha guardado la contraseña con éxito: " + user);
			return new ResponseEntity<List<ObjectError>>(HttpStatus.CREATED);
		} catch (Exception e) {
			LOG.error("No se ha podido guardar la contraseña");
			return new ResponseEntity<List<ObjectError>>(HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "findeditpassword/{username}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserEdit> findUser(@PathVariable("username") String username) {
		try {
			LOG.info("Buscamos el user con username: " + username);
			User user = userService.findByUsername(username);
			UserEdit userEdit = userConverter.convertUserToUserEdit(user);
			return new ResponseEntity<UserEdit>(userEdit, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Excepción encontrando el vehículo para editar");
			return new ResponseEntity<UserEdit>(HttpStatus.BAD_REQUEST);
		}
	}
}
