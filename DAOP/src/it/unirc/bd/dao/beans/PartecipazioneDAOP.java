package it.unirc.bd.dao.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import it.unirc.bd.dao.utils.DBManager;

public class PartecipazioneDAOP {
	private static Connection conn=null;
	
	public boolean salvaEvento(Partecipazione p) {
		String query = "INSERT INTO partecipazione VALUES (?, ?, ?, ?)";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, p.getIdEvento());
			ps.setInt(2, p.getMatricolaFin());
			ps.setInt(3, p.getPosizione());
			ps.setString(4, p.getCategoria());
			int tmp = ps.executeUpdate();
			if(tmp==1)
				esito=true;
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	
	
	

}