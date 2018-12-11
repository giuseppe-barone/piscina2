package it.unirc.bd.dao.beans;

public class Iscritto {
	private int IdIscritto;
	private String nome;
	private String cognome;
	private String sesso;
	private int eta;
	public Iscritto(int idIscritto, String nome, String cognome, String sesso, int eta) {
		super();
		IdIscritto = idIscritto;
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.eta = eta;
	}
	@Override
	public String toString() {
		return "Iscritto [IdIscritto=" + IdIscritto + ", nome=" + nome + ", cognome=" + cognome + ", sesso=" + sesso
				+ ", eta=" + eta + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + IdIscritto;
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + eta;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sesso == null) ? 0 : sesso.hashCode());
		return result;
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
	public int getEta() {
		return eta;
	}
	public void setEta(int eta) {
		this.eta = eta;
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
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (eta != other.eta)
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
}
