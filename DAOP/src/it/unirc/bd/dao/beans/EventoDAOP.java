package it.unirc.bd.dao.beans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

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


	//------------------RESTITUZIONE TUTTE LE TUPLE-----------
	public Vector<Evento> getAll(Date data,String tipo,String livello, boolean isTutti ){
			Vector<Evento> risultato=new Vector<Evento>();
			String query = "SELECT * FROM evento WHERE idEvento IS NOT NULL ? ? ? ;";
			String Tipo;
			String Livello;
			String Data;
			Evento res;
			PreparedStatement ps;
			conn=DBManager.startConnection();
			try {
				ps = conn.prepareStatement(query);
				if (!isTutti) {
					if (!tipo.equals(null)) {
						Tipo="AND `Tipo`="+tipo+" ";
						ps.setString(1, Tipo);
					}
					else
						ps.setString(1, "");
					if (!livello.equals(null)) {
						Livello="AND `Livello`="+livello+" ";
						ps.setString(2, Livello);
					}
					else
						ps.setString(2, "");
					if (!data.equals(null)) {
						Data="AND `Data`="+data.toString()+" ";
						ps.setString(3, Data);
					}
					else
						ps.setString(3, "");
				}
				else {
					query="SELECT * FROM evento ;";
				}
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					System.out.println("funziona");
					res=new Evento();
					res.setIdEvento(rs.getInt("idEvento"));
					res.setData(rs.getDate("Data"));
					res.setLivello(rs.getString("Livello"));
					res.setTipo(rs.getString("Tipo"));
					risultato.add(res);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBManager.closeConnection();
			return risultato;
		
	}

	
	
	
	
	
	
	
	
	/*
	 
	 //------------------RESTITUZIONE TUTTE LE TUPLE-----------
	public Vector<Evento> getAll(Date data,String tipo,String livello, boolean isTutti ){
			Vector<Evento> risultato=new Vector<Evento>();
			String query = "SELECT * FROM evento WHERE Tipo=? AND Livello=? AND Data=? ";

			Evento res;
			PreparedStatement ps;
			conn=DBManager.startConnection();
			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, tipo);
				ps.setString(2, livello);
				ps.setString(3, data.toString());
				//System.out.println(tipo);
			//	System.out.println(livello);
				//System.out.println(data);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					System.out.println("funziona");
					res=new Evento();
					res.setIdEvento(rs.getInt("idEvento"));
					res.setData(rs.getDate("Data"));
					res.setLivello(rs.getString("Livello"));
					res.setTipo(rs.getString("Tipo"));
					risultato.add(res);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBManager.closeConnection();
			return risultato;
		
	}
	 
	 
	 */
	
	
	
	
	
	
	

	
	 


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
	protected Evento recordToEvento(ResultSet rs) throws SQLException {
		Evento e = new Evento();
		e.setIdEvento(rs.getInt("idEvento"));
		e.setData(rs.getDate("data"));
		e.setLivello(rs.getString("livello"));
		e.setTipo(rs.getString("tipo"));
		return e;
	}
	public Vector<Evento> getAll(){
		String query = "SELECT * FROM evento";
		Vector<Evento> list = new Vector<Evento>();
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps=conn.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			Evento e=null;
			while(rs.next()) {
				list.add(recordToEvento(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return list;
	}


}
