package it.unirc.bd.dao.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.unirc.bd.dao.utils.DBManager;

public class PartecipazioneDAOP {
	private static Connection conn=null;
	IscrittoDAOP iDAOP=new IscrittoDAOP();
	EventoDAOP eDAOP=new EventoDAOP();
	
	
	public boolean salvaPartecipazione(Partecipazione p) {
		String query = "INSERT INTO partecipazione (`idEvento`, `MatricolaFin`, `Posizione`,`Categoria`) VALUES ( ?, ?, ?,?)";
		boolean esito = false;
		int idEvento=p.getIdEvento();
		int Matricola=p.getMatricolaFin();
		int posizione=p.getPosizione();
		String categoria=p.getCategoria();
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);	
			ps.setInt(1, idEvento);
			ps.setInt(2, Matricola);
			ps.setInt(3,posizione);
			ps.setString(4,categoria);
			int tmp = ps.executeUpdate();
			if(tmp==1)
				esito=true;
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}

	//ASSEMBLAGGIO DELLE INFORMAZIONI UTILE ALLA VISUALIZZAZIONE
		public Vector<String[]> assembler(Vector<Partecipazione> v) {
			Vector<String[]> lista=new Vector<String[]>();
			for(Partecipazione p:v) {
				Iscritto i =new Iscritto();
				Evento e=new Evento();
				String[] stringa=new String[6];
				i=iDAOP.getAtleta(p.getMatricolaFin());
				e=eDAOP.getEventoId(p.getIdEvento());
				stringa[0]=e.getTipo();
				stringa[1]=e.getData().toString();
				stringa[2]=i.getNome()+" "+i.getCognome();
				stringa[3]= Integer.toString(i.getMatricolaFIN());
				stringa[4]=i.CalcoloCategoria(i);
				switch (p.getPosizione()) {
				case 0:
					stringa[5]="N/D";
					break;
				case 1:
					stringa[5]="1°";
					break;
				case 2:
					stringa[5]="2°";
					break;
				case 3:
					stringa[5]="3°";
					break;
				case 4:
					stringa[5]="ALTRO";
					break;
				}
				lista.add(stringa);
				System.out.println("STRINGA AGGIUNTA");
				System.out.println(stringa);
			}
			return lista;
		}
	
		
		
		
		//ricerca partecipazioni tutti 
		//CERCA TUTTE LE PARTECIAPZIONI
		public Vector<Partecipazione> getTuttePartecipazioni() throws SQLException {
			String query = "SELECT * FROM partecipazione;";
			PreparedStatement ps;
			Vector<Partecipazione> lista=new Vector<Partecipazione>();
			conn=DBManager.startConnection();
			try {
				ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Partecipazione p=new Partecipazione();
					p.setCategoria(rs.getString("Categoria"));
					p.setIdEvento(rs.getInt("idEvento"));
					p.setMatricolaFin(rs.getInt("MatricolaFin"));
					p.setPosizione(rs.getInt("Posizione"));
					lista.add(p);
					System.out.println("STRINGA AGGIUNTA");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBManager.closeConnection();	
			return lista;
		}
		
		
		
		
		
		
		
		
		
}