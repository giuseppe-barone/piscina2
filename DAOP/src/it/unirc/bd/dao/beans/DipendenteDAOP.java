package it.unirc.bd.dao.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

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

	//----RESTITUZIONE DI TUTTI GLI EVENTI----PER LA COMBOBOX ----PROVA CON OGETTO DI TIPO EVENTO----FUNZIONA
		public DefaultComboBoxModel<Dipendente> getAllenatorecb(){
			DefaultComboBoxModel<Dipendente> risultato = new DefaultComboBoxModel<Dipendente>();
			String query = "SELECT * FROM dipendente where TipologiaDipendente = 2;";
			Dipendente res = new Dipendente();
			PreparedStatement ps;
			conn=DBManager.startConnection();
			try {
				ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Dipendente d = new Dipendente();
					d.setIdDipendente(rs.getInt("idDipendente"));
					d.setNome(rs.getString("nome"));
					d.setCognome(rs.getString("cognome"));
					d.setCellulare(rs.getString("cellulare"));
					d.setSesso(rs.getString("sesso"));
					d.setTipologiaDipendente(rs.getInt("tipologiaDipendente"));
			
					risultato.addElement(d);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBManager.closeConnection();
			return risultato;

		}	
	
	
	
	
	public Vector<Dipendente> getAllAllenatore(){
		String query = "SELECT * FROM dipendente where TipologiaDipendente = 2";
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




