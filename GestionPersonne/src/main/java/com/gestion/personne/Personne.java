/**
 * 
 */
package com.gestion.personne;

import java.sql.Date;

/**
 * 
 */
public class Personne {
	private int id;
	private String nom;
	private String prenom;
	private Date date;
	
	public Personne() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param date
	 */
	public Personne(int id, String nom, String prenom, Date date) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.date = date;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
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
