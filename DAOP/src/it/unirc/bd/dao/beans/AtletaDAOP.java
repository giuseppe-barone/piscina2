package it.unirc.bd.dao.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.unirc.bd.dao.utils.DBManager;

public class AtletaDAOP {
	private static Connection conn = null;
	 public boolean salvaAtleta(Atleta a) {
		 String query = "INSERT INTO atleta VALUES(?, ?, ?)";
		 boolean esito = false;
		 conn = DBManager.startConnection();
		 try {
			 PreparedStatement ps = conn.prepareStatement(query);
			 ps.setInt(1, a.getMatricolaFIN());
			 ps.setString(2, a.getCategoria());
			 ps.setInt(3, a.getIdIscritto());
			 int tmp = ps.executeUpdate();
			 if(tmp==1)
				 esito=true;
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
		 DBManager.closeConnection();
		 return esito;
	 }

}
