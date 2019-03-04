package it.unirc.bd.dao.beans;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

public class Iscritto {
	public Iscritto(int idIscritto, String nome, String cognome, String sesso, String cellulare, Date dataNascita,
			Integer matricolaFIN) {
		super();
		IdIscritto = idIscritto;
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.cellulare = cellulare;
		this.dataNascita = dataNascita;
		this.matricolaFIN = matricolaFIN;
	}
	private int IdIscritto;
	private String nome;
	private String cognome;
	private String sesso;
	private String cellulare;
	private Date dataNascita;
	private Integer matricolaFIN;
	public Iscritto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return nome + " " + cognome + " " + sesso + " " + dataNascita + " MatricolaFin=" + matricolaFIN ; 
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
	//----GET E SET----
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
	public Integer getMatricolaFIN() {
		return matricolaFIN;
	}
	public void setMatricolaFIN(Integer matricolaFIN) {
		this.matricolaFIN = matricolaFIN;
	}
	//----METODO CHE MI SERVE PER FAMRI MOSTRARE SOTTOFORMA DI STRINGA SOLO LE INFORMAZIONI DI UN ATLETA (NELLA COMBOBOX)----
	public String toStringAtleta() {
		return nome +" "+cognome+" "+dataNascita ;
	}
	
	//CALOCLO DELLA CATEGORIA IN BASE ALLA DATA DI NASCITA DELL'ATLETA
	
		public String CalcoloCategoria(Iscritto i) {
			String risultato = null;
			int eta = 0;
			boolean isMaschio;
			if (i.getSesso().equals("Maschio"))
				isMaschio=true;
			else
				isMaschio=false;
			//--------------CODICE PER IL CALCOLO DELL'ETA DA INSERIRE A PARTE PER PEPPE----------------
			LocalDate corrente=LocalDate.now();
			Date nascita=i.getDataNascita();
			LocalDate LNascita=nascita.toLocalDate();
			//System.out.println(LNascita.toString());

			//System.out.println(corrente.toString());
			if ((corrente != null) && (nascita != null)) {
	          // System.out.println(Period.between(LNascita, corrente).getYears());
	           eta=Period.between(LNascita, corrente).getYears();
	           
	        }
			if (isMaschio) {	//SE è MASCHIO
				if (eta<14)
					risultato="Esordienti";
				else if (eta<17)
					risultato="Ragazzi";
				else if (eta<21)
					risultato="Cadetti";
				else
					risultato="Seniores";
			}
			else {				//SE NON è MASCIO
				if (eta<13)
					risultato="Esordienti";
				else if (eta<15)
					risultato="Ragazzi";
				else if (eta<19)
					risultato="Cadetti";
				else
					risultato="Seniores";
			}
			return risultato;
		}
	
	
}
