package it.unirc.bd.dao.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import java.util.Vector;

import it.unirc.bd.dao.utils.DBManager;






public class InfortunioDAOP {
	private Connection conn = null;

	public boolean salva(Infortunio i) {
		String query = "INSERT INTO `piscina`.`infortunio` (`Data`, `GiorniDiRiposo`, `Gravita`, `MatricolaFin`) VALUES (?, ?, ?, ?);";
		boolean esito=false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDate(1, i.getData());
			ps.setInt(2, i.getGiorniSosta());
			ps.setInt(3, i.getGravita());
			ps.setInt(4, i.getMatricolaFIN());
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
	
	
	//-----------------RICERCA PER MatricolaFin -------------------
			public LinkedList<String[]> getIscrittoId(int ID) throws SQLException {
				String query = "SELECT iscritto.Nome, iscritto.Cognome,iscritto.MatricolaFin, infortunio.Gravita,  infortunio.Data \r\n" + 
						"FROM iscritto JOIN infortunio\r\n" + 
						"ON iscritto.MatricolaFin = infortunio.MatricolaFin where iscritto.MatricolaFin=?;";
				Iscritto res = null;
				PreparedStatement ps;
				LinkedList<String[]> lista=new LinkedList<String[]>();
				String[] stringa=new String[5]; 
				conn=DBManager.startConnection();
				try {
					ps = conn.prepareStatement(query);
					ps.setInt(1, ID);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						stringa[0]=rs.getString("Nome");
						stringa[1]=rs.getString("Cognome");
						stringa[2]=Integer.toString(rs.getInt("MatricolaFin"));
						stringa[3]=Integer.toString(rs.getInt("Gravita"));
						stringa[4]=rs.getDate("Data").toString();
						//for (int i =0;i<stringa.length;i++)
						//	System.out.println(stringa[i]+" ");
						lista.add(stringa);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				DBManager.closeConnection();
			//	System.out.println(list.toString());
				

				
				
				return lista;
			} 
		
	
	
	
}
