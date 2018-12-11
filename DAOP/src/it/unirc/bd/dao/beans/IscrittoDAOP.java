package it.unirc.bd.dao.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unirc.bd.dao.utils.DBManager;

public class IscrittoDAOP {
	private Connection conn=null;
	public boolean salvaIscritto(Iscritto i) {
		String query = "INSERT INTO iscritto VALUES (?,?,?,?,?)";
		boolean esito=false;
		conn=DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, i.getIdIscritto());
			ps.setString(2, i.getNome() );
			ps.setString(3, i.getCognome());
			ps.setString(4, i.getSesso());
			ps.setInt(5, i.getEta());
			int tmp=ps.executeUpdate();
			if (tmp==1)
				esito=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
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
				risultato = true;
			else
				risultato = false;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return risultato;
	}
}

