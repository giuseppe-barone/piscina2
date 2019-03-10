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
	
	/* DA CONTROLLARE
	 RICERCA TRAMITE ID
	 public Prenotazione getRicercaId(Prenotazione p) {
    String query = "SELECT * FROM Prenotazione WHERE idPrenotazione= ?";
    Prenotazione res = null;
    PreparedStatement ps;
    conn = DBManager.startConnection();
    try {
      ps = conn.prepareStatement(query);
      ps.setInt(1, p.getIdPrenotazione());
      ResultSet rs = ps.executeQuery();
      if(rs.next()) {
        res = new Prenotazione();
        res.setIdDipendente(rs.getInt("idIscritto"));
        res.setCorsia(rs.getInt("Corsia"));
        res.setData(rs.getDate("Data"));
        res.setIdIscritto(rs.getInt("idIscritto"));
        res.setTipoPiscina(rs.getString("TipoPiscina"));
        res.setOra(rs.getInt("Ora"));
        res.setIdDipendente(rs.getInt("idDipendente"));
      }
    }catch (SQLException e) {
      e.printStackTrace();
    }
    DBManager.closeConnection();
    return res;
}
	
	RICERCA TRAMITE MULTI INFORMAZIONI DA VERIFICARE
	public Prenotazione getMultiRicerca(Prenotazione p){
		String query = "SELECT * FROM Prenotazione WHERE data=?, ora=?";
		Prenotazione res = null;
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
      ps = conn.prepareStatement(query);
      ps.setInt(1, p.getIdPrenotazione());
      ResultSet rs = ps.executeQuery();
      if(rs.next()) {
        res = new Prenotazione();
        res.setIdDipendente(rs.getInt("idIscritto"));
        res.setCorsia(rs.getInt("Corsia"));
        res.setData(rs.getDate("Data"));
        res.setIdIscritto(rs.getInt("idIscritto"));
        res.setTipoPiscina(rs.getString("TipoPiscina"));
        res.setOra(rs.getInt("Ora"));
        res.setIdDipendente(rs.getInt("idDipendente"));
      }
    }catch (SQLException e) {
      e.printStackTrace();
    }
    DBManager.closeConnection();
    return res;
}
	 */

	public boolean salva(Prenotazione p) {
		String query = "INSERT INTO prenotazione VALUES (?,?,?,?,?,?,?)";
		boolean esito = false;
		conn=DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, p.getIdPrenotazione());
			ps.setInt(2, p.getCorsia());
			ps.setDate(3, p.getData());
			ps.setInt(4, p.getIdIscritto());
			ps.setString(5, p.getTipoPiscina());
			ps.setInt(6, p.getOra());
			ps.setInt(7, p.getIdDipendente());
			int tmp=ps.executeUpdate();
			if(tmp==1)
				esito=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	public boolean ControlloDisponibilit�Piscina(Integer p){
		boolean risultato = false;
		String query = "SELECT * FROM corso where Ora=?, Data=?";
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try{
			ps = conn.prepareStatement(query);
			ps.setInt(1, p.intValue());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				risultato = true; //Esiste una tupla con quell'ID di conseguenza
			//Non si pu� prenotare
			else
				risultato = false; //Si pu� prenotare
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return risultato;
	}
	protected Prenotazione recordToPrenotazione(ResultSet rs) throws SQLException {
		Prenotazione p = new Prenotazione();
		p.setIdPrenotazione(rs.getInt("IdPrenotazione"));
		p.setCorsia(rs.getInt("Corsia"));
		p.setData(rs.getDate("data"));
		p.setIdIscritto(rs.getInt("idIscritto"));
		p.setTipoPiscina(rs.getString("tipoPiscina"));
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
			Prenotazione res=null;
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
					res.setTipoPiscina(rs.getString("TipoPiscina"));
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
		//----RICERCA PER TIPO DI PISCINA----
				public Vector<Prenotazione> RicercaPerTipo(String tipo ){
					Vector<Prenotazione> risultato=new Vector<Prenotazione>();
					String query = "SELECT * FROM prenotazione WHERE TipoPiscina=?";
					Prenotazione res;
					PreparedStatement ps;
					conn=DBManager.startConnection();
					try {
						ps = conn.prepareStatement(query);
						ps.setString(1, tipo);

						ResultSet rs = ps.executeQuery();
						while(rs.next()){

							res=new Prenotazione();
							res.setIdPrenotazione(rs.getInt("idPrenotazione"));
							res.setCorsia(rs.getInt("Corsia"));
							res.setData(rs.getDate("Data"));
							res.setIdIscritto(rs.getInt("idIscritto"));
							res.setTipoPiscina(rs.getString("TipoPiscina"));
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
							res.setTipoPiscina(rs.getString("TipoPiscina"));
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
		


}
