package it.unirc.bd.dao.beans;

public class Corso {
	Integer IdCorso=null;
	Integer Giorni=null;
	Integer Ora =null;
	String Tipo=null;
	Integer Allenatore1 =null;
	Integer Allenatore2 =null;
public Corso(Integer idCorso, Integer giorni, Integer ora, String tipo, Integer allenatore1, Integer allenatore2) {
		super();
		IdCorso = idCorso;
		Giorni = giorni;
		Ora = ora;
		Tipo = tipo;
		Allenatore1 = allenatore1;
		Allenatore2 = allenatore2;
	}
public Corso() {
		super();
		// TODO Auto-generated constructor stub
	}
public Integer getIdCorso() {
	return IdCorso;
}
public void setIdCorso(Integer idCorso) {
	IdCorso = idCorso;
}
public Integer getGiorni() {
	return Giorni;
}
public void setGiorni(Integer giorni) {
	Giorni = giorni;
}
public Integer getOra() {
	return Ora;
}
public void setOra(Integer ora) {
	Ora = ora;
}
public String getTipo() {
	return Tipo;
}
public void setTipo(String tipo) {
	Tipo = tipo;
}
public Integer getAllenatore1() {
	return Allenatore1;
}
public void setAllenatore1(Integer allenatore1) {
	Allenatore1 = allenatore1;
}
public Integer getAllenatore2() {
	return Allenatore2;
}
public void setAllenatore2(Integer allenatore2) {
	Allenatore2 = allenatore2;
}
@Override
public String toString() {
	
	return "IdCorso: " +  IdCorso + " Tipo: " + Tipo +  " Ora: " + Ora   ;
}
}
