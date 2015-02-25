package fr.oms.metier;

import java.util.List;

public class Association {

	private int uid;
	private String nom;
	private boolean adherent;
	private List<Equipement> listeEquipement;
	private Sport sport;
	private Personne contact;
	
	public Association(int unId, String unNom, boolean adherent, List<Equipement> uneListeEquipement, Sport unSport, Personne unContact){
		uid = unId;
		nom = unNom;
		this.adherent = adherent;
		if(uneListeEquipement.size() > 0){
			listeEquipement = uneListeEquipement;
		}
		else{
			listeEquipement = null;
		}
		sport = unSport;
		contact = unContact;
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

	public boolean isAdherent() {
		return adherent;
	}

	public void setAdherent(boolean adherent) {
		this.adherent = adherent;
	}

	public List<Equipement> getListeEquipement() {
		return listeEquipement;
	}

	public void setListeEquipement(List<Equipement> listeEquipement) {
		this.listeEquipement = listeEquipement;
	}

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public Personne getContact() {
		return contact;
	}

	public void setContact(Personne contact) {
		this.contact = contact;
	}
	
	
}
