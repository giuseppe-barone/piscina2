package it.unirc.bd.dao.beans;

import java.sql.Date;

public class Prenotazione {
	private int idPrenotazione;
	private int corsia;
	private Date data;
	private int idIscritto;
	private int ora;
	private int idDipendente;
	
	/*public Prenotazione() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Prenotazione(int idPrenotazione, int corsia, Date data, int idIscritto, String tipoPiscina, int ora,
			int giorno, int idDipendente) {
		super();
		this.idPrenotazione = idPrenotazione;
		this.corsia = corsia;
		this.data = data;
		this.idIscritto = idIscritto;
		TipoPiscina = tipoPiscina;
		this.ora = ora;
		this.idDipendente = idDipendente;
		}*/
	
	@Override
	public String toString() {
		return "Prenotazione [idPrenotazione=" + idPrenotazione + ", corsia=" + corsia + ", data=" + data
				+ ", idIscritto=" + idIscritto +  ", ora=" + ora + ", idDipendente="
				+ idDipendente + "]";
	}
	public Prenotazione(int idPrenotazione, int corsia, Date data, int idIscritto, int ora,
			int idDipendente) {
		super();
		this.idPrenotazione = idPrenotazione;
		this.corsia = corsia;
		this.data = data;
		this.idIscritto = idIscritto;
		this.ora = ora;
		this.idDipendente = idDipendente;
	}
	public Prenotazione() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public int getIdPrenotazione() {
		return idPrenotazione;
	}
	public void setIdPrenotazione(int idPrenotazione) {
		this.idPrenotazione = idPrenotazione;
	}
	public int getCorsia() {
		return corsia;
	}
	public void setCorsia(int corsia) {
		this.corsia = corsia;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getIdIscritto() {
		return idIscritto;
	}
	public void setIdIscritto(int idIscritto) {
		this.idIscritto = idIscritto;
	}
	
	public int getOra() {
		return ora;
	}
	public void setOra(int ora) {
		this.ora = ora;
	}
	public int getIdDipendente() {
		return idDipendente;
	}
	public void setIdDipendente(int idDipendente) {
		this.idDipendente = idDipendente;
	}
	
	

}
