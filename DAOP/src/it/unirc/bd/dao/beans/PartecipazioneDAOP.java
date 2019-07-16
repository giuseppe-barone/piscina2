package it.unirc.bd.dao.beans;

import java.sql.Connection;
import java.sql.Date;
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
	
	
	
	
	//CERCA PARTECIPAZIONE
	public Partecipazione getPartecipazione(int idEvento, int MatricolaFin) {
		String query = "SELECT * FROM piscina.partecipazione where partecipazione.idEvento=? and partecipazione.MatricolaFin=?;";
		PreparedStatement ps;
		Partecipazione result=new Partecipazione();
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				result.setPosizione(rs.getInt("Posizione"));
				result.setMatricolaFin(rs.getInt("MatricolaFin"));
				result.setIdEvento(rs.getInt("idEvento"));
				result.setCategoria(rs.getString("Categoria"));
				System.out.println(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();	
		return result;
	}
	
	
	

/*	//ASSEMBLAGGIO DELLE INFORMAZIONI UTILE ALLA VISUALIZZAZIONE
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
	*/
		
		
		//CERCA TUTTE LE PARTECIAPZIONI DA AGGIORNARE
				public Vector<String[]> getPartecipazioniDaAggiornare() {
					String query = "SELECT evento.Data,evento.Tipo,evento.Livello,iscritto.Nome,iscritto.Cognome,iscritto.DataDiNascita ,partecipazione.Categoria\r\n" + 
							"from evento join partecipazione on evento.idEvento=partecipazione.idEvento\r\n" + 
							"	join iscritto on partecipazione.MatricolaFin=iscritto.MatricolaFin\r\n" + 
							"    where evento.Data<curdate() and partecipazione.Posizione=0;";
					PreparedStatement ps;
					Vector<String[]> lista=new Vector<String[]>();
					conn=DBManager.startConnection();
					try {
						ps = conn.prepareStatement(query);
						ResultSet rs = ps.executeQuery();
						while(rs.next()){
							String[] stringa=new String[7];
							stringa[0]=rs.getDate("Data").toString();
							stringa[1]=rs.getString("Tipo");
							stringa[2]=rs.getString("Livello");
							stringa[3]=rs.getString("Nome");
							stringa[4]=rs.getString("Cognome");
							stringa[5]=rs.getDate("DataDiNascita").toString();
							stringa[6]=rs.getString("Categoria");
							System.out.println(stringa);
							lista.add(stringa);
							System.out.println("STRINGA AGGIUNTA");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					DBManager.closeConnection();	
					return lista;
				}
				
				
				
				
				//CERCA TUTTE LE PARTECIAPZIONI
				public Vector<String[]> getPartecipazioniTutte() {
					String query = "SELECT  partecipazione.idEvento,partecipazione.MatricolaFin,evento.Data,evento.Tipo,evento.Livello,iscritto.Nome,iscritto.Cognome,iscritto.DataDiNascita ,partecipazione.Categoria\r\n" + 
							"from evento join partecipazione on evento.idEvento=partecipazione.idEvento\r\n" + 
							"	join iscritto on partecipazione.MatricolaFin=iscritto.MatricolaFin;";
					PreparedStatement ps;
					Vector<String[]> lista=new Vector<String[]>();
					conn=DBManager.startConnection();
					try {
						ps = conn.prepareStatement(query);
						ResultSet rs = ps.executeQuery();
						while(rs.next()){
							String[] stringa=new String[8];
							stringa[0]=rs.getDate("Data").toString();
							stringa[1]=rs.getString("Tipo");
							stringa[2]=rs.getString("Livello");
							stringa[3]=rs.getString("Nome");
							stringa[4]=rs.getString("Cognome");
							stringa[5]=rs.getDate("DataDiNascita").toString();
							stringa[6]=rs.getString("Categoria");
							stringa[7]=rs.getString("idEvento")+"/"+rs.getString("MatricolaFin");	//PASSO UN ELEMENTO ID CHE COMPRENDE L'ID DELL'EVENTO E LA MATRICOLA COSI DA IDENTIFICARE LA PARTECIPAZIONE ESSENDO TUTTI E DUE IDENTIFICATORI
							System.out.println(stringa);
							lista.add(stringa);
							System.out.println("STRINGA AGGIUNTA");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					DBManager.closeConnection();	
					return lista;
				}
		
		
				
				
				
				//CERCA TUTTE LE PARTECIAPZIONI PER DATA
				public Vector<String[]> getPartecipazioniData(Date data) {
					String query = "SELECT  partecipazione.idEvento,partecipazione.MatricolaFin,evento.Data,evento.Tipo,evento.Livello,iscritto.Nome,iscritto.Cognome,iscritto.DataDiNascita ,partecipazione.Categoria\r\n" + 
							"from evento join partecipazione on evento.idEvento=partecipazione.idEvento\r\n" + 
							"	join iscritto on partecipazione.MatricolaFin=iscritto.MatricolaFin\r\n" + 
							"    where evento.Data=?;";
					PreparedStatement ps;
					Vector<String[]> lista=new Vector<String[]>();
					conn=DBManager.startConnection();
					try {
						ps = conn.prepareStatement(query);
						
						ps.setDate(1, data);
						
						ResultSet rs = ps.executeQuery();
						while(rs.next()){
							String[] stringa=new String[8];
							stringa[0]=rs.getDate("Data").toString();
							stringa[1]=rs.getString("Tipo");
							stringa[2]=rs.getString("Livello");
							stringa[3]=rs.getString("Nome");
							stringa[4]=rs.getString("Cognome");
							stringa[5]=rs.getDate("DataDiNascita").toString();
							stringa[6]=rs.getString("Categoria");
							stringa[7]=rs.getString("idEvento")+"/"+rs.getString("MatricolaFin");	//PASSO UN ELEMENTO ID CHE COMPRENDE L'ID DELL'EVENTO E LA MATRICOLA COSI DA IDENTIFICARE LA PARTECIPAZIONE ESSENDO TUTTI E DUE IDENTIFICATORI
							System.out.println(stringa);
							lista.add(stringa);
							System.out.println("STRINGA AGGIUNTA");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					DBManager.closeConnection();	
					return lista;
				}
				
				
				
				
				
				//CERCA TUTTE LE PARTECIAPZIONI PER ATLETA
				public Vector<String[]> getPartecipazioniFromMatricola(int id) {
					String query = "SELECT  partecipazione.idEvento,partecipazione.MatricolaFin,evento.Data,evento.Tipo,evento.Livello,iscritto.Nome,iscritto.Cognome,iscritto.DataDiNascita ,partecipazione.Categoria\r\n" + 
							"from evento join partecipazione on evento.idEvento=partecipazione.idEvento\r\n" + 
							"	join iscritto on partecipazione.MatricolaFin=iscritto.MatricolaFin\r\n" + 
							"    where partecipazione.MatricolaFin=?;";
					PreparedStatement ps;
					Vector<String[]> lista=new Vector<String[]>();
					conn=DBManager.startConnection();
					try {
						ps = conn.prepareStatement(query);
						
						ps.setInt(1, id);
						
						ResultSet rs = ps.executeQuery();
						while(rs.next()){
							String[] stringa=new String[8];
							stringa[0]=rs.getDate("Data").toString();
							stringa[1]=rs.getString("Tipo");
							stringa[2]=rs.getString("Livello");
							stringa[3]=rs.getString("Nome");
							stringa[4]=rs.getString("Cognome");
							stringa[5]=rs.getDate("DataDiNascita").toString();
							stringa[6]=rs.getString("Categoria");
							stringa[7]=rs.getString("idEvento")+"/"+rs.getString("MatricolaFin");	//PASSO UN ELEMENTO ID CHE COMPRENDE L'ID DELL'EVENTO E LA MATRICOLA COSI DA IDENTIFICARE LA PARTECIPAZIONE ESSENDO TUTTI E DUE IDENTIFICATORI
							System.out.println(stringa);
							lista.add(stringa);
							System.out.println("STRINGA AGGIUNTA");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					DBManager.closeConnection();	
					return lista;
				}
				
				
				
				
				//CERCA TUTTE LE PARTECIAPZIONI PER TIPO
				public Vector<String[]> getPartecipazioniFromTipo(String tipo) {
					String query = "SELECT  partecipazione.idEvento,partecipazione.MatricolaFin,evento.Data,evento.Tipo,evento.Livello,iscritto.Nome,iscritto.Cognome,iscritto.DataDiNascita ,partecipazione.Categoria\r\n" + 
							"from evento join partecipazione on evento.idEvento=partecipazione.idEvento\r\n" + 
							"	join iscritto on partecipazione.MatricolaFin=iscritto.MatricolaFin\r\n" + 
							"    where evento.Tipo=?;";
					PreparedStatement ps;
					Vector<String[]> lista=new Vector<String[]>();
					conn=DBManager.startConnection();
					try {
						ps = conn.prepareStatement(query);
						
						ps.setString(1, tipo);
						
						ResultSet rs = ps.executeQuery();
						while(rs.next()){
							String[] stringa=new String[8];
							stringa[0]=rs.getDate("Data").toString();
							stringa[1]=rs.getString("Tipo");
							stringa[2]=rs.getString("Livello");
							stringa[3]=rs.getString("Nome");
							stringa[4]=rs.getString("Cognome");
							stringa[5]=rs.getDate("DataDiNascita").toString();
							stringa[6]=rs.getString("Categoria");
							stringa[7]=rs.getString("idEvento")+"/"+rs.getString("MatricolaFin");	//PASSO UN ELEMENTO ID CHE COMPRENDE L'ID DELL'EVENTO E LA MATRICOLA COSI DA IDENTIFICARE LA PARTECIPAZIONE ESSENDO TUTTI E DUE IDENTIFICATORI
							System.out.println(stringa);
							lista.add(stringa);
							System.out.println("STRINGA AGGIUNTA");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					DBManager.closeConnection();	
					return lista;
				}
				
				
				
		
		
		
		/*//ricerca partecipazioni tutti 
		//CERCA TUTTE LE PARTECIAPZIONI
		public Vector<Partecipazione> getTuttePartecipazioni() {
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
		}*/
		
		
		
		
		
		
		
		
		
}