package org.springframework.samples.petclinic.enumerate;

public enum TipoResponsable {
	MADRE,PADRE,TUTORLEGAL;
	
	public static TipoResponsable fromNombre(String nombre) {
		for(TipoResponsable a : TipoResponsable.values()) {
			if(a.name().equals(nombre)) {
				return a;
			}
		}
		return null;
	}
}
