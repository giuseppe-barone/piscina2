package it.unirc.bd.dao.beans;

public class Dipendente {
	
	public int IdDipendente;
	public String Nome;
	public String Cognome;
	public String Cellulare;
	public String Sesso;
	public int TipologiaDipendente;
	public Dipendente() {
		super();
		// TODO Auto-generated constructor stub
	}
public Dipendente(int idDipendente, String nome, String cognome, String cellulare, String sesso,
			int tipologiaDipendente) {
		super();
		IdDipendente = idDipendente;
		Nome = nome;
		Cognome = cognome;
		Cellulare = cellulare;
		Sesso = sesso;
		TipologiaDipendente = tipologiaDipendente;
	}
public int getIdDipendente() {
	return IdDipendente;
}
public void setIdDipendente(int idDipendente) {
	IdDipendente = idDipendente;
}
public String getNome() {
	return Nome;
}
public void setNome(String nome) {
	Nome = nome;
}
public String getCognome() {
	return Cognome;
}
public void setCognome(String cognome) {
	Cognome = cognome;
}
public String getCellulare() {
	return Cellulare;
}
public void setCellulare(String cellulare) {
	Cellulare = cellulare;
}
public String getSesso() {
	return Sesso;
}
public void setSesso(String sesso) {
	Sesso = sesso;
}
public int getTipologiaDipendente() {
	return TipologiaDipendente;
}
public void setTipologiaDipendente(int tipologiaDipendente) {
	TipologiaDipendente = tipologiaDipendente;
}
@Override
public String toString() {
	return "Dipendente [IdDipendente=" + IdDipendente + ", Nome=" + Nome + ", Cognome=" + Cognome + ", Cellulare="
			+ Cellulare + ", Sesso=" + Sesso + ", TipologiaDipendente=" + TipologiaDipendente + "]";
}




}
