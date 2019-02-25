package it.unirc.bd.dao.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

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



	public Vector<Dipendente> getAll(){
		String query = "SELECT * FROM dipendente";
		Vector<Dipendente> list = new Vector<Dipendente>();
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Dipendente res=null;
			while(rs.next()){
				list.add(recordToDipendente(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return list;
	}



	protected Dipendente recordToDipendente(ResultSet rs) throws SQLException {
		Dipendente d = new Dipendente();
		d.setIdDipendente(rs.getInt("idDipendente"));
		d.setNome(rs.getString("nome"));
		d.setCognome(rs.getString("cognome"));
		d.setCellulare(rs.getString("cellulare"));
		d.setSesso(rs.getString("sesso"));
		d.setTipologiaDipendente(rs.getInt("tipologiaDipendente"));
		return d;
	}


}




