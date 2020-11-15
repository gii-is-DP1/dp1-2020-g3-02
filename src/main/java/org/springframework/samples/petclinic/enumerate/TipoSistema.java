package org.springframework.samples.petclinic.enumerate;

public enum TipoSistema {
	D("COLOCADORGENERAL"), A("4-2") , B("5-1") , C("6-2");


	private String tipoSistema;

	TipoSistema(String string) {
		this.tipoSistema = string;
	}

	public String getTipoSistema() {
		return tipoSistema;
	}
}
