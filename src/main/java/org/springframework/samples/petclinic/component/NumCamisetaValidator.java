package org.springframework.samples.petclinic.component;

import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.model.NumCamiseta;
import org.springframework.samples.petclinic.service.NumCamisetaService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class NumCamisetaValidator implements Validator{
	
private static final Log LOG = LogFactory.getLog(CapitanValidator.class);
	
	@Autowired
	private NumCamisetaService numCamisetaService;

	@Override
	public void validate(Object target, Errors errors) {
		NumCamiseta numCamiseta = (NumCamiseta) target;


		//Numero de camiseta repetido en un equipo
		if(numCamisetaService.findByEquipo(numCamiseta.getEquipo().getId()).stream().map(x->x.getNumero()).collect(Collectors.toList()).contains(numCamiseta.getNumero())){
			LOG.warn(ValidationConstant.NUM_CAMISETA_REPETIDA);
			errors.rejectValue("numero", "error",ValidationConstant.NUM_CAMISETA_REPETIDA);
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}
}
