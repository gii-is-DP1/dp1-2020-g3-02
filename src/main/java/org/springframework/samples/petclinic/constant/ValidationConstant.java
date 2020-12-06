package org.springframework.samples.petclinic.constant;

public class ValidationConstant {
	
	/** GENERALES */
	public static final String VALOR_OBLIGATORIO = "Este campo es obligatorio";
	
	/** USUARIO */
	public static final String FIRSTNAME_ERROR = "El nombre es requerido y debe tener más de tres letras";
	public static final String LASTNAME_ERROR = "El nombre es requerido y debe tener más de tres letras";
	
	/** FECHA */
	public static final String FECHA_FORMATO_ERRONEO = "La fecha debe tener el formato dd/MM/yyyy";
	public static final String FECHA_ANTERIOR_ERROR = "La fecha introducida es anterior a la actual";
	
	/** HORA */
	public static final String HORA_FORMATO_ERRONEO = "La hora debe tener el formato hh:mm, desde 00:00 hasta 23:59";

}
