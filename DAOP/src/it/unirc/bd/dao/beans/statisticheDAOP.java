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
	
	public Vector<Dipendente> getAllenatorePrima(){
		Vector<Dipendente> risultato=new Vector<Dipendente>();
		String query = "SELECT * FROM piscina.dipendente where dipendente.idDipendente IN (SELECT DISTINCT Allenatore1 FROM piscina.corso);";
		Dipendente res = null;
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				res=new Dipendente();
				res.setIdDipendente(rs.getInt("idDipendente"));
				res.setNome( rs.getString("Nome") );
				res.setCognome(rs.getString("Cognome"));
				res.setCellulare( rs.getString("Cellulare") );
				res.setSesso( rs.getString("Sesso") );
				risultato.add(res);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		System.out.println(risultato.toString());
		return risultato;
	}
	


	public DefaultCategoryDataset getTuttiInfortunioAtleta(DefaultCategoryDataset dataset, Iscritto i) {
		String query = "SELECT COUNT(\"Gravita\")as Numero, Gravita from infortunio where infortunio.MatricolaFin=? group by Gravita;";
		int ID =i.getMatricolaFIN();
		PreparedStatement ps;
		int lieve=0;
		int medio=0;
		int grave=0;

		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, ID);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){

				if(rs.getString("Gravita").equals("1"))
					lieve=rs.getInt("Numero");
				if (rs.getString("Gravita").equals("2"))
					medio=rs.getInt("Numero");
				if (rs.getString("Gravita").equals("3"))
					grave=rs.getInt("Numero");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();

		System.out.println("Lieve: "+Integer.toString(lieve));
		System.out.println("Medio: "+Integer.toString(medio));
		System.out.println("Grave: "+Integer.toString(grave));
		dataset.addValue(lieve, i.getNome()+" "+i.getCognome(), "Lieve");
        dataset.addValue(medio, i.getNome()+" "+i.getCognome(), "Medio");
        dataset.addValue(grave, i.getNome()+" "+i.getCognome(), "Grave");
		System.out.println("DATASET AGGIUNTA");
		return dataset;
	} 


	
	 
	public DefaultCategoryDataset getInfortuniAllenatori(DefaultCategoryDataset dataset ) {
		String query = "SELECT COUNT(\"Gravita\")as Numero, Gravita from infortunio where MatricolaFin IN (SELECT MatricolaFin FROM iscritto where iscritto.idIscritto IN (SELECT frequenta.idIscritto from frequenta where frequenta.idCorso=(SELECT idCorso FROM piscina.corso where Allenatore1=?))) group by Gravita;";
		PreparedStatement ps;
		Vector<Dipendente> vettore = getAllenatorePrima();
		conn=DBManager.startConnection();
		for (Dipendente d: vettore){
		int lieve=0;
		int medio=0;
		int grave=0;
		int ID =d.getIdDipendente();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, ID);
			ResultSet rs = ps.executeQuery();
			int contatore=0;
			while(rs.next()){

				if(rs.getString("Gravita").equals("1"))
					lieve=rs.getInt("Numero");
				if (rs.getString("Gravita").equals("2"))
					medio=rs.getInt("Numero");
				if (rs.getString("Gravita").equals("3"))
					grave=rs.getInt("Numero");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();

		System.out.println("Lieve: "+Integer.toString(lieve));
		System.out.println("Medio: "+Integer.toString(medio));
		System.out.println("Grave: "+Integer.toString(grave));
		dataset.addValue(lieve, d.getNome()+" "+d.getCognome(), "Lieve");
        dataset.addValue(medio, d.getNome()+" "+d.getCognome(), "Medio");
        dataset.addValue(grave, d.getNome()+" "+d.getCognome(), "Grave");
		System.out.println("DATASET AGGIUNTA");
		}
		return dataset;
	} 
	 
	
	
}
