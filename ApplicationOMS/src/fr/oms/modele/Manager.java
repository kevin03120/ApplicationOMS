package fr.oms.modele;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import fr.oms.activities.R;
import fr.oms.metier.Association;
import fr.oms.metier.Discipline;
import fr.oms.metier.Equipement;
import fr.oms.metier.Personne;
import fr.oms.metier.Quartier;
import fr.oms.metier.Sport;

public class Manager {

	private static Manager INSTANCE;
	private List<Discipline> listeDiscipline;
	private List<Association> listeAssociation;
	private List<Equipement> listeEquipement;
	private List<Quartier> listeQuartier;
	private List<Sport> listeSport;
	private List<Personne> listPersonne;
	private iAccesDonnees accesDonnees;
	
	private Manager(){
		listeDiscipline = new ArrayList<Discipline>();
		listeAssociation = new ArrayList<Association>();
		listeEquipement = new ArrayList<Equipement>();
		listeQuartier = new ArrayList<Quartier>();
		listeSport = new ArrayList<Sport>();
		listPersonne = new ArrayList<Personne>();
		//getTousLesSport();
	}
	
	public void getTousLesSport(Context context) {
		BufferedReader br = null;
		String line="";		
		int idSport=0;
		
		br = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.sports)));		
		try {
			line = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		while (line!=null && !line.equals("")) {
			listeSport.add(new Sport(idSport,line.trim()));		
			try {
				line=br.readLine();
				idSport++;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public List<Sport> getListeSport() {
		return listeSport;
	}

	public void setListeSport(List<Sport> listeSport) {
		this.listeSport = listeSport;
	}

	public Sport recupereLeSport(String compare) {
		for(Sport s:listeSport){
			if(s.getNom().equals(compare)){
				return s;
			}
		}
		return null;
	}

	public List<Personne> getListPersonne() {
		return listPersonne;
	}

	public void setListPersonne(List<Personne> listPersonne) {
		this.listPersonne = listPersonne;
	}

	public Personne recupereUnePersonneAPartirDuMail(String mail) {
		for(Personne p : listPersonne){
			if(p.getEmail().equals(mail)){
				return p;
			}
		}
		return null;
	}

	public Equipement recupererEquipementAPartirDuNom(String nom) {
		for(Equipement e:listeEquipement){
			if(e.getNom().equals(nom)){
				return e;
			}
		}
		return null;
	}

}
