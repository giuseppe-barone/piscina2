package it.unirc.bd.dao.beans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;

import it.unirc.bd.dao.utils.DBManager;

public class EventoDAOP {
	private static Connection conn=null;
	
	public boolean salvaEvento(Evento e) {
		String query = "INSERT INTO evento VALUES (?, ?, ?, ?)";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, e.getIdEvento());
			ps.setDate(2, e.getData());
			ps.setString(3, e.getLivello());
			ps.setString(4, e.getTipo());
			int tmp = ps.executeUpdate();
			if(tmp==1)
				esito=true;
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	
	
	
	public boolean Ricerca(String tipo,Date data,String livello,Integer MatricolaFin, boolean iaAtleta ) {
		String tipo;
		String data;
		String livello;
		String query = "SELECT * FROM evento WHERE idEvento IS NOT NULL ???";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			if (!tipo.equals(null)) {
				ps.setString(1, ",Tipo='"+tipo+"'");
			}
			else
				ps.setString(1, "");
			
			if (!livello.equals(null)) {
				ps.setString(2, ",Livello='"+livello+"'");
			}
			else
				ps.setString(2, "");
			
			
			if (!data.equals(null)) {
				ps.setString(3, ",Data='"+data+"'");
			}
			else
				ps.setString(3, "");
			
			
			int tmp = ps.executeUpdate();
			if(tmp==1)
				esito=true;
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	
	
	
	public boolean ControlloDinamicoEvento(int id) {
		String query = "SELECT * FROM evento WHERE idEvento = ?";
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
	/*
	//----RESTITUZIONE DI TUTTI GLI EVENTI----PER LA COMBOBOX----NON CANCELLARE PER SICUREZZA
	public DefaultComboBoxModel<String> getEventicb(){
		DefaultComboBoxModel<String> risultato = new DefaultComboBoxModel<String>();
		String query = "SELECT * FROM piscina.evento;";
		Evento res = new Evento();
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				res=new Evento();
				res.setIdEvento(rs.getInt("idEvento"));
				res.setData(rs.getDate("Data"));
				res.setLivello( rs.getString("Livello") );
				res.setTipo( rs.getString("Tipo") );

				risultato.addElement(res.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return risultato;
	
}		*/
	
	
	
	//----RESTITUZIONE DI TUTTI GLI EVENTI----PER LA COMBOBOX ----PROVA CON OGETTO DI TIPO EVENTO----FUNZIONA
	public DefaultComboBoxModel<Evento> getEventicb(){
		DefaultComboBoxModel<Evento> risultato = new DefaultComboBoxModel<Evento>();
		String query = "SELECT * FROM piscina.evento;";
		Evento res = new Evento();
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				res=new Evento();
				res.setIdEvento(rs.getInt("idEvento"));
				res.setData(rs.getDate("Data"));
				res.setLivello( rs.getString("Livello") );
				res.setTipo( rs.getString("Tipo") );

				risultato.addElement(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return risultato;
	
}		
	
	

}
