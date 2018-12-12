package it.unirc.bd.dao.beans;

public class Iscritto {
	private int IdIscritto;
	private String nome;
	private String cognome;
	private String sesso;
	private String cellulare;
	private int eta;
	public Iscritto(int idIscritto, String nome, String cognome, String sesso, String cellulare, int eta) {
		super();
		IdIscritto = idIscritto;
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.cellulare = cellulare;
		this.eta = eta;
	}
	@Override
	public String toString() {
		return "Iscritto [IdIscritto=" + IdIscritto + ", nome=" + nome + ", cognome=" + cognome + ", sesso=" + sesso
				+ ", cellulare=" + cellulare + ", eta=" + eta + "]";
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
		if (cellulare != other.cellulare)
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
	public int getEta() {
		return eta;
	}
	public void setEta(int eta) {
		this.eta = eta;
	}

}