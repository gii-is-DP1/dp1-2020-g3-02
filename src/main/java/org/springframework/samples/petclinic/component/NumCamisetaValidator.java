package org.springframework.samples.petclinic.component;

import java.util.List;
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

		//Número de camiseta validación
		if (numCamiseta.getNumero()==null) {
			LOG.warn(ValidationConstant.NUM_CAMISETA_ERORR);
			errors.rejectValue("numero", "error",ValidationConstant.NUM_CAMISETA_ERORR);
		}else if(numCamiseta.getNumero()<1){
			LOG.warn(ValidationConstant.NUM_CAMISETA_MENOR_QUE_1);
			errors.rejectValue("numero", "error",ValidationConstant.NUM_CAMISETA_MENOR_QUE_1);
		}else if(numCamiseta.getNumero()>99) {
			LOG.warn(ValidationConstant.NUM_CAMISETA_MAYOR_QUE_99);
			errors.rejectValue("numero", "error",ValidationConstant.NUM_CAMISETA_MAYOR_QUE_99);
		}else{
			List<NumCamiseta> nums = numCamisetaService.findByEquipo(numCamiseta.getEquipo().getId());
			if(nums != null && nums.stream().map(x->x.getNumero()).collect(Collectors.toList()).contains(numCamiseta.getNumero())) {
				for(int i = 0; i< nums.size();i++) {
					if(nums.get(i).getNumero() == numCamiseta.getNumero() && nums.get(i).getId() != numCamiseta.getId()) {
						LOG.warn(ValidationConstant.NUM_CAMISETA_REPETIDO);
						errors.rejectValue("numero", "error",ValidationConstant.NUM_CAMISETA_REPETIDO);
					}
				}
			}
		}
		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}
}
