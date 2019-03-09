package it.unirc.bd.dao.beans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;


import java.util.Vector;

import it.unirc.bd.dao.utils.DBManager;






public class InfortunioDAOP {
	private Iscritto iscritto=new Iscritto();
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
	
	public boolean modifica(Infortunio i) {
		String query = "UPDATE `piscina`.`infortunio` SET `Data`=?, `GiorniDiRiposo` = ?, `Gravita` = ?, `MatricolaFin` = ? WHERE (`idInfortunio` = ?);";
		boolean esito=false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDate(1, i.getData());
			ps.setInt(2, i.getGiorniSosta());
			ps.setInt(3, i.getGravita());
			ps.setInt(4, i.getMatricolaFIN());
			ps.setInt(5,i.getIdInfortunio());
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
	
	
	public Infortunio getIDInfo(int ID) {
		String query = "SELECT * FROM infortunio where idInfortunio=?";
		Infortunio infortunio=new Infortunio();
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, ID);
			ResultSet rs = ps.executeQuery();
			Infortunio res=null;
			while(rs.next()){
				infortunio.setData(rs.getDate("Data"));
				infortunio.setIdInfortunio(rs.getInt("idInfortunio"));
				infortunio.setGiorniSosta(rs.getInt("GiorniDiRiposo"));
				infortunio.setGravita(rs.getInt("Gravita"));
				infortunio.setMatricolaFIN(rs.getInt("MatricolaFin"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return infortunio;
	} 
	
	
	
	//------------------OPERAZIONI CHE COMPRENDONO ANCHE ISCRITTO--------------------------
	
	
	//-----------------RICERCA PER MatricolaFin -------------------
			public Vector<String[]> getIscrittoId(int ID) throws SQLException {
				String query = "SELECT iscritto.Nome, iscritto.Cognome,iscritto.MatricolaFin, infortunio.idInfortunio, infortunio.Gravita,  infortunio.Data \r\n" + 
						"FROM iscritto JOIN infortunio\r\n" + 
						"ON iscritto.MatricolaFin = infortunio.MatricolaFin where iscritto.MatricolaFin=?;";
				Iscritto res = null;
				PreparedStatement ps;
				Vector<String[]> lista=new Vector<String[]>();
 
				conn=DBManager.startConnection();
				try {
					ps = conn.prepareStatement(query);
					ps.setInt(1, ID);
					ResultSet rs = ps.executeQuery();
					int contatore=0;
					while(rs.next()){
						String[] stringa=new String[6];
						contatore++;
						stringa[0]=rs.getString("Nome");
						//System.out.println(stringa[0]);
						stringa[1]=rs.getString("Cognome");
						//System.out.println(stringa[1]);
						stringa[2]=Integer.toString(rs.getInt("MatricolaFin"));
						//System.out.println(stringa[2]);
						switch (rs.getInt("Gravita")) {
						case 1:
							stringa[3]="Lieve";
							break;
						case 2:
							stringa[3]="Media";
							break;
						case 3:
							stringa[3]="Elevata";
							break;
						}
						//stringa[3]=Integer.toString(rs.getInt("Gravita"));
						//System.out.println(stringa[3]);
						stringa[4]=rs.getDate("Data").toString();
						//System.out.println(stringa[4]);
						stringa[5]=Integer.toString(rs.getInt("idInfortunio"));
						//System.out.println(contatore);
						/*for (int i =0;i<stringa.length;i++)
							System.out.println(stringa[i]+" ");*/
						lista.add(stringa);
						System.out.println("STRINGA AGGIUNTA");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				DBManager.closeConnection();
			
				
			/*	System.out.println("PROVA DI SCRITTURA-------------INIZIO------------");
				for (String[] s : lista) {
					for (int x=0;x<5;x++)
						System.out.println(s[x]);
				}
				System.out.println("PROVA DI SCRITTURA-------------FINE--------------");
				*/
				return lista;
			} 
		
	
	
			//-----------------RICERCA PER Gravità -------------------
			public Vector<String[]> getGravita(int Gravita) throws SQLException {
				String query = "SELECT iscritto.Nome, iscritto.Cognome,iscritto.MatricolaFin, infortunio.idInfortunio, infortunio.Gravita,  infortunio.Data \r\n" + 
						"FROM iscritto JOIN infortunio\r\n" + 
						"ON iscritto.MatricolaFin = infortunio.MatricolaFin where infortunio.Gravita=?";
				Iscritto res = null;
				PreparedStatement ps;
				Vector<String[]> lista=new Vector<String[]>();
 
				conn=DBManager.startConnection();
				try {
					ps = conn.prepareStatement(query);
					ps.setInt(1, Gravita);
					ResultSet rs = ps.executeQuery();
					int contatore=0;
					while(rs.next()){
						String[] stringa=new String[6];
						contatore++;
						stringa[0]=rs.getString("Nome");
						//System.out.println(stringa[0]);
						stringa[1]=rs.getString("Cognome");
						//System.out.println(stringa[1]);
						stringa[2]=Integer.toString(rs.getInt("MatricolaFin"));
						//System.out.println(stringa[2]);
						switch (rs.getInt("Gravita")) {
						case 1:
							stringa[3]="Lieve";
							break;
						case 2:
							stringa[3]="Media";
							break;
						case 3:
							stringa[3]="Elevata";
							break;
						}
						//stringa[3]=Integer.toString(rs.getInt("Gravita"));
						//System.out.println(stringa[3]);
						stringa[4]=rs.getDate("Data").toString();
						//System.out.println(stringa[4]);
						stringa[5]=Integer.toString(rs.getInt("idInfortunio"));
						//System.out.println(contatore);
						/*for (int i =0;i<stringa.length;i++)
							System.out.println(stringa[i]+" ");*/
						lista.add(stringa);
						System.out.println("STRINGA AGGIUNTA");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				DBManager.closeConnection();				
				/*System.out.println("PROVA DI SCRITTURA-------------INIZIO------------");
				for (String[] s : lista) {
					for (int x=0;x<5;x++)
						System.out.println(s[x]);
				}
				System.out.println("PROVA DI SCRITTURA-------------FINE--------------");
				*/
				return lista;
			} 
			
			
			//-----------------RICERCA Tutti -------------------
			public Vector<String[]> getTutti() throws SQLException {
				String query = "SELECT iscritto.Nome, iscritto.Cognome,iscritto.MatricolaFin, infortunio.idInfortunio, infortunio.Gravita,  infortunio.Data \r\n" + 
						"FROM iscritto JOIN infortunio\r\n" + 
						"ON iscritto.MatricolaFin = infortunio.MatricolaFin ;";
				Iscritto res = null;
				PreparedStatement ps;
				Vector<String[]> lista=new Vector<String[]>();
				conn=DBManager.startConnection();
				try {
					ps = conn.prepareStatement(query);
				
					ResultSet rs = ps.executeQuery();
					int contatore=0;
					while(rs.next()){
						String[] stringa=new String[6];
						contatore++;
						stringa[0]=rs.getString("Nome");
						//System.out.println(stringa[0]);
						stringa[1]=rs.getString("Cognome");
						//System.out.println(stringa[1]);
						stringa[2]=Integer.toString(rs.getInt("MatricolaFin"));
						//System.out.println(stringa[2]);
						switch (rs.getInt("Gravita")) {
						case 1:
							stringa[3]="Lieve";
							break;
						case 2:
							stringa[3]="Media";
							break;
						case 3:
							stringa[3]="Elevata";
							break;
						}
						//stringa[3]=Integer.toString(rs.getInt("Gravita"));
						//System.out.println(stringa[3]);
						stringa[4]=rs.getDate("Data").toString();
						//System.out.println(stringa[4]);
						//System.out.println(contatore);
						stringa[5]=Integer.toString(rs.getInt("idInfortunio"));
						/*for (int i =0;i<stringa.length;i++)
							System.out.println(stringa[i]+" ");*/
						lista.add(stringa);
						System.out.println("STRINGA AGGIUNTA");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				DBManager.closeConnection();				
				/*System.out.println("PROVA DI SCRITTURA-------------INIZIO------------");
				for (String[] s : lista) {
					for (int x=0;x<5;x++)
						System.out.println(s[x]);
				}
				System.out.println("PROVA DI SCRITTURA-------------FINE--------------");
				*/
				return lista;
			} 
			
			
			//-----------------RICERCA Categoria -------------------
			public Vector<String[]> getCategoria(String Categoria) throws SQLException {
				String query = "SELECT iscritto.Nome, iscritto.Cognome,iscritto.MatricolaFin, iscritto.Sesso, iscritto.DataDiNascita, infortunio.idInfortunio, infortunio.Gravita,  infortunio.Data \r\n" + 
						"FROM iscritto JOIN infortunio\r\n" + 
						"ON iscritto.MatricolaFin = infortunio.MatricolaFin  ;";
				Iscritto res = null;
				PreparedStatement ps;
				Vector<String[]> lista=new Vector<String[]>();
 
				conn=DBManager.startConnection();
				try {
					ps = conn.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					int contatore=0;
					while(rs.next()){
						String[] stringa=new String[6];
						contatore++;
						stringa[0]=rs.getString("Nome");
						//System.out.println(stringa[0]);
						stringa[1]=rs.getString("Cognome");
						//System.out.println(stringa[1]);
						stringa[2]=Integer.toString(rs.getInt("MatricolaFin"));
						//System.out.println(stringa[2]);
						switch (rs.getInt("Gravita")) {
						case 1:
							stringa[3]="Lieve";
							break;
						case 2:
							stringa[3]="Media";
							break;
						case 3:
							stringa[3]="Elevata";
							break;
						}
						//stringa[3]=Integer.toString(rs.getInt("Gravita"));
						//System.out.println(stringa[3]);
						stringa[4]=rs.getDate("Data").toString();
						//System.out.println(stringa[4]);
						//System.out.println(contatore);
						stringa[5]=Integer.toString(rs.getInt("idInfortunio"));
						/*for (int i =0;i<stringa.length;i++)
							System.out.println(stringa[i]+" ");*/
						if (CalcoloCategoria(rs.getString("Sesso"), rs.getDate("DataDiNascita")).equals(Categoria))
							lista.add(stringa);
						System.out.println("STRINGA AGGIUNTA");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				DBManager.closeConnection();				
				/*System.out.println("PROVA DI SCRITTURA-------------INIZIO------------");
				for (String[] s : lista) {
					for (int x=0;x<5;x++)
						System.out.println(s[x]);
				}
				System.out.println("PROVA DI SCRITTURA-------------FINE--------------");
				*/
				return lista;
			} 
			
			//CALCOLO CATEGORIA
			public String CalcoloCategoria(String sesso, Date data) {
				String risultato = null;
				int eta = 0;
				boolean isMaschio;
				if (sesso.equals("Maschio"))
					isMaschio=true;
				else
					isMaschio=false;
				//--------------CODICE PER IL CALCOLO DELL'ETA DA INSERIRE A PARTE PER PEPPE----------------
				LocalDate corrente=LocalDate.now();
				Date nascita=data;
				LocalDate LNascita=nascita.toLocalDate();
				//System.out.println(LNascita.toString());

				//System.out.println(corrente.toString());
				if ((corrente != null) && (nascita != null)) {
		          // System.out.println(Period.between(LNascita, corrente).getYears());
		           eta=Period.between(LNascita, corrente).getYears();
		           
		        }
				if (isMaschio) {	//SE è MASCHIO
					if (eta<14)
						risultato="Esordienti";
					else if (eta<17)
						risultato="Ragazzi";
					else if (eta<21)
						risultato="Cadetti";
					else
						risultato="Seniores";
				}
				else {				//SE NON è MASCIO
					if (eta<13)
						risultato="Esordienti";
					else if (eta<15)
						risultato="Ragazzi";
					else if (eta<19)
						risultato="Cadetti";
					else
						risultato="Seniores";
				}
				return risultato;
			}
			
			
	
}
