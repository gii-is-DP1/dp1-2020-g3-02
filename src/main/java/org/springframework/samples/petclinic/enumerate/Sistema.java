package org.springframework.samples.petclinic.enumerate;

public enum Sistema {
	
	COLOCADOR_GENERAL, CUATRO_DOS, CINCO_UNO, SEIS_DOS;
	
	public static Sistema fromNombre(String nombre) {
		for(Sistema a : Sistema.values()) {
			if(a.name().equals(nombre)) {
				return a;
			}
		}
		return null;
	}

}
