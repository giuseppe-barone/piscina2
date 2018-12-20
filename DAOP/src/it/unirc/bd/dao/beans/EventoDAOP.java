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




	/*
//------------------RESTITUZIONE TUTTE LE TUPLE--------------public Vector<CorsoDiLaurea> getAll() che restituisce tutti i corsi di laurea
			public Vector<CorsoDiLaurea> getAll(){
					Vector<CorsoDiLaurea> risultato=new Vector<CorsoDiLaurea>();
					String query = "SELECT * FROM CorsoDiLaurea";
					CorsoDiLaurea res = new CorsoDiLaurea();
					PreparedStatement ps;
					conn=DBManager.startConnection();
					try {
						ps = conn.prepareStatement(query);
						ResultSet rs = ps.executeQuery();
						while(rs.next()){
							res=new CorsoDiLaurea();
							res.setId(rs.getInt("id"));
							res.setNome( rs.getString("nome") );
							risultato.add(res);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					DBManager.closeConnection();
					return risultato;

			}
	 */


	public Vector<Evento> Ricerca(String tipo,Date data,String livello, boolean isTutti) {
		Vector<Evento> risultato=new Vector<Evento>();
		String query = "SELECT * FROM evento WHERE idEvento IS NOT NULL ? ? ?;";
		Evento res =new Evento();
		PreparedStatement ps;
		boolean esito = false;
		ResultSet rs;
		
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			if (!isTutti){	//CASO IN CUI SERVONO RESTRIZIONI DI RICERCA
				
				if (tipo!=null) {
					System.out.println("cipolla");
					ps = conn.prepareStatement(query);
					ps.setString(1, ",Tipo='"+tipo+"'");
				}
				else
					ps.setString(1, "");
				System.out.println(query);

				if (!livello.equals(null)) {
					ps.setString(2, ",Livello='"+livello+"'");
				}
				else
					ps.setString(2, "");


				if (!data.equals(null)) {
					ps.setString(3, ",Data='"+data.toString()+"'");
				}
				else
					ps.setString(3, "");
				System.out.println(query);
				}
			else{	//CASO IN CUI NON SERVONO RESTRIZIONI DI RICERCA
				//----PREPARAZIONE QUERY
				ps = conn.prepareStatement(query);
				query = "SELECT * FROM evento;";			
			}
			
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				res=new Evento();
				res.setIdEvento(rs.getInt("idEvento")); 
				res.setData( rs.getDate("Data")); 
				res.setLivello( rs.getString("Livello"));  
				res.setTipo(rs.getString("Tipo"));
				risultato.add(res);
			}
			if (rs.next()) {
				esito=true;
			}
			else
				esito=false;


		}catch(SQLException exc) {
			exc.printStackTrace();
		}
		DBManager.closeConnection();
		System.out.println(query);
		//----INSERIRE I CONTROLLI PER FAR VISUALIZZARE LA RICERCA CON UN MESSAGEBOXALLERT----
		/*if(esito) {
			JOptionPane.showMessageDialog(null, "Inserimento con successo");
		}
		else
			JOptionPane.showMessageDialog(null, "Inserimento fallito");*/

		return risultato;
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
