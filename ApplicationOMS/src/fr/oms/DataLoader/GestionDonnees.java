package fr.oms.DataLoader;

import java.util.ArrayList;
import java.util.List;

import fr.oms.metier.Association;
import fr.oms.metier.Discipline;
import fr.oms.metier.Equipement;
import fr.oms.metier.Quartier;
import fr.oms.metier.Sport;
import fr.oms.modele.Manager;
import fr.oms.modele.iAccesDonnees;

public class GestionDonnees implements iAccesDonnees{

	@Override
	public void lireDonnees() {
		Manager.getInstance().getListeAssociation().clear();
		Manager.getInstance().getListeEquipement().clear();
		Manager.getInstance().getListeDiscipline().clear();
		Manager.getInstance().getListeQuartier().clear();
		List<Equipement> mesEquipements = new ArrayList<Equipement>();
//		Association association = new Association(0,"Association 1",true,mesEquipements,null,null);
//		Manager.getInstance().getListeAssociation().add(association);
//		association = new Association(2,"Association 2",false,mesEquipements,null,null);
//		Manager.getInstance().getListeAssociation().add(association);
//		association = new Association(3,"Association 3",true,mesEquipements,null,null);
//		Manager.getInstance().getListeAssociation().add(association);
//		association = new Association(4,"Association 4",false,mesEquipements,null,null);
//		Manager.getInstance().getListeAssociation().add(association);
//		association = new Association(5,"Association 5",true,mesEquipements,null,null);
//		Manager.getInstance().getListeAssociation().add(association);
//		association = new Association(6,"Association 6",false,mesEquipements,null,null);
//		Manager.getInstance().getListeAssociation().add(association);
//		association = new Association(7,"Association 7",true,mesEquipements,null,null);
//		Manager.getInstance().getListeAssociation().add(association);
//		association = new Association(8,"Association 8", false,mesEquipements,null,null);
//		Manager.getInstance().getListeAssociation().add(association);
//		association = new Association(9,"Association 9",true,mesEquipements,null,null);
//		Manager.getInstance().getListeAssociation().add(association);
//		association = new Association(10,"Association 10",false,mesEquipements,null,null);
//		Manager.getInstance().getListeAssociation().add(association);
		
		List<Equipement> lesEquipements = new ArrayList<Equipement>();
		Equipement equipement = new Equipement(0, "Salle 1", "", null);
		lesEquipements.add(equipement);
		Manager.getInstance().getListeEquipement().add(equipement);
		equipement = new Equipement(1, "Gymnase 1", "", null);
		lesEquipements.add(equipement);
		Manager.getInstance().getListeEquipement().add(equipement);
		equipement = new Equipement(2, "Stade Marcel Michelin", "", null);
		lesEquipements.add(equipement);
		Manager.getInstance().getListeEquipement().add(equipement);
		equipement = new Equipement(3, "Parc des sports", "", null);
		lesEquipements.add(equipement);
		Manager.getInstance().getListeEquipement().add(equipement);
		equipement = new Equipement(4, "Boulodrome", "", null);
		lesEquipements.add(equipement);
		Manager.getInstance().getListeEquipement().add(equipement);
		equipement = new Equipement(5, "Salle 2", "", null);
		lesEquipements.add(equipement);
		Manager.getInstance().getListeEquipement().add(equipement);
		
		List<Sport> mesSports = new ArrayList<Sport>();
		Sport sport = new Sport(0, "Basket");
		mesSports.add(sport);
		sport = new Sport(1, "Football");
		mesSports.add(sport);
		sport = new Sport(2, "Rugby");
		mesSports.add(sport);
		sport = new Sport(3, "Tennis");
		mesSports.add(sport);
		
		Discipline discipline = new Discipline(0, "Sport collectif exterieur", mesSports);
		Manager.getInstance().getListeDiscipline().add(discipline);
		discipline = new Discipline(1, "Sport individuel exterieur", mesSports);
		Manager.getInstance().getListeDiscipline().add(discipline);
		discipline = new Discipline(2, "Sport collectif en salle", mesSports);
		Manager.getInstance().getListeDiscipline().add(discipline);
		discipline = new Discipline(3, "Sport individuel en salle", mesSports);
		Manager.getInstance().getListeDiscipline().add(discipline);
		discipline = new Discipline(4, "Sport de nature", mesSports);
		Manager.getInstance().getListeDiscipline().add(discipline);
		
		Quartier quartier = new Quartier(0, "Croix de Neyrat", lesEquipements);
		Manager.getInstance().getListeQuartier().add(quartier);
		quartier = new Quartier(1, "Jaude", lesEquipements);
		Manager.getInstance().getListeQuartier().add(quartier);
		quartier = new Quartier(2, "Montferrand", lesEquipements);
		Manager.getInstance().getListeQuartier().add(quartier);
		quartier = new Quartier(3, "Saint Jacques", lesEquipements);
		Manager.getInstance().getListeQuartier().add(quartier);
	}

}
