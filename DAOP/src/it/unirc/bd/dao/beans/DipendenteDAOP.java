package it.unirc.bd.dao.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.unirc.bd.dao.utils.DBManager;


public class DipendenteDAOP {
	private static Connection conn = null;
	//----------------INSERISCI DIPENDENTE----------------------
	public boolean salvaDipendente(Dipendente d){
		String query = "INSERT INTO dipendente VALUES (?, ?,?,?,?,?)";
		boolean esito=false;
		conn=DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, d.getIdDipendente());
			ps.setString(2, d.getNome() );
			ps.setString(3, d.getCognome());
			ps.setString(4, d.getCellulare());
			ps.setString(5, d.getSesso());
			ps.setInt(6, d.getTipologiaDipendente());
			int tmp=ps.executeUpdate();
			if (tmp==1)
				esito=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}


	//----------------INSERISCI DIPENDENTE (ALLENATORE)----------------------
	public boolean salvaAllenatore(Allenatore a){
		String query = "INSERT INTO allenatore VALUES (?, ?,?)";
		boolean esito=false;
		conn=DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, a.getIdAllenatore());
			ps.setString(2, a.getQualifica() );
			ps.setInt(3, a.getIdDipendente());
			int tmp=ps.executeUpdate();
			if (tmp==1)
				esito=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}




	//-----------------CONTROLLO DINAMICO ID DIPENDENTE -------------------
	public boolean ControlloDinamicoIdDipendente(int ID) {
		//----INSERIRE UN CONTROLLO CHE MI PERMETTA DI VEDERE SE L'OGETTO PASSATO è VUOTO E RESTITUISCA SUBITO UN VALORE
		//INSERISCO QUESTO CONTROLLO PRIMA DEL RESTO DEL CONDICE PERCHè SENNO PARTIREBBE UNA QUERY CON DEI VALORI INCOMPATIBILI
		//ANZICHE PASSARE AL METODO UN TIPO INT PASSARE AL METODO UN TIPO INTEGER (OGETTO) CHE PUò ESSERE ANCHE NULL
		String query = "SELECT * FROM dipendente WHERE idDipendente = ?";
		boolean risultato =false;
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, ID);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				risultato=true;	//ESISTE UNA TUPLA CON QUELL'ID
			else
				risultato=false;//NON ESISTE UNA TUPLA CON QUELL'ID
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return risultato;
	}

	//-----------------CONTROLLO DINAMICO ID ALLENATORE -------------------
	public boolean ControlloDinamicoIdAllenatore(int ID) {
		//----INSERIRE UN CONTROLLO CHE MI PERMETTA DI VEDERE SE L'OGETTO PASSATO è VUOTO E RESTITUISCA SUBITO UN VALORE
		//INSERISCO QUESTO CONTROLLO PRIMA DEL RESTO DEL CONDICE PERCHè SENNO PARTIREBBE UNA QUERY CON DEI VALORI INCOMPATIBILI
		//ANZICHE PASSARE AL METODO UN TIPO INT PASSARE AL METODO UN TIPO INTEGER (OGETTO) CHE PUò ESSERE ANCHE NULL
		String query = "SELECT * FROM allenatore WHERE idAllenatore = ?";
		boolean risultato =false;
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, ID);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				risultato=true;	//ESISTE UNA TUPLA CON QUELL'ID
			else
				risultato=false;//NON ESISTE UNA TUPLA CON QUELL'ID
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return risultato;
	}


}
