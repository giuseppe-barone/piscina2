package it.unirc.bd.dao.beans;

import java.sql.Date;

public class Iscritto {
	private int IdIscritto;
	private String nome;
	private String cognome;
	private String sesso;
	private String cellulare;
	private Date dataNascita;
	private int matricolaFIN;
	
	public Iscritto(int idIscritto, String nome, String cognome, String sesso, String cellulare, Date dataNascita,
			int matricolaFIN) {
		super();
		IdIscritto = idIscritto;
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.cellulare = cellulare;
		this.dataNascita = dataNascita;
		this.matricolaFIN = matricolaFIN;
	}
	@Override
	public String toString() {
		return "Iscritto [IdIscritto=" + IdIscritto + ", nome=" + nome + ", cognome=" + cognome + ", sesso=" + sesso
				+ ", cellulare=" + cellulare + ", dataNascita=" + dataNascita + ", matricolaFIN=" + matricolaFIN + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + IdIscritto;
		result = prime * result + ((cellulare == null) ? 0 : cellulare.hashCode());
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((dataNascita == null) ? 0 : dataNascita.hashCode());
		result = prime * result + matricolaFIN;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sesso == null) ? 0 : sesso.hashCode());
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
		Iscritto other = (Iscritto) obj;
		if (IdIscritto != other.IdIscritto)
			return false;
		if (cellulare == null) {
			if (other.cellulare != null)
				return false;
		} else if (!cellulare.equals(other.cellulare))
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (dataNascita == null) {
			if (other.dataNascita != null)
				return false;
		} else if (!dataNascita.equals(other.dataNascita))
			return false;
		if (matricolaFIN != other.matricolaFIN)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sesso == null) {
			if (other.sesso != null)
				return false;
		} else if (!sesso.equals(other.sesso))
			return false;
		return true;
	}
	public int getIdIscritto() {
		return IdIscritto;
	}
	public void setIdIscritto(int idIscritto) {
		IdIscritto = idIscritto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getSesso() {
		return sesso;
	}
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	public String getCellulare() {
		return cellulare;
	}
	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}
	public Date getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	public int getMatricolaFIN() {
		return matricolaFIN;
	}
	public void setMatricolaFIN(int matricolaFIN) {
		this.matricolaFIN = matricolaFIN;
	}

}
