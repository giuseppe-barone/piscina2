package it.unirc.bd.dao.beans;

public class Allenatore {
	public int idAllenatore;
	public int idDipendente;
	public String Qualifica;
public Allenatore() {
		super();
		// TODO Auto-generated constructor stub
	}
public Allenatore(int idAllenatore, String qualifica, int idDipendente) {
		super();
		this.idAllenatore = idAllenatore;
		this.idDipendente = idDipendente;
		Qualifica = qualifica;
	}
public int getIdAllenatore() {
	return idAllenatore;
}
public void setIdAllenatore(int idAllenatore) {
	this.idAllenatore = idAllenatore;
}
public int getIdDipendente() {
	return idDipendente;
}
public void setIdDipendente(int idDipendente) {
	this.idDipendente = idDipendente;
}
public String getQualifica() {
	return Qualifica;
}
public void setQualifica(String qualifica) {
	Qualifica = qualifica;
}
@Override
public String toString() {
	return "Allenatore [idAllenatore=" + idAllenatore + ", idDipendente=" + idDipendente + ", Qualifica=" + Qualifica
			+ "]";
}

}
