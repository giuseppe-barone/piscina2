package it.unirc.bd.dao.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.unirc.bd.dao.utils.DBManager;

public class InfortunioDAOP {
	private Connection conn = null;

	public boolean salva(Infortunio i) {
		String query = "INSERT INTO infortunio VALUES(?,?,?,?,?)";
		boolean esito=false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, i.getIdInfortunio());
			ps.setDate(2, i.getData());
			ps.setInt(3, i.getGiorniSosta());
			ps.setInt(4, i.getGravita());
			ps.setInt(5, i.getMatricolaFIN());
			int tmp = ps.executeUpdate();
			if(tmp==1)
				esito=true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
}
