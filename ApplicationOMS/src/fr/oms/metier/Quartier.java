package fr.oms.metier;

import fr.oms.modele.Manager;

public class Quartier {

	private int uid;
	private String nom;
	
	public Quartier(int unId, String unNom){
		setUid(unId);
		setNom(unNom);
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
