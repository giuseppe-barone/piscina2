package it.unirc.bd.dao.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

import it.unirc.bd.dao.utils.DBManager;


public class CorsoDAOP {
	private static Connection conn = null;
	IscrittoDAOP iDAOP=new IscrittoDAOP();




	//----------------INSERISCI CORSO----------------------
	public boolean salvaCorso(Corso c){
		String query = "INSERT INTO `piscina`.`corso` (`Giorni`, `Ora`, `Tipo`, `Allenatore1`, `Allenatore2`) VALUES ( ?, ?, ?, ?, ?)";
		boolean esito=false;
		conn=DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, c.getGiorni().intValue() );
			ps.setInt(2, c.getOra().intValue());
			ps.setString(3, c.getTipo());
			ps.setInt(4, c.getAllenatore1().intValue());
			ps.setInt(5, c.getAllenatore2().intValue());
			System.out.println(query);
			int tmp=ps.executeUpdate();
			if (tmp==1)
				esito=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}



	//-----------------CONTROLLO DINAMICO ID CORSO -------------------RITORNA VERO SE ESISTE O NON è UN VALORE ACCETTABILE FALSO ALTRIMENTI
	/*public boolean ControlloDinamicoIdCorso(Integer ID) {
		boolean risultato =false;
		//----INSERIRE UN CONTROLLO CHE MI PERMETTA DI VEDERE SE L'OGETTO PASSATO è VUOTO E RESTITUISCA SUBITO UN VALORE
		//INSERISCO QUESTO CONTROLLO PRIMA DEL RESTO DEL CONDICE PERCHè SENNO PARTIREBBE UNA QUERY CON DEI VALORI INCOMPATIBILI
		//ANZICHE PASSARE AL METODO UN TIPO INT PASSARE AL METODO UN TIPO INTEGER (OGETTO) CHE PUò ESSERE ANCHE NULL
		if (ID==null || Integer.toString(ID.intValue()).equals("")) {
			risultato=true;
			return risultato;
		}						
		String query = "SELECT * FROM corso WHERE idCorso = ?";
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, ID.intValue());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				risultato=true;	//ESISTE UNA TUPLA CON QUELL'ID
			else
				risultato=false;//NON ESISTE UNA TUPLA CON QUELL'ID
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return risultato;
	}*/



	//-----------------CONTROLLO PRESENZA ALLENATORE -------------------RITORNA TRUE SE ESISTE ALMENO UN CORSO CHE è TENUTO (ALLENATORE1 O ALLENATORE2) DALL'ALLENATORE PASSATO AL METODO 
	public boolean ControlloPresenzaAllenatore(Integer ID) {
		boolean risultato =false;
		String query = "SELECT * FROM corso WHERE Allenatore1 = ? ;";
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, ID.intValue());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				risultato=true;	//ESISTE UNA TUPLA CON QUELL'ID
			else
				risultato=false;//NON ESISTE UNA TUPLA CON QUELL'ID
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return risultato;
	}
	
	protected Corso recordToCorso(ResultSet rs) throws SQLException{
		Corso c = new Corso();
		c.setIdCorso(rs.getInt("idCorso"));
		c.setGiorni(rs.getInt("giorni"));
		c.setOra(rs.getInt("ora"));
		c.setTipo(rs.getString("tipo"));
		c.setAllenatore1(rs.getInt("allenatore1"));
		c.setAllenatore2(rs.getInt("allenatore2"));
		return c;
	}
	
	public Vector<Corso> getAll(){
		String query = "SELECT * FROM corso";
		Vector<Corso> list = new Vector<Corso>();
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Corso c = null;
			while(rs.next()) {
				list.add(recordToCorso(rs));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return list;
	}
	
	/*
	 public Vector<Dipendente> getAllAllenatore1(){
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
	 */
	
	
	
	
	//INSERISCI ISCRIZIONE AL CORSO
	public boolean salvaIscrizioneCorso(int IdCorso, int IdIscritto){
		String query = "INSERT INTO `piscina`.`frequenta` (`idCorso`, `idIscritto`) VALUES ( ?, ?)";
		boolean esito=false;
		conn=DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, IdCorso );
			ps.setInt(2, IdIscritto);
			System.out.println(query);
			int tmp=ps.executeUpdate();
			if (tmp==1)
				esito=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	
	
	//MODEL PER COMBOBOX CORSO
	public DefaultComboBoxModel<Corso> getCorsoCb(){
		DefaultComboBoxModel<Corso> risultato = new DefaultComboBoxModel<Corso>();
		String query = "SELECT * FROM piscina.corso;";
		Corso res = new Corso();
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				res=new Corso();
				
				res.setIdCorso(rs.getInt("idCorso"));
				res.setGiorni(rs.getInt("giorni"));
				res.setOra(rs.getInt("ora"));
				res.setTipo(rs.getString("tipo"));
				res.setAllenatore1(rs.getInt("allenatore1"));
				res.setAllenatore2(rs.getInt("allenatore2"));

				risultato.addElement(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return risultato;

	}
	
	
	
	public Corso getCorsoFromId(int ID) {
		Corso result=new Corso();
		String stringa="SELECT * FROM piscina.corso WHERE idCorso=?";
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(stringa);
			ps.setInt(1, ID );
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			result=recordToCorso( rs);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		System.out.println(result);
		return result;		
	}
	
	
	
	public Vector<Corso> getVectorCorsoFromGiorni(int giorni) {
		Vector<Corso> list = new Vector<Corso>();
		String stringa="SELECT * FROM piscina.corso WHERE Giorni=?";
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(stringa);
			ps.setInt(1, giorni );
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(recordToCorso(rs));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return list;		
	}
	
	public Vector<Corso> getVectorCorsoFromOra(int ora) {
		Vector<Corso> list = new Vector<Corso>();
		String stringa="SELECT * FROM piscina.corso WHERE Ora=?";
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(stringa);
			ps.setInt(1, ora );
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(recordToCorso(rs));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return list;		
	}
	
	public Vector<Corso> getVectorCorsoFromTipo(String tipo) {
		Vector<Corso> list = new Vector<Corso>();
		String stringa="SELECT * FROM piscina.corso WHERE Tipo=?";
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(stringa);
			ps.setString(1, tipo );
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(recordToCorso(rs));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return list;		
	}
	
	public Vector<Corso> getVectorCorsoFromAllenatore1(int ID) {
		Vector<Corso> list = new Vector<Corso>();
		String stringa="SELECT * FROM piscina.corso WHERE Allenatore1=?";
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(stringa);
			ps.setInt(1, ID );
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(recordToCorso(rs));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return list;		
	}
	
	
	
	
	
	
	//ricerca di tutte le iscrizioni
	public Vector<String[]> getAllIscrizioni(){
		String query = "SELECT * FROM frequenta";
		Vector<String[]> listResult = new Vector<String[]>();
		Vector<int[]> list = new Vector<int[]>();
		int idCorso;	//verranno usate per inserirci dentro gli id delle varie tuple così che sarà possibile rislarire a tutti i dati
		int idIscritto;
		
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				int[] iscrizioni=new int[2];
				idCorso=rs.getInt("idCorso");
				idIscritto=rs.getInt("idIscritto");
				iscrizioni[0]=idCorso;
				iscrizioni[1]=idIscritto;
				list.add(iscrizioni);
				/*String[] stringa=new String[7];// I VALORI CHE CONTERRà SARANNO: HO RISCRITTO QUESTO METODO IN MODO ASSESTANTE (infoIscrizione)
				//ISCRITTO: id, nome, cogome e data di nascita; CORSO: id, tipo e ora; 
				idCorso=rs.getInt("idCorso");
				idIscritto=rs.getInt("idIscritto");
				Corso c=getCorsoFromId(idCorso);
				Iscritto i=iDAOP.getIscrittoId(idIscritto);
				//INSERIMENTO VALORI NELL'ARRAY DI STRINGHE PRIMA QUELLI DELL'ISCRITTO E POI QUELLI DEL CORSO
				//STRINGHE PER L'ISCRITTO
				stringa[0]=Integer.toString(i.getIdIscritto());
				stringa[1]=i.getNome();
				stringa[2]=i.getCognome();
				stringa[3]=i.getDataNascita().toString();
				//STRINGHE PER IL CORSO
				stringa[4]=Integer.toString(c.getIdCorso());
				stringa[5]=c.getTipo();
				stringa[6]=Integer.toString(c.getOra());
				list.add(stringa);
				System.out.println("STRINGA AGGIUNTA ALLA LISTA");*/
				}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		//1)HO UN VETTORE DI ARRAY DI STRINGHE PER OGNI ARRAY IL PRIME è L'ID DEL CORSO E IL SECONDO DELL'ISCRITTO
		//2)PER OGNI ARRAY DEL VETTORE CONVERTO IN UN'ARRAY DI STRINGHE DI INFORMAZIONI RELATIVE A QUELL'INFORTUNIO
		//3)INFINE AGGIUNGO QUEST'ARRAY DI STRINGHE DI INFORMAZIONI SULL'ISCRIZIONE AD UNA LISTA
		//2)
		for (int[] i: list ) {
			String[] stringa=new String[7];
			stringa=infoIscrizione(i[0], i[1]);
			//3)
			listResult.add(stringa);
			
		}
			/*System.out.println("PROVA DI SCRITTURA-------------INIZIO------------");
		for (String[] s : listResult) {
			for (int x=0;x<6;x++)
				System.out.println(s[x]);
		}
		System.out.println("PROVA DI SCRITTURA-------------FINE--------------");*/
 
		return listResult;
	}
	
	
	
	//METODO PER LA RICERCA DELLE ISCRIZIONI PER IDISCRITTO
	public Vector<String[]> getIscrizioniFromIdIscritto(int idIscritto){
		String query = "SELECT * FROM frequenta WHERE idIscritto=?";
		Vector<String[]> listResult = new Vector<String[]>();
		Vector<int[]> list = new Vector<int[]>();
		int idCorso;	//verranno usate per inserirci dentro gli id delle varie tuple così che sarà possibile rislarire a tutti i dati		
		PreparedStatement ps;
		conn = DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, idIscritto);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				int[] iscrizioni=new int[2];
				idCorso=rs.getInt("idCorso");
				idIscritto=rs.getInt("idIscritto");
				iscrizioni[0]=idCorso;
				iscrizioni[1]=idIscritto;
				list.add(iscrizioni);
				}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		//1)HO UN VETTORE DI ARRAY DI STRINGHE PER OGNI ARRAY IL PRIME è L'ID DEL CORSO E IL SECONDO DELL'ISCRITTO
		//2)PER OGNI ARRAY DEL VETTORE CONVERTO IN UN'ARRAY DI STRINGHE DI INFORMAZIONI RELATIVE A QUELL'INFORTUNIO
		//3)INFINE AGGIUNGO QUEST'ARRAY DI STRINGHE DI INFORMAZIONI SULL'ISCRIZIONE AD UNA LISTA
		//2)
		for (int[] i: list ) {
			String[] stringa=new String[7];
			stringa=infoIscrizione(i[0], i[1]);
			//3)
			listResult.add(stringa);
			
		}
			/*System.out.println("PROVA DI SCRITTURA-------------INIZIO------------");
		for (String[] s : listResult) {
			for (int x=0;x<6;x++)
				System.out.println(s[x]);
		}
		System.out.println("PROVA DI SCRITTURA-------------FINE--------------");*/
 
		return listResult;
	}
	
	
	
	
	//ricerca di tutte le iscrizioni PER ID DEL CORSO
		public Vector<String[]> getIscrizioniFromIdCorso(int idCorso){
			String query = "SELECT * FROM frequenta WHERE idCorso=?";
			Vector<String[]> listResult = new Vector<String[]>();
			Vector<int[]> list = new Vector<int[]>();
			//verranno usate per inserirci dentro gli id delle varie tuple così che sarà possibile rislarire a tutti i dati
			int idIscritto;
			
			PreparedStatement ps;
			conn = DBManager.startConnection();
			try {
				ps = conn.prepareStatement(query);
				ps.setInt(1, idCorso);
				ResultSet rs = ps.executeQuery();

				while(rs.next()) {
					int[] iscrizioni=new int[2];
					idCorso=rs.getInt("idCorso");
					idIscritto=rs.getInt("idIscritto");
					iscrizioni[0]=idCorso;
					iscrizioni[1]=idIscritto;
					list.add(iscrizioni);
					
					}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			DBManager.closeConnection();
			//1)HO UN VETTORE DI ARRAY DI STRINGHE PER OGNI ARRAY IL PRIME è L'ID DEL CORSO E IL SECONDO DELL'ISCRITTO
			//2)PER OGNI ARRAY DEL VETTORE CONVERTO IN UN'ARRAY DI STRINGHE DI INFORMAZIONI RELATIVE A QUELL'INFORTUNIO
			//3)INFINE AGGIUNGO QUEST'ARRAY DI STRINGHE DI INFORMAZIONI SULL'ISCRIZIONE AD UNA LISTA
			//2)
			for (int[] i: list ) {
				String[] stringa=new String[7];
				stringa=infoIscrizione(i[0], i[1]);
				//3)
				listResult.add(stringa);
				
			}
				/*System.out.println("PROVA DI SCRITTURA-------------INIZIO------------");
			for (String[] s : listResult) {
				for (int x=0;x<6;x++)
					System.out.println(s[x]);
			}
			System.out.println("PROVA DI SCRITTURA-------------FINE--------------");*/
	 
			return listResult;
		}
	
	
	
	
	public String[] infoIscrizione(int idCorso, int idIscritto) {
		String[] stringa=new String[7];// I VALORI CHE CONTERRà SARANNO:
		//ISCRITTO: id, nome, cogome e data di nascita; CORSO: id, tipo e ora; 
		Corso c=getCorsoFromId(idCorso);
		Iscritto i=iDAOP.getIscrittoId(idIscritto);
		//INSERIMENTO VALORI NELL'ARRAY DI STRINGHE PRIMA QUELLI DELL'ISCRITTO E POI QUELLI DEL CORSO
		//STRINGHE PER L'ISCRITTO
		stringa[0]=Integer.toString(i.getIdIscritto());
		stringa[1]=i.getNome();
		stringa[2]=i.getCognome();
		stringa[3]=i.getDataNascita().toString();
		//STRINGHE PER IL CORSO
		stringa[4]=Integer.toString(c.getIdCorso());
		stringa[5]=c.getTipo();
		stringa[6]=Integer.toString(c.getOra());
		System.out.println("STRINGA GENERATA");
		return stringa;
		
	}
	
	

}