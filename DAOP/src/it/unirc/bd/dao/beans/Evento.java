package it.unirc.bd.dao.beans;

import java.sql.Date;

public class Evento {
	public Evento() {
		super();
		// TODO Auto-generated constructor stub
	}
	private Integer idEvento;
	private Date data;
	private String livello;
	private String tipo;
	
	public Evento(Integer idEvento, Date data, String livello, String tipo) {
		super();
		this.idEvento = idEvento;
		this.data = data;
		this.livello = livello;
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return 	tipo +" | "+livello+" | "+data.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + idEvento;
		result = prime * result + ((livello == null) ? 0 : livello.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Evento other = (Evento) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (idEvento != other.idEvento)
			return false;
		if (livello == null) {
			if (other.livello != null)
				return false;
		} else if (!livello.equals(other.livello))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	public int getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getLivello() {
		return livello;
	}
	public void setLivello(String livello) {
		this.livello = livello;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
