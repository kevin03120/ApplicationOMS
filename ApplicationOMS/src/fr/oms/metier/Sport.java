package fr.oms.metier;

public class Sport {

	private int id;
	private String nom;
	
	public Sport(int unId, String unNom){
		id = unId;
		nom = unNom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
