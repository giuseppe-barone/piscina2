package it.unirc.bd.dao.beans;

import java.sql.Date;

public class Infortunio {
	private int idInfortunio;
	private Date data;
	private int GiorniSosta;
	private int Gravita;
	private int MatricolaFIN;
	public Infortunio() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Infortunio(int idInfortunio, Date data, int giorniSosta, int gravita, int matricolaFIN) {
		super();
		this.idInfortunio = idInfortunio;
		this.data = data;
		GiorniSosta = giorniSosta;
		Gravita = gravita;
		MatricolaFIN = matricolaFIN;
	}
	@Override
	public String toString() {
		return "Infortunio [idInfortunio=" + idInfortunio + ", data=" + data + ", GiorniSosta=" + GiorniSosta
				+ ", Gravita=" + Gravita + ", MatricolaFIN=" + MatricolaFIN + "]"; 
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + GiorniSosta;
		result = prime * result + Gravita;
		result = prime * result + MatricolaFIN;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + idInfortunio;
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
		Infortunio other = (Infortunio) obj;
		if (GiorniSosta != other.GiorniSosta)
			return false;
		if (Gravita != other.Gravita)
			return false;
		if (MatricolaFIN != other.MatricolaFIN)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (idInfortunio != other.idInfortunio)
			return false;
		return true;
	}
	public int getIdInfortunio() {
		return idInfortunio;
	}
	public void setIdInfortunio(int idInfortunio) {
		this.idInfortunio = idInfortunio;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getGiorniSosta() {
		return GiorniSosta;
	}
	public void setGiorniSosta(int giorniSosta) {
		GiorniSosta = giorniSosta;
	}
	public int getGravita() {
		return Gravita;
	}
	public void setGravita(int gravita) {
		Gravita = gravita;
	}
	public int getMatricolaFIN() {
		return MatricolaFIN;
	}
	public void setMatricolaFIN(int matricolaFIN) {
		MatricolaFIN = matricolaFIN;
	}
	

	

}
