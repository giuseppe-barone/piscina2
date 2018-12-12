package it.unirc.bd.dao.beans;

public class Atleta {
	private int MatricolaFIN;
	private String Categoria;
	private int IdIscritto;
	public Atleta(int matricolaFIN, String categoria, int idIscritto) {
		super();
		MatricolaFIN = matricolaFIN;
		Categoria = categoria;
		IdIscritto = idIscritto;
	}
	@Override
	public String toString() {
		return "Atleta [MatricolaFIN=" + MatricolaFIN + ", Categoria=" + Categoria + ", IdIscritto=" + IdIscritto + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Categoria == null) ? 0 : Categoria.hashCode());
		result = prime * result + IdIscritto;
		result = prime * result + MatricolaFIN;
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
		Atleta other = (Atleta) obj;
		if (Categoria == null) {
			if (other.Categoria != null)
				return false;
		} else if (!Categoria.equals(other.Categoria))
			return false;
		if (IdIscritto != other.IdIscritto)
			return false;
		if (MatricolaFIN != other.MatricolaFIN)
			return false;
		return true;
	}
	public int getMatricolaFIN() {
		return MatricolaFIN;
	}
	public void setMatricolaFIN(int matricolaFIN) {
		MatricolaFIN = matricolaFIN;
	}
	public String getCategoria() {
		return Categoria;
	}
	public void setCategoria(String categoria) {
		Categoria = categoria;
	}
	public int getIdIscritto() {
		return IdIscritto;
	}
	public void setIdIscritto(int idIscritto) {
		IdIscritto = idIscritto;
	}
}
