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
	private IscrittoDAOP iDAOP=new IscrittoDAOP();
	
	public boolean salvaPartecipazione(int idEvento,int Matricola, int posizione,String categoria ) {
		String query = "INSERT INTO partecipazione (`idEvento`, `MatricolaFin`, `Posizione`,`Categoria`) VALUES ( ?, ?, ?,?)";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);	
			ps.setInt(1, idEvento);
			ps.setInt(2, Matricola);
			ps.setInt(3,posizione);
			ps.setString(4,categoria);
			int tmp = ps.executeUpdate();
			if(tmp==1)
				esito=true;
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	
	public boolean salvaEvento(Evento e) {
		String query = "INSERT INTO evento (`Data`, `Livello`, `Tipo`) VALUES ( ?, ?, ?)";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);	
			ps.setDate(1, e.getData());
			ps.setString(2, e.getLivello());
			ps.setString(3, e.getTipo());
			int tmp = ps.executeUpdate();
			if(tmp==1)
				esito=true;
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}




	//------------------RICERCA PER LIVELLO-----------
	public Vector<Evento> RicercaPerLivello(String livello ){
		Vector<Evento> risultato=new Vector<Evento>();
		String query = "SELECT * FROM evento WHERE Livello=?";

		Evento res;
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, livello);

			ResultSet rs = ps.executeQuery();
			while(rs.next()){

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
	//----RICERCA PER DATA----
	public Vector<Evento> RicercaPerData(Date data ){
		Vector<Evento> risultato=new Vector<Evento>();
		String query = "SELECT * FROM evento WHERE Data=?";

		Evento res;
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, data.toString());

			ResultSet rs = ps.executeQuery();
			while(rs.next()){

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

	//----ELENCO TIPI SENZA RIDONDANZE----
	public DefaultComboBoxModel<String> ElencoTipi( ){
		DefaultComboBoxModel<String> risultato=new DefaultComboBoxModel<String>();
		String query = "SELECT DISTINCT Tipo FROM evento;";
		String res;
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				res=rs.getString("Tipo");
				risultato.addElement(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return risultato;

	}
	
	

	
	

	//----ELENCO EVENTI A DISPOSIZIONE (SU SCELTA DOPO LA DATA CORRENTE) ----
	public DefaultComboBoxModel<Evento> ElencoEventiDisponibili(boolean modifica){//se modifica è true allora si cercano temporalmente tutti gli eventi
		DefaultComboBoxModel<Evento> risultato=new DefaultComboBoxModel<Evento>();
		String condizione="";
		if (modifica!=true)
			condizione="WHERE Data>=CURDATE() ";
		String query = "SELECT * FROM evento "+condizione +";";
		Evento res=new Evento();
		
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				res=new Evento();
				res.setData(rs.getDate("Data"));
				res.setIdEvento(rs.getInt("idEvento"));
				res.setLivello(rs.getString("Livello"));
				res.setTipo(rs.getString("Tipo"));
				risultato.addElement(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return risultato;

	}


	//----RICERCA PER TIPO----
	public Vector<Evento> RicercaPerTipo(String tipo ){
		Vector<Evento> risultato=new Vector<Evento>();
		String query = "SELECT * FROM evento WHERE Tipo=?";

		Evento res;
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, tipo);

			ResultSet rs = ps.executeQuery();
			while(rs.next()){

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



	//
	//	SELECT evento.* FROM evento 	INNER JOIN partecipazione ON evento.idEvento = partecipazione.idEvento and partecipazione.MatricolaFin=12;



	//----RICERCA PER TIPO----
	public Vector<Evento> RicercaPerMatricolaFin(Integer matricola ){
		Vector<Evento> risultato=new Vector<Evento>();
		String query = "SELECT evento.* FROM evento INNER JOIN partecipazione ON evento.idEvento = partecipazione.idEvento and partecipazione.MatricolaFin=?;";

		Evento res;
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, matricola);

			ResultSet rs = ps.executeQuery();
			while(rs.next()){

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

	//--------------------MODIFICA EVENTO-----------------
	public boolean ModificaEvento(Evento e){
		String query = "UPDATE evento SET Data=?, Livello=?, Tipo=? WHERE idEvento=?";
		boolean esito=false;
		conn=DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(4, e.getIdEvento());
			ps.setDate(1, e.getData());
			ps.setString(2, e.getLivello());
			ps.setString(3, e.getTipo());
			int tmp=ps.executeUpdate();
			if (tmp==1)
				esito=true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
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
	//-----------------RICERCA PER ID -------------------
	public Evento getEventoId(Integer ID) {
		String query = "SELECT * FROM evento WHERE idEvento = ?";
		Evento res = null;
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, ID);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				res=new Evento();
				res.setIdEvento(rs.getInt("idEvento"));
				res.setData(rs.getDate("Data"));
				res.setLivello(rs.getString("Livello"));
				res.setTipo(rs.getString("Tipo"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		System.out.println(res.toString());
		return res;
	} 
	
	//-----------------------------PARTECIPAZIONE------------------------
	
	
	//CERCA TUTTE LE PARTECIAPZIONI
	public Vector<String[]> getTuttePartecipazioni() throws SQLException {
		String query = "SELECT * FROM partecipazione;";
		PreparedStatement ps;
		Vector<String[]> lista=new Vector<String[]>();
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				//RITORNERò UN VECTOR DI ARRAY DI STRINGHE, OGNI TIGA CONTERRà:
				//DATI PER L'EVENTO: TIPO, DATA; ISCRITTO: NOME+COGNOME, MATRICOLA, CATEGORIA,  POSIZIONE, 
				String[] stringa=new String[6];
				Evento e ;
				e=getEventoId(rs.getInt("idEvento"));
				stringa[0]=e.getTipo();
				stringa[1]=e.getData().toString();
				Iscritto i=iDAOP.getAtleta(rs.getInt("MatricolaFin"));
				stringa[2]=i.getNome()+" "+i.getCognome();
				stringa[3]= Integer.toString(i.getMatricolaFIN());
				stringa[4]=rs.getString("Categoria");
				switch (rs.getInt("Posizione")) {
				case 0:
					stringa[5]="N/D";
					break;
				case 1:
					stringa[5]="1°";
					break;
				case 2:
					stringa[5]="2°";
					break;
				case 3:
					stringa[5]="3°";
					break;
				case 4:
					stringa[5]="ALTRO";
					break;
				}
				lista.add(stringa);
				System.out.println("STRINGA AGGIUNTA");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();	
	/*	System.out.println("PROVA DI SCRITTURA-------------INIZIO------------");
		for (String[] s : lista) {
			for (int x=0;x<5;x++)
				System.out.println(s[x]);
		}
		System.out.println("PROVA DI SCRITTURA-------------FINE--------------");
		*/
		return lista;
	}
	
	


}
