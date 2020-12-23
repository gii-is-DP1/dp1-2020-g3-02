package org.springframework.samples.petclinic.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EquipoValidator implements Validator{
	
	private static final Log LOG = LogFactory.getLog(EquipoValidator.class);

	@Autowired
	private EquipoService equipoService;
	
	@Override
	public void validate(Object target, Errors errors) {
		Equipo equipo = (Equipo) target;
		
				//Categoria validation
				if ( StringUtils.isEmpty(equipo.getCategoria()) || equipo.getCategoria().length() < 3) {
					LOG.warn(ValidationConstant.CATEGORIA_ERROR);
					errors.rejectValue("categoria", "error",ValidationConstant.CATEGORIA_ERROR);
				}
				//liga validation
				if ( StringUtils.isEmpty(equipo.getLiga()) || equipo.getLiga().length() < 3) {
					LOG.warn(ValidationConstant.LIGA_ERROR);
					errors.rejectValue("liga", "error",ValidationConstant.LIGA_ERROR);
				}
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Equipo.class.isAssignableFrom(clazz);
	}
}
