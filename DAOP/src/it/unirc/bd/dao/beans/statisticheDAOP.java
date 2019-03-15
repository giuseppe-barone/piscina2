package it.unirc.bd.dao.beans;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.jfree.data.category.DefaultCategoryDataset;

import it.unirc.bd.dao.utils.DBManager;
public class statisticheDAOP {
	private Connection conn = null;



	public DefaultCategoryDataset getTuttiInfortunioAtleta(DefaultCategoryDataset dataset, Iscritto i) {
		String query = "SELECT COUNT(\"Gravita\")as Numero, Gravita from infortunio where infortunio.MatricolaFin=? group by Gravita;";
		int ID =i.getMatricolaFIN();
		PreparedStatement ps;

		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, ID);
			ResultSet rs = ps.executeQuery();
			int contatore=0;
			while(rs.next()){
				int lieve=0;
				int medio=0;
				int grave=0;
				if(rs.getString("Gravita").equals("1"))
					lieve=rs.getInt("Numero");
				if (rs.getString("Gravita").equals("2"))
					medio=rs.getInt("Numero");
				if (rs.getString("Gravita").equals("3"))
					medio=rs.getInt("Grave");
				dataset.addValue(lieve, i.getNome()+" "+i.getCognome(), "Lieve");
		        dataset.addValue(medio, i.getNome()+" "+i.getCognome(), "Medio");
		        dataset.addValue(grave, i.getNome()+" "+i.getCognome(), "Grave");
				System.out.println("DATASET AGGIUNTA");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();


		return dataset;
	} 


}
