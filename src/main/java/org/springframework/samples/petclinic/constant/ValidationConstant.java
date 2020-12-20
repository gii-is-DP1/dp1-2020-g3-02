package org.springframework.samples.petclinic.constant;

public class ValidationConstant {
	
	/** GENERALES */
	public static final String VALOR_OBLIGATORIO = "Este campo es obligatorio";
	
	/** USUARIO */
	public static final String FIRSTNAME_ERROR = "El nombre es requerido y no puede contener ningún caracter especial ni números";
	public static final String LASTNAME_ERROR = "El apellido es requerido y no puede contener ningún caracter especial ni números";
	public static final String EMAIL_FORMATO_ERROR = "El formato del email es incorrecto";
	public static final String EMAIL_YAEXISTE_ERROR = "El email ya está registrado";
	public static final String DIRECCION_ERROR = "La dirección es requerida y debe tener al menos 5 caracteres";
	public static final String LOCALIDAD_ERROR = "La localidad es requerida y debe tener al menos 5 caracteres";
	
	/** JUGADOR */
	public static final String ALTURA_ERROR = "La altura debe estar entre 100 y 300 centímetros";
	public static final String PESO_ERROR = "El peso debe estar entre 20 y 250 Kg";
	
	/** DNI */
	public static final String DNI_ERROR = "El DNI es requerido y debe tener 8 números y una letra";
	
	/** FECHA */
	public static final String FECHA_FORMATO_ERRONEO = "La fecha debe tener el formato dd/MM/yyyy";
	public static final String FECHA_FORMATO_ERRONEO_INVERSO = "La fecha debe tener el formato yyyy/MM/dd";
	public static final String FECHA_ANTERIOR_ERROR = "La fecha introducida es anterior a la actual";
	public static final String FECHA_POSTERIOR_ERROR = "La fecha introducida es posterior a la actual";
	
	/** HORA */
	public static final String HORA_FORMATO_ERRONEO = "La hora debe tener el formato hh:mm, desde 00:00 hasta 23:59";
	public static final String HORA_PARTIDO_COINCIDEN_ANTERIOR = "Este equipo ya tiene un partido en el tramo horario de 2 horas antes";
	public static final String HORA_PARTIDO_COINCIDEN_POSTERIOR = "Este equipo ya tiene un partido en el tramo horario de 2 horas después";
	
	/** EJERCICIOS INDIVIDUALES */
	public static final String EJERCICIOS_NOMBRE_DUPLICADO = "Este nombre de ejercicio ya existe";

}
