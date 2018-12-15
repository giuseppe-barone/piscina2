package it.unirc.bd.dao.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;

import it.unirc.bd.dao.utils.DBManager;

public class IscrittoDAOP {
	private Connection conn=null;
	
	//----RICERCA PER MatricoolaFin----
	//-----------------RICERCA PER ID -------------------
	public Iscritto getAtleta(Iscritto i) {
		String query = "SELECT * FROM iscritto WHERE MatricolaFin = ?";
		Iscritto res = null;
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, i.getMatricolaFIN());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				res=new Iscritto();
				res.setIdIscritto(rs.getInt("idIscritto"));
				res.setNome( rs.getString("Nome") );
				res.setCognome(rs.getString("Cognome"));
				res.setSesso( rs.getString("Sesso") );
				res.setCellulare( rs.getString("Cellulare") );
				res.setDataNascita(rs.getDate("DataDiNascita"));
				res.setMatricolaFIN( rs.getInt("MatricolaFin") );
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	} 
	
	
	
	
	//---INSERIMENTO ISCRITTO----
	public boolean salvaIscritto(Iscritto i) {
		boolean isAtleta=true;
		String query;
		if (Integer.toString(i.getMatricolaFIN()).equals("") || i.getMatricolaFIN()==null)
			isAtleta=false;
		if (isAtleta)
		query = "INSERT INTO iscritto VALUES (?,?,?,?,?,?,?)";
		else
			query = "INSERT INTO `piscina`.`iscritto` (`idIscritto`, `Nome`, `Cognome`, `Sesso`,`Cellulare`, `DataDiNascita`) VALUES (?,?,?,?,?,?);";
		boolean esito=false;
		conn=DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, i.getIdIscritto());
			ps.setString(2, i.getNome() );
			ps.setString(3, i.getCognome());
			ps.setString(4, i.getSesso());
			ps.setString(5, i.getCellulare());
			ps.setDate(6, i.getDataNascita());
			
			if(isAtleta)
			ps.setInt(7, i.getMatricolaFIN());
			
			int tmp=ps.executeUpdate();
			if (tmp==1)
				esito=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	//----CONTROLLO DINAMICO ID ISCRITTO----
	public boolean ControlloDinamicoIdIscritto(int id) {
		String query = "SELECT * FROM iscritto WHERE idIscritto = ?";
		boolean risultato = false;
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				risultato = true; //ESISTE GIA UNA TUPLA CON QUELL'ID
			else
				risultato = false; //NON ESISTE GIA UNA TUPLA
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return risultato;
	}
	
	
	/*//----RESTITUZIONE DI TUTTI GLI ATLETI----PER LA COMBOBOX----NON CANCELLARE PER SICUREZZA
	public DefaultComboBoxModel<String> getAtleticb(){
		DefaultComboBoxModel<String> risultato = new DefaultComboBoxModel<String>();
		String query = "SELECT * FROM piscina.iscritto WHERE MatricolaFin!='null';";
		Iscritto res = new Iscritto();
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				res=new Iscritto();
				res.setIdIscritto(rs.getInt("idIscritto"));
				res.setNome( rs.getString("Nome") );
				res.setCognome( rs.getString("Cognome") );
				res.setSesso( rs.getString("Sesso") );
				res.setCellulare( rs.getString("Cellulare") );
				res.setDataNascita( rs.getDate("DataDiNascita"));
				res.setMatricolaFIN(rs.getInt("MatricolaFin"));
				
				risultato.addElement(res.toStringAtleta());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return risultato;
	
}		*/
	
	
	
	//----RESTITUZIONE DI TUTTI GLI ATLETI----PER LA COMBOBOX----PROVA CON OGETTO ISCRITTO----
		public DefaultComboBoxModel<Iscritto> getAtleticb(){
			DefaultComboBoxModel<Iscritto> risultato = new DefaultComboBoxModel<Iscritto>();
			String query = "SELECT * FROM piscina.iscritto WHERE MatricolaFin!='null';";
			Iscritto res = new Iscritto();
			PreparedStatement ps;
			conn=DBManager.startConnection();
			try {
				ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					res=new Iscritto();
					res.setIdIscritto(rs.getInt("idIscritto"));
					res.setNome( rs.getString("Nome") );
					res.setCognome( rs.getString("Cognome") );
					res.setSesso( rs.getString("Sesso") );
					res.setCellulare( rs.getString("Cellulare") );
					res.setDataNascita( rs.getDate("DataDiNascita"));
					res.setMatricolaFIN(rs.getInt("MatricolaFin"));
					
					risultato.addElement(res);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBManager.closeConnection();
			return risultato;
		
	}		
	
	
	
	
}

