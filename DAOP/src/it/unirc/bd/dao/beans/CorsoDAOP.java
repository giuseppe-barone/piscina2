package it.unirc.bd.dao.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.unirc.bd.dao.utils.DBManager;


public class CorsoDAOP {
	private static Connection conn = null;





	//----------------INSERISCI CORSO----------------------
	public boolean salvaCorso(Corso c){
		String query = "INSERT INTO corso VALUES (?, ?,?,?,?,?)";
		boolean esito=false;
		conn=DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, c.getIdCorso().intValue());
			ps.setInt(2, c.getGiorni().intValue() );
			ps.setInt(3, c.getOra().intValue());
			ps.setString(4, c.getTipo());
			ps.setInt(5, c.getAllenatore1().intValue());
			ps.setInt(6, c.getAllenatore2().intValue());
			int tmp=ps.executeUpdate();
			if (tmp==1)
				esito=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}



	//-----------------CONTROLLO DINAMICO ID CORSO -------------------RITORNA VERO SE ESISTE O NON è UN VALORE ACCETTABILE FALSO ALTRIMENTI
	public boolean ControlloDinamicoIdCorso(Integer ID) {
		boolean risultato =false;
		//----INSERIRE UN CONTROLLO CHE MI PERMETTA DI VEDERE SE L'OGETTO PASSATO è VUOTO E RESTITUISCA SUBITO UN VALORE
		//INSERISCO QUESTO CONTROLLO PRIMA DEL RESTO DEL CONDICE PERCHè SENNO PARTIREBBE UNA QUERY CON DEI VALORI INCOMPATIBILI
		//ANZICHE PASSARE AL METODO UN TIPO INT PASSARE AL METODO UN TIPO INTEGER (OGETTO) CHE PUò ESSERE ANCHE NULL
		if (ID==null || Integer.toString(ID.intValue()).equals("")) {
			risultato=true;
			return risultato;
		}						
		String query = "SELECT * FROM corso WHERE idCorso = ?";
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, ID.intValue());
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



	//-----------------CONTROLLO PRESENZA ALLENATORE -------------------RITORNA TRUE SE ESISTE ALMENO UN CORSO CHE è TENUTO (ALLENATORE1 O ALLENATORE2) DALL'ALLENATORE PASSATO AL METODO 
	public boolean ControlloPresenzaAllenatore(Integer ID) {
		boolean risultato =false;
		String query = "SELECT * FROM corso WHERE Allenatore1 = ? OR Allenatore2= ?;";
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, ID.intValue());
			ps.setInt(2, ID.intValue());
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
	
	protected Corso recordToCorso(ResultSet rs) throws SQLException{
		Corso c = new Corso();
		c.setIdCorso(rs.getInt("id"));
		c.setGiorni(rs.getInt("giorni"));
		c.setOra(rs.getInt("ora"));
		c.setTipo(rs.getString("tipo"));
		c.setAllenatore1(rs.getInt("allenatore1"));
		c.setAllenatore2(rs.getInt("allenatore2"));
		return c;
	}
	
	public Vector<Corso> getAll(){
		String query = "SELECT * FROM corso";
		Vector<Corso> list = new Vector<Corso>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Corso c = null;
			while(rs.next()) {
				list.add(recordToCorso(rs));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return list;
	}

}