package it.unirc.bd.dao.beans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.unirc.bd.dao.utils.DBManager;

public class PrenotazioneDAOP {
	private Connection conn = null;
	
	
	public boolean salva(Prenotazione p) {
		String query = "INSERT INTO prenotazione (Corsia,Data,idIscritto,Ora,idDipendente) VALUES (?,?,?,?,?)";
		boolean esito = false;
		if (!ControlloDisponibilita(p))
			return esito;
		else {
		conn=DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, p.getCorsia());
			ps.setDate(2, p.getData());
			ps.setInt(3, p.getIdIscritto());
			ps.setInt(4, p.getOra());
			ps.setInt(5, p.getIdDipendente());
			int tmp=ps.executeUpdate();
			if(tmp==1)
				esito=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
		}
	}
	protected Prenotazione recordToPrenotazione(ResultSet rs) throws SQLException {
		Prenotazione p = new Prenotazione();
		p.setIdPrenotazione(rs.getInt("IdPrenotazione"));
		p.setCorsia(rs.getInt("Corsia"));
		p.setData(rs.getDate("data"));
		p.setIdIscritto(rs.getInt("idIscritto"));
		p.setOra(rs.getInt("ora"));
		p.setIdDipendente(rs.getInt("idDipendente"));
		return p;
	}
	
	public Vector<Prenotazione> getAll() {
		String query = "SELECT * FROM prenotazione";
		Vector<Prenotazione> list = new Vector<Prenotazione>();
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				list.add(recordToPrenotazione(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return list;
	} 
	//----RICERCA PER DATA----
		public Vector<Prenotazione> RicercaPerData(Date data ){
			Vector<Prenotazione> risultato=new Vector<Prenotazione>();
			String query = "SELECT * FROM prenotazione WHERE Data=?";
			Prenotazione res;
			PreparedStatement ps;
			conn=DBManager.startConnection();
			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, data.toString());

				ResultSet rs = ps.executeQuery();
				while(rs.next()){

					res=new Prenotazione();
					res.setIdPrenotazione(rs.getInt("idPrenotazione"));
					res.setCorsia(rs.getInt("Corsia"));
					res.setData(rs.getDate("Data"));
					res.setIdIscritto(rs.getInt("idIscritto"));
					res.setOra(rs.getInt("Ora"));
					res.setIdDipendente(rs.getInt("idDipendente"));
					risultato.add(res);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBManager.closeConnection();
			return risultato;

		}

		//----RICERCA PER ORARIO----
				public Vector<Prenotazione> RicercaPerOrario(int ora ){
					Vector<Prenotazione> risultato=new Vector<Prenotazione>();
					String query = "SELECT * FROM prenotazione WHERE Ora=?";
					Prenotazione res;
					PreparedStatement ps;
					conn=DBManager.startConnection();
					try {
						ps = conn.prepareStatement(query);
						ps.setInt(1, ora);

						ResultSet rs = ps.executeQuery();
						while(rs.next()){

							res=new Prenotazione();
							res.setIdPrenotazione(rs.getInt("idPrenotazione"));
							res.setCorsia(rs.getInt("Corsia"));
							res.setData(rs.getDate("Data"));
							res.setIdIscritto(rs.getInt("idIscritto"));
							res.setOra(rs.getInt("Ora"));
							res.setIdDipendente(rs.getInt("idDipendente"));
							risultato.add(res);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					DBManager.closeConnection();
					return risultato;

				}
				
				
				
				//CONTROLLO DISPONIBILITà
				//----CONTROLLO DISPONIBILITà----
				private boolean ControlloDisponibilita(Prenotazione p ){	//ritorna vero se è disponibile la data e l'orario scelto
					boolean disponibile=true;
					String query = "SELECT * FROM prenotazione WHERE Data=? and Ora=?";

					PreparedStatement ps;
					conn=DBManager.startConnection();
					try {
						ps = conn.prepareStatement(query);
						ps.setDate(1, p.getData());
						ps.setInt(2, p.getOra());
						ResultSet rs = ps.executeQuery();
						if(rs.next()){
							disponibile=false;
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					DBManager.closeConnection();
					return disponibile;

				}
		


}
