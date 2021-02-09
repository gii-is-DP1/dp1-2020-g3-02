package org.springframework.samples.petclinic.constant;

public class ValidationConstant {
	
	/** GENERALES */
	public static final String VALOR_OBLIGATORIO = "Este campo es obligatorio";
	public static final String CAMPO_NEGATIVO = "El valor no puede ser un número negativo";
	public static final String VALOR_ERROR_ENUM = "El valor no es compatible en este campo";
	public static final String VALOR_NUMERICO_ENTERO_ERROR = "El valor introducido debe ser un número entero";
	/** USUARIO */
	public static final String USERNAME_ERROR = "El username es requerido y debe tener más de 5 carácteres";
	public static final String USERNAME_YAEXISTE_ERROR = "El nombre de usuario ya está registrado";
	public static final String PASSWORD_ERROR = "La contraseña es requerida y debe tener 8 o más caracteres, un número, mayúsculas y minúsculas";
	public static final String PASSWORD_ERROR2 = "La nueva contraseña debe ser distinta a la ya establecida";
	public static final String PASSWORD_ERROR3 = "La contraseña debe ser igual a la actual";
	public static final String PASSWORD_ERROR4 = "La contraseña de confirmacón debe ser igual a la nueva contraseña";
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
	
	/** EQUIPO */
	public static final String CATEGORIA_ERROR = "La categoría es requerida y debe tener un mínimo de 3 caracteres";
	public static final String CATEGORIA_EXISTE_ERROR = "La categorría que intenta introducir ya existe";
	public static final String LIGA_ERROR = "La liga es requerida y debe tener un mínimo de 3 caracteres";
	
	/** EQUIPO */
	public static final String NUM_CAMISETA_ERORR = "El número de camiseta es requerido y no puede ser 0 ni mayor que 99";
	public static final String NUM_CAMISETA_REPETIDO = "El número de camiseta debe ser diferente de los del resto de jugadores del equipo";
	public static final String NUM_CAMISETA_MENOR_QUE_1 = "El número de camiseta no puede ser 0";
	public static final String NUM_CAMISETA_MAYOR_QUE_99 = "El número de camiseta no puede ser mayor que 99";
	
	/** FECHA */
	public static final String FECHA_FORMATO_ERRONEO = "La fecha debe tener el formato dd/MM/yyyy";
	public static final String FECHA_FORMATO_ERRONEO_INVERSO = "La fecha debe tener el formato yyyy/MM/dd";
	public static final String FECHA_ANTERIOR_ERROR = "La fecha introducida es anterior a la actual";
	public static final String FECHA_POSTERIOR_ERROR = "La fecha introducida es posterior a la actual";
	
	/** HORA */
	public static final String HORA_FORMATO_ERRONEO = "La hora debe tener el formato hh:mm, desde 00:00 hasta 23:59";
	public static final String HORA_PARTIDO_COINCIDEN_ANTERIOR = "Este equipo ya tiene un partido en el tramo horario de 2 horas antes";
	public static final String HORA_PARTIDO_COINCIDEN_POSTERIOR = "Este equipo ya tiene un partido en el tramo horario de 2 horas después";
	public static final String HORA_ENTRENAMIENTO_COINCIDEN_ANTERIOR = "Este equipo ya tiene un entrenamiento en el tramo horario de 1 horas antes";
	public static final String HORA_ENTRENAMIENTO_COINCIDEN_POSTERIOR = "Este equipo ya tiene un entrenamiento en el tramo horario de 1 horas después";
	
	/** EJERCICIOS INDIVIDUALES */
	public static final String EJERCICIOS_NOMBRE_ERROR = "El nombre es requerido y debe tener como mucho 300 caracteres";
	public static final String EJERCICIOS_NOMBRE_DUPLICADO = "Este nombre de ejercicio ya existe";
	public static final String EJERCICIOS_NOMBRE_MUY_EXTENSO = "EL nombre es demasiado extenso (máximo 300 caracteres)";
	public static final String EJERCICIOS_DESCRIPCION_ERROR = "La descripcion es requerida y debe tener como mucho 10.000 caracteres";
	public static final String EJERCICIOS_DESCRIPCION_MUY_EXTENSA = "La descripción es demasiado extensa (máximo 10.000 caracteres)";
	
	/** SUSTITUCIONES */
	public static final String MINUTO_SUSTITUCION_ERROR = "El minuto de sustitución es requerido y ha de ser mayor que 0";
	
	/** PERSONALES */
	public static final String PROPIETARIO_ERROR = "El propietario debe ser un jugador o un entrenador";
	
	/** PRUEBAS DE CONDICIÓN FÍSICA */
	public static final String TIPOPRUEBA_ERROR = "El tipo de prueba no debe tener más de 30 carácteres";
	public static final String DATO_PRUEBA_ERROR = "El dato de la prueba física es requerido y debe ser un número";
	public static final String PULSACIONES_ERROR = "Las pulsaciones deben ser un número entero entre 30 y 200";
	public static final String FLEXIBILIDAD_ERROR ="El dato de flexibilidad debe ser un número decimal y ser menor a 50";
	public static final String SALTOVERTICAL_ERROR ="El salto vertical no puede ser menor que la altura del jugador";

	/** MATERIALES */
	public static final String STOCK_NEGATIVO ="No puedes pasar más cantidad de materiales de la que tienes";
	public static final String STOCK_ZERO ="El valor que estás pasando es 0, cambia el valor que quieres pasar";

}
