package org.springframework.samples.petclinic.component;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Capitan;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.service.CapitanService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component

public class CapitanValidator implements Validator{


	@Autowired
	private CapitanService capitanService;

	@Override
	public void validate(Object target, Errors errors) {
		Capitan capitan = (Capitan) target;


		//Tiempos muertos
		if ( capitan.getNtiemposmuertos()<0) {
			errors.rejectValue("ntiemposmuertos", "Tiene que haber algún tiempo muerto","Tiene que haber algún tiempo muerto");
		}

		//Actitud Validation
		if ( capitan.getActitud().toString().length() > 255) {
			errors.rejectValue("actitud", "La actitud no debe tener más de 255 carácteres","La actitud no debe tener más de 255 carácteres");
		}



	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Jugador.class.isAssignableFrom(clazz);
	}


}
