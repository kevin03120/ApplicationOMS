package fr.oms.metier;

import java.util.List;

public class Discipline {

	private int uid;
	private String nom;
	private List<Sport> listeSport;
	
	public Discipline(int unId, String unNom){
		uid = unId;
		nom = unNom;
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

	public List<Sport> getListeSport() {
		return listeSport;
	}

	public void setListeSport(List<Sport> listeSport) {
		this.listeSport = listeSport;
	}
}
