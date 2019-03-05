package it.unirc.bd.dao.beans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

import it.unirc.bd.dao.utils.DBManager;


public class DipendenteDAOP {
	private static Connection conn = null;
	//----------------INSERISCI DIPENDENTE----------------------
	public boolean salvaDipendente(Dipendente d){
		String query = "INSERT INTO `piscina`.`dipendente` (`Nome`, `Cognome`, `Cellulare`, `Sesso`, `TipologiaDipendente`) VALUES (?, ?, ?, ?, ?);";
		boolean esito=false;
		conn=DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, d.getNome() );
			ps.setString(2, d.getCognome());
			ps.setString(3, d.getCellulare());
			ps.setString(4, d.getSesso());
			ps.setInt(5, d.getTipologiaDipendente());
			int tmp=ps.executeUpdate();
			if (tmp==1)
				esito=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}

	//----RESTITUZIONE DI TUTTI GLI EVENTI----PER LA COMBOBOX ----PROVA CON OGETTO DI TIPO EVENTO----FUNZIONA
		public DefaultComboBoxModel<Dipendente> getAllenatorecb(){
			DefaultComboBoxModel<Dipendente> risultato = new DefaultComboBoxModel<Dipendente>();
			String query = "SELECT * FROM dipendente where TipologiaDipendente = 2;";
			Dipendente res = new Dipendente();
			PreparedStatement ps;
			conn=DBManager.startConnection();
			try {
				ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Dipendente d = new Dipendente();
					d.setIdDipendente(rs.getInt("idDipendente"));
					d.setNome(rs.getString("nome"));
					d.setCognome(rs.getString("cognome"));
					d.setCellulare(rs.getString("cellulare"));
					d.setSesso(rs.getString("sesso"));
					d.setTipologiaDipendente(rs.getInt("tipologiaDipendente"));
			
					risultato.addElement(d);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBManager.closeConnection();
			return risultato;

		}	
	
	
	
	
	public Vector<Dipendente> getAllAllenatore(){
		String query = "SELECT * FROM dipendente where TipologiaDipendente = 2";
		Vector<Dipendente> list = new Vector<Dipendente>();
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Dipendente res=null;
			while(rs.next()){
				list.add(recordToDipendente(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return list;
	}
	
	
	
	
	

	public Vector<Dipendente> getAll(){
		String query = "SELECT * FROM dipendente";
		Vector<Dipendente> list = new Vector<Dipendente>();
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Dipendente res=null;
			while(rs.next()){
				list.add(recordToDipendente(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return list;
	}



	protected Dipendente recordToDipendente(ResultSet rs) throws SQLException {
		Dipendente d = new Dipendente();
		d.setIdDipendente(rs.getInt("idDipendente"));
		d.setNome(rs.getString("nome"));
		d.setCognome(rs.getString("cognome"));
		d.setCellulare(rs.getString("cellulare"));
		d.setSesso(rs.getString("sesso"));
		d.setTipologiaDipendente(rs.getInt("tipologiaDipendente"));
		return d;
	}
	
	//--------RICERCA PER SESSO---------
	public Vector<Dipendente> RicercaPerSesso(String sesso) {
		Vector<Dipendente> risultato=new Vector<Dipendente>();
		String query = "SELECT * FROM dipendente WHERE Sesso = ?";
		Dipendente res = null;
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, sesso);
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
	//----RICERCA PER NOME/COGNOME----
			public Vector<Dipendente> RicercaPerNomeCognome(String nome, String cognome, boolean isNome, boolean isCognome){
					Vector<Dipendente> risultato=new Vector<Dipendente>();
					boolean completo=false;
					String query = "SELECT * FROM dipendente;";
					if (isNome && isCognome )
						query="SELECT * FROM dipendente where Nome=? and Cognome=?";
					else if (!isNome && isCognome)
						query="SELECT * FROM dipendente where Cognome=?";
					else if (isNome && !isCognome) {
						query="SELECT * FROM dipendente where Nome=?";
					}
					ResultSet rs=null;
					Dipendente res;
					PreparedStatement ps;
					conn=DBManager.startConnection();
					try {
						
						
						if (isNome && isCognome ) {
							ps = conn.prepareStatement(query);
							ps.setString(1, nome);
							ps.setString(2,cognome);
							rs = ps.executeQuery();
						}
						else if (!isNome && isCognome) {
							ps = conn.prepareStatement(query);
							ps.setString(1, cognome);
							rs = ps.executeQuery();
						}
						else if (isNome && !isCognome) {
							ps = conn.prepareStatement(query);
							ps.setString(1, nome);
							rs = ps.executeQuery();
						}
						
						
						while(rs.next()){

							res=new Dipendente();
							res.setIdDipendente(rs.getInt("idIscritto"));
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
					
					
					System.out.println("RISULTATI TROVATI: "+risultato.size());
					System.out.println("_______________________________________________________________________________________________");
					System.out.println(risultato.toString());
					return risultato;

				}
			//------RICERCA PER TIPOLOGIA------
			public Vector<Dipendente> RicercaPerTipologia(String tipo) {
				Vector<Dipendente> risultato= new Vector<Dipendente>();
				String query = "SELECT * FROM piscina.dipendente;";
				Dipendente res;
				PreparedStatement ps;
				conn=DBManager.startConnection();
				try {
					ps = conn.prepareStatement(query);
					//ps.setString(1, tipo);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						res=new Dipendente();
						res.setIdDipendente(rs.getInt("idDipendente"));
						res.setNome( rs.getString("Nome") );
						res.setCognome(rs.getString("Cognome"));
						res.setCellulare( rs.getString("Cellulare") );
						res.setSesso( rs.getString("Sesso") );
						risultato.add(res);
				}}
				catch (SQLException e) {
					e.printStackTrace();
				}
				DBManager.closeConnection();
				System.out.println(risultato.toString());
				return risultato;
			}
			//-----------------RICERCA COMPOSTA -------------------
			public Vector<Dipendente> RicercaComposta(String Nome, String Cognome, String Sesso) {
				String nome ="";
				String cognome ="";
				String sesso ="";
				if (Nome!=null)
				nome=" AND Nome='" + Nome + "' " ;
				if (Cognome!=null)
				cognome=" AND Cognome='" + Cognome + "' " ;
				if (Sesso!=null)
				sesso=" AND Sesso='" + Sesso + "' " ;
				
				System.out.println(nome+cognome+sesso);
				Vector<Dipendente> risultato=new Vector<Dipendente>();
				String query = "SELECT * FROM piscina.dipendente where idDipendente is not null "+nome +cognome+sesso+" ;";
				Dipendente res = null;
				PreparedStatement ps;
				conn=DBManager.startConnection();
				try {
					ps = conn.prepareStatement(query);
					System.out.println(query);
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
		}




