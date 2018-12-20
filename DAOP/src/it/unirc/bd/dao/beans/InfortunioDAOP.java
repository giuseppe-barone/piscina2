package it.unirc.bd.dao.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

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
	protected Infortunio recordToInfortunio(ResultSet rs) throws SQLException {
		Infortunio i = new Infortunio();
		i.setIdInfortunio(rs.getInt("idInfortunio"));
		i.setData(rs.getDate("data"));
		i.setGiorniSosta(rs.getInt("giornidiRiposo"));
		i.setGravita(rs.getInt("Gravita"));
		i.setMatricolaFIN(rs.getInt("matricolaFIN"));
		return i;
	}
	
	public Vector<Infortunio> getAll() {
		String query = "SELECT * FROM infortunio";
		Vector<Infortunio> list = new Vector<Infortunio>();
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Infortunio res=null;
			while(rs.next()){
				list.add(recordToInfortunio(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return list;
	} 
}
