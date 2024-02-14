/**
 * 
 */
package com.gestion.personne;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * 
 */
@RequestScoped
@Named
public class PersonneEJB {
	private Personne personne;
	private List<Personne> listePersonnes;
	private Date date;
	private boolean modif=false;
	private int perId;
	
	 @PostConstruct
	    public void init() {
	        personne = new Personne();
	    }


	public Connection connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdpersonne", "root", "root");
			return con;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			Connection  con = null;
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
			Connection con = null;
			return con;
		}
	}
	
	
	public List<Personne> affichePersonnes() {
		listePersonnes = new ArrayList<Personne>();
		
		String req = "SELECT * FROM personne";
		try {
			PreparedStatement ps = connect().prepareStatement(req);
			ResultSet res = ps.executeQuery();
			
			while(res.next()) {
				Personne pers = new Personne();
				pers.setId(res.getInt("id"));
				pers.setNom(res.getString("nom"));
				pers.setPrenom(res.getString("prenom"));
				pers.setDate(res.getDate("Datenaiss"));
				listePersonnes.add(pers);
			}
			return listePersonnes;
		} catch (SQLException e) {
			e.printStackTrace();
			return listePersonnes;
		}
	}
	
	public void ajoutPersonne() {
	    String req = "INSERT INTO personne(nom, prenom, datenaiss) VALUES (?, ?, ?)";
	    
	    try (Connection connection = connect();
	         PreparedStatement ps = connection.prepareStatement(req)) {

	        // Vérifier que les champs ne sont pas vides
	        if (personne.getNom() == null || personne.getPrenom() == null) {
	            // Gérer l'erreur, par exemple afficher un message à l'utilisateur
	            return;
	        }

	        personne.setDate(convDate(date));
	        
	        ps.setString(1, personne.getNom());
	        ps.setString(2, personne.getPrenom());
	        ps.setDate(3, personne.getDate());
	        
	        ps.execute();
	        
	        affichePersonnes();
	        personne = new Personne();
	        date = null;
	    } catch (SQLException e1) {
	        e1.printStackTrace();
	        // Gérer l'erreur, par exemple afficher un message à l'utilisateur
	    }
	}

	
	public void deletePersonne(Personne per) {
		String req = "delete from personne where id= ?";
		try {
			PreparedStatement ps = connect().prepareStatement(req);
			ps.setInt(1, per.getId());
			ps.execute();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
    public void affiche(Personne per) {
    	perId = per.getId();
    	date = per.getDate();
    	personne = per;
    	modif = true;
    }
    
    public void modifPersonne() {
    	personne.setDate(convDate(date));
    	try {
    		String req = "UPDATE personne SET nom = ?, prenom = ?, datenaiss = ? WHERE id = ?";
    		PreparedStatement ps = connect().prepareStatement(req);
    		ps.setString(1, personne.getNom());
    		ps.setString(2, personne.getPrenom());
    		ps.setDate(3, personne.getDate());
    		ps.setInt(4, perId);
    		
    		System.out.println(ps);
    		
    		ps.executeUpdate();
    		
    		affichePersonnes();
    		personne = new Personne();
    		date = null;
    	} catch (SQLException e1) {
    		e1.printStackTrace();
    	}
    }
    
	
	public java.sql.Date convDate(java.util.Date calendarDate) {
		return new java.sql.Date(calendarDate.getTime());
	}
	
	
	

	/**
	 * @return the perId
	 */
	public int getPerId() {
		return perId;
	}


	/**
	 * @param perId the perId to set
	 */
	public void setPerId(int perId) {
		this.perId = perId;
	}


	/**
	 * @return the modif
	 */
	public boolean isModif() {
		return modif;
	}


	/**
	 * @param modif the modif to set
	 */
	public void setModif(boolean modif) {
		this.modif = modif;
	}


	/**
	 * @return the pistePersonne
	 */
	public List<Personne> getPistePersonne() {
		return affichePersonnes();
	}


	/**
	 * @param pistePersonne the pistePersonne to set
	 */
	public void setPistePersonne(List<Personne> pistePersonne) {
		
	}


	/**
	 * @return the personne
	 */
	public Personne getPersonne() {
		return personne;
	}

	/**
	 * @param personne the personne to set
	 */
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	/**
	 * @return the listePersonne
	 */
	public List<Personne> getListePersonne() {
		return listePersonnes;
	}

	/**
	 * @param listePersonne the listePersonne to set
	 */
	public void setListePersonne(List<Personne> listePersonne) {
		this.listePersonnes = listePersonne;
	}
	

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}


	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
}
