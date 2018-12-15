package it.unirc.bd.dao.beans;

public class Partecipazione {
	int idEvento;
	int MatricolaFin;
	int Posizione;
	String Categoria;
	public Partecipazione(int idEvento, int matricolaFin, int posizione, String categoria) {
		super();
		this.idEvento = idEvento;
		MatricolaFin = matricolaFin;
		Posizione = posizione;
		Categoria = categoria;
	}
public Partecipazione() {
		super();
		// TODO Auto-generated constructor stub
	}

public int getIdEvento() {
	return idEvento;
}
public void setIdEvento(int idEvento) {
	this.idEvento = idEvento;
}
public int getMatricolaFin() {
	return MatricolaFin;
}
public void setMatricolaFin(int matricolaFin) {
	MatricolaFin = matricolaFin;
}
public int getPosizione() {
	return Posizione;
}
public void setPosizione(int posizione) {
	Posizione = posizione;
}
public String getCategoria() {
	return Categoria;
}
public void setCategoria(String categoria) {
	Categoria = categoria;
}
@Override
public String toString() {
	return "Partecipazione [idEvento=" + idEvento + ", MatricolaFin=" + MatricolaFin + ", Posizione=" + Posizione
			+ ", Categoria=" + Categoria + "]";
}

}
