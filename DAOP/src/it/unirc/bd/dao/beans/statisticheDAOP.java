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
	CorsoDAOP cDAOP = new CorsoDAOP();
	
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
	
	public DefaultCategoryDataset getInfortuniCorso(DefaultCategoryDataset dataset , Integer anno) {
		String query;
		String Anno="";
		if (anno!=null) {
			Anno="AND YEAR(Data) ="+ Integer.toString(anno);
		}
		PreparedStatement ps;
		Vector<Corso> vettore = cDAOP.getAll();
		for (Corso c: vettore){
		System.out.println(c.getTipo());
		conn=DBManager.startConnection();	
		int lieve=0;
		int medio=0;
		int grave=0;
		int ID =c.getIdCorso();
		query = "SELECT COUNT(\"Gravita\")as Numero, Gravita from infortunio where idInfortunio IS NOT NULL "+Anno+" AND MatricolaFin IN (SELECT MatricolaFin FROM iscritto where iscritto.idIscritto IN (SELECT frequenta.idIscritto from frequenta where frequenta.idCorso="+Integer.toString(ID)+")) group by Gravita;";
		try {
			ps = conn.prepareStatement(query);
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
		if (lieve!=0 || medio!=0 || grave!=0) {
			dataset.addValue(lieve, "Lieve",c.getTipo());
	        dataset.addValue(medio, "Medio" , c.getTipo());
	        dataset.addValue(grave, "Grave" , c.getTipo());
			System.out.println("DATASET AGGIUNTA");
		}
		else
			System.out.println("DATASET NON AGGIUNTA");
		

		}
		return dataset;
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
	 
	public DefaultCategoryDataset getInfortuniAllenatori(DefaultCategoryDataset dataset , Integer anno) {
		String query;
		String Anno="";
		if (anno!=null) {
			Anno="AND YEAR(Data) ="+ Integer.toString(anno);
		}
		PreparedStatement ps;
		Vector<Dipendente> vettore = getAllenatorePrima();
		for (Dipendente d: vettore){
		System.out.println(d.getNome()+" "+d.getCognome());
		conn=DBManager.startConnection();	
		int lieve=0;
		int medio=0;
		int grave=0;
		int ID =d.getIdDipendente();
		query = "SELECT COUNT(\"Gravita\")as Numero, Gravita from infortunio where idInfortunio IS NOT NULL "+Anno+" AND MatricolaFin IN (SELECT MatricolaFin FROM iscritto where iscritto.idIscritto IN (SELECT frequenta.idIscritto from frequenta where frequenta.idCorso IN(SELECT idCorso FROM piscina.corso where Allenatore1= "+Integer.toString(ID)+ " ))) group by Gravita;";
		try {
			ps = conn.prepareStatement(query);
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
		if (lieve!=0 || medio!=0 || grave!=0) {
			dataset.addValue(lieve, "Lieve", d.getNome()+" "+d.getCognome());
	        dataset.addValue(medio, "Medio" , d.getNome()+" "+d.getCognome());
	        dataset.addValue(grave, "Grave" , d.getNome()+" "+d.getCognome());
			System.out.println("DATASET AGGIUNTA");
		}
		else
			System.out.println("DATASET NON AGGIUNTA");
		

		}
		return dataset;
	}
	
	
	
	
	
	//PARTE RELATIVA ALLE GARE
	public DefaultCategoryDataset getPosizioneCorsi(DefaultCategoryDataset dataset , Integer anno) {
		String query;
		String Anno="";
		if (anno!=null) {
			Anno="AND YEAR(Data) ="+ Integer.toString(anno);
		}
		PreparedStatement ps;
		Vector<Corso> vettore = cDAOP.getAll();
		for (Corso c: vettore){
		System.out.println(c.getTipo());
		conn=DBManager.startConnection();	
		int Primo=0;
		int Secondo=0;
		int Terzo=0;
		int NonPodio=0;
		String tipo =c.getTipo();
		query = "SELECT COUNT(\"Posizione\")as Numero, Posizione FROM piscina.partecipazione where partecipazione.Posizione!=0 "+Anno+" AND partecipazione.idEvento in(SELECT evento.idEvento from evento where tipo='"+tipo+"') group by partecipazione.Posizione;";
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){

				if(rs.getString("Posizione").equals("1"))
					Primo=rs.getInt("Numero");
				if (rs.getString("Posizione").equals("2"))
					Secondo=rs.getInt("Numero");
				if (rs.getString("Posizione").equals("3"))
					Terzo=rs.getInt("Numero");
				if (rs.getString("Posizione").equals("4"))
					NonPodio=rs.getInt("Numero");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		System.out.println("Primo: "+Integer.toString(Primo));
		System.out.println("Secondo: "+Integer.toString(Secondo));
		System.out.println("Terzo: "+Integer.toString(Terzo));
		System.out.println(">3: "+Integer.toString(NonPodio));
		if (Primo!=0 || Secondo!=0 || Terzo!=0 || NonPodio!=0) {
			dataset.addValue(Primo, "1",c.getTipo());
	        dataset.addValue(Secondo, "2" , c.getTipo());
	        dataset.addValue(Terzo, "3" , c.getTipo());
	        dataset.addValue(NonPodio, ">3" , c.getTipo());
			System.out.println("DATASET AGGIUNTA");
		}
		else
			System.out.println("DATASET NON AGGIUNTA");
		

		}
		return dataset;
	}
	
	
	
	public DefaultCategoryDataset getPosizioneAllenatori(DefaultCategoryDataset dataset , Integer anno) { //DA AGGIUNGERE L'ANNO
		String query;
		String Anno="";
		if (anno!=null) {
			Anno="AND YEAR(Data) ="+ Integer.toString(anno);
		}
		PreparedStatement ps;
		Vector<Dipendente> vettore = getAllenatorePrima();
		for (Dipendente d: vettore){
		System.out.println(d.getNome()+" "+d.getCognome());
		conn=DBManager.startConnection();	
		int Primo=0;
		int Secondo=0;
		int Terzo=0;
		int NonPodio=0;
		int ID =d.getIdDipendente();
		query = "SELECT  COUNT(\"Posizione\")as Numero, partecipazione.Posizione  FROM piscina.partecipazione \r\n" + 
				"where partecipazione.idEvento IN(\r\n" + 
				"SELECT distinct evento.idEvento from evento\r\n" + 
				"	WHERE evento.Tipo IN (\r\n" + 
				"	select corso.tipo from corso\r\n" + 
				"	join dipendente where \r\n" + 
				"	corso.Allenatore1 ="+ID+"    )\r\n" + 
				")\r\n" + 
				"group by partecipazione.posizione;";
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){

				if(rs.getString("Posizione").equals("1"))
					Primo=rs.getInt("Numero");
				if (rs.getString("Posizione").equals("2"))
					Secondo=rs.getInt("Numero");
				if (rs.getString("Posizione").equals("3"))
					Terzo=rs.getInt("Numero");
				if (rs.getString("Posizione").equals("4"))
					NonPodio=rs.getInt("Numero");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();

		System.out.println("Primo: "+Integer.toString(Primo));
		System.out.println("Secondo: "+Integer.toString(Secondo));
		System.out.println("Terzo: "+Integer.toString(Terzo));
		System.out.println(">3: "+Integer.toString(NonPodio));
		if (Primo!=0 || Secondo!=0 || Terzo!=0 || NonPodio!=0) {
			dataset.addValue(Primo, "1", d.getNome()+" "+d.getCognome());
	        dataset.addValue(Secondo, "2" , d.getNome()+" "+d.getCognome());
	        dataset.addValue(Terzo, "3" , d.getNome()+" "+d.getCognome());
	        dataset.addValue(NonPodio, ">3" , d.getNome()+" "+d.getCognome());
			System.out.println("DATASET AGGIUNTA");
		}
		else
			System.out.println("DATASET NON AGGIUNTA");
		

		}
		return dataset;
	}
	
	
	
	public DefaultCategoryDataset getTuttePosizioniAtleta(DefaultCategoryDataset dataset, Iscritto i) {
		String query = "SELECT  COUNT(\"Posizione\")as Numero, partecipazione.Posizione  FROM piscina.partecipazione \r\n" + 
				"where partecipazione.idEvento IN(\r\n" + 
				"SELECT distinct evento.idEvento from evento\r\n" + 
				"	WHERE evento.Tipo IN (\r\n" + 
				"    select tipo from corso join frequenta on corso.idCorso=frequenta.idCorso join iscritto on frequenta.idIscritto=iscritto.idIscritto where iscritto.idIscritto=? \r\n" + 
				"    )\r\n" + 
				")\r\n" + 
				"group by partecipazione.posizione;";
		int ID =i.getMatricolaFIN();
		PreparedStatement ps;
		int Primo=0;
		int Secondo=0;
		int Terzo=0;
		int NonPodio=0;

		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, ID);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){

				if(rs.getString("Posizione").equals("1"))
					Primo=rs.getInt("Numero");
				if (rs.getString("Posizione").equals("2"))
					Secondo=rs.getInt("Numero");
				if (rs.getString("Posizione").equals("3"))
					Terzo=rs.getInt("Numero");
				if (rs.getString("Posizione").equals("4"))
					NonPodio=rs.getInt("Numero");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();

		System.out.println("Primo: "+Integer.toString(Primo));
		System.out.println("Secondo: "+Integer.toString(Secondo));
		System.out.println("Terzo: "+Integer.toString(Terzo));
		System.out.println(">3: "+Integer.toString(NonPodio));
		dataset.addValue(Primo, i.getNome()+" "+i.getCognome(), "1");
        dataset.addValue(Secondo, i.getNome()+" "+i.getCognome(), "2");
        dataset.addValue(Terzo, i.getNome()+" "+i.getCognome(), "3");
        dataset.addValue(NonPodio, i.getNome()+" "+i.getCognome(), ">3");
		System.out.println("DATASET AGGIUNTA");
		return dataset;
	} 
	
	
	
	
}
