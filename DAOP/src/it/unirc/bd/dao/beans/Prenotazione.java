package it.unirc.bd.dao.beans;

import java.sql.Date;

public class Prenotazione {
	private int idPrenotazione;
	private int corsia;
	private Date data;
	private int idIscritto;
	private String TipoPiscina;
	private int ora;
	private int giorno;
	private int idDipendente;
	public Prenotazione() {
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
		this.giorno = giorno;
		this.idDipendente = idDipendente;
	}
	@Override
	public String toString() {
		return "Prenotazione [idPrenotazione=" + idPrenotazione + ", corsia=" + corsia + ", data=" + data
				+ ", idIscritto=" + idIscritto + ", TipoPiscina=" + TipoPiscina + ", ora=" + ora + ", giorno=" + giorno
				+ ", idDipendente=" + idDipendente + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((TipoPiscina == null) ? 0 : TipoPiscina.hashCode());
		result = prime * result + corsia;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + giorno;
		result = prime * result + idDipendente;
		result = prime * result + idIscritto;
		result = prime * result + idPrenotazione;
		result = prime * result + ora;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prenotazione other = (Prenotazione) obj;
		if (TipoPiscina == null) {
			if (other.TipoPiscina != null)
				return false;
		} else if (!TipoPiscina.equals(other.TipoPiscina))
			return false;
		if (corsia != other.corsia)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (giorno != other.giorno)
			return false;
		if (idDipendente != other.idDipendente)
			return false;
		if (idIscritto != other.idIscritto)
			return false;
		if (idPrenotazione != other.idPrenotazione)
			return false;
		if (ora != other.ora)
			return false;
		return true;
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
	public String getTipoPiscina() {
		return TipoPiscina;
	}
	public void setTipoPiscina(String tipoPiscina) {
		TipoPiscina = tipoPiscina;
	}
	public int getOra() {
		return ora;
	}
	public void setOra(int ora) {
		this.ora = ora;
	}
	public int getGiorno() {
		return giorno;
	}
	public void setGiorno(int giorno) {
		this.giorno = giorno;
	}
	public int getIdDipendente() {
		return idDipendente;
	}
	public void setIdDipendente(int idDipendente) {
		this.idDipendente = idDipendente;
	}
	
	

}
