package org.springframework.samples.petclinic.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.model.Material;
import org.springframework.samples.petclinic.service.MaterialService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class MaterialValidator implements Validator{

	private static final Log LOG = LogFactory.getLog(MaterialValidator.class);
	
	@Autowired
	private MaterialService materialIndividualService;

	@Override
	public void validate(Object target, Errors errors) {
		Material material = (Material) target;

		//Stock validation
		if (material.getStock()<0) {
			LOG.warn(ValidationConstant.STOCK_NEGATIVO + ": número de stock");
			errors.rejectValue("stock", "error",ValidationConstant.STOCK_NEGATIVO);
		} 

		//Estado Validation
		if ( material.getEstado()== null || material.getEstado().toString().length()>255 ) {
			LOG.warn(ValidationConstant.VALOR_ERROR_ENUM + ": estado");
			errors.rejectValue("estado", "error",ValidationConstant.VALOR_ERROR_ENUM);
		}
		//tipo validation
		if ( material.getTipo()==null || material.getTipo().toString().length()>255) {
			LOG.warn(ValidationConstant.VALOR_ERROR_ENUM + ": tipo");
			errors.rejectValue("tipo", "error",ValidationConstant.VALOR_ERROR_ENUM);
		}
	}
	@Override
	public boolean supports(Class<?> clazz) {
		return Material.class.isAssignableFrom(clazz);
	}

}
