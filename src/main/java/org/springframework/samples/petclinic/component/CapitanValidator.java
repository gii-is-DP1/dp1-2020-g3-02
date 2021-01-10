package org.springframework.samples.petclinic.component;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.model.Capitan;
import org.springframework.samples.petclinic.service.CapitanService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class CapitanValidator implements Validator{

	private static final Log LOG = LogFactory.getLog(CapitanValidator.class);
	
	@Autowired
	private CapitanService capitanService;

	@Override
	public void validate(Object target, Errors errors) {
		Capitan capitan = (Capitan) target;


		//Tiempos muertos
		if ( capitan.getNtiemposmuertos()<0) {
			LOG.warn(ValidationConstant.CAMPO_NEGATIVO + ": número de tiempos muertos");
			errors.rejectValue("ntiemposmuertos", "error",ValidationConstant.CAMPO_NEGATIVO);
		}

		//Actitud Validation
		if (capitan.getActitud() == null || capitan.getActitud().toString().length() > 255) {
			LOG.warn(ValidationConstant.VALOR_ERROR_ENUM + ": actitud");
			errors.rejectValue("actitud", "error",ValidationConstant.VALOR_ERROR_ENUM);
		}



	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Capitan.class.isAssignableFrom(clazz);
	}


}
