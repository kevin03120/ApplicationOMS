package fr.oms.modele;

import java.util.ArrayList;
import java.util.List;

import fr.oms.metier.*;

public class Manager {

	private static Manager INSTANCE;
	private List<Discipline> listeDiscipline;
	private List<Association> listeAssociation;
	private List<Equipement> listeEquipement;
	private List<Quartier> listeQuartier;
	private iAccesDonnees accesDonnees;
	
	private Manager(){
		listeDiscipline = new ArrayList<Discipline>();
		listeAssociation = new ArrayList<Association>();
		listeEquipement = new ArrayList<Equipement>();
		listeQuartier = new ArrayList<Quartier>();
	}
	
	public static Manager getInstance(){
		if(INSTANCE == null){
			INSTANCE = new Manager();
		}
		return INSTANCE;
	}
	

	public List<Discipline> getListeDiscipline() {
		return listeDiscipline;
	}

	public void setListeDiscipline(List<Discipline> listeDiscipline) {
		this.listeDiscipline = listeDiscipline;
	}

	public List<Association> getListeAssociation() {
		return listeAssociation;
	}

	public void setListeAssociation(List<Association> listeAssociation) {
		this.listeAssociation = listeAssociation;
	}

	public List<Equipement> getListeEquipement() {
		return listeEquipement;
	}

	public void setListeEquipement(List<Equipement> listeEquipement) {
		this.listeEquipement = listeEquipement;
	}

	public List<Quartier> getListeQuartier() {
		return listeQuartier;
	}

	public void setListeQuartier(List<Quartier> listeQuartier) {
		this.listeQuartier = listeQuartier;
	}

	public iAccesDonnees getAccesDonnees() {
		return accesDonnees;
	}

	public void setAccesDonnees(iAccesDonnees accesDonnees) {
		this.accesDonnees = accesDonnees;
	}
	
	public void lireDonnees(){
		accesDonnees.lireDonnees();
	}

}
