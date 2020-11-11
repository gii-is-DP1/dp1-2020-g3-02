package org.springframework.samples.petclinic.enumerate;

public enum TipoEjercicio {
	ATAQUE,RECEPCION,COLOCACION,SAQUE,BLOQUEO,DEFENSA,ATAQUERAPIDO;
	
	public static TipoEjercicio fromNombre(String nombre) {
		for(TipoEjercicio a : TipoEjercicio.values()) {
			if(a.name().equals(nombre)) {
				return a;
			}
		}
		return null;
	}
}
