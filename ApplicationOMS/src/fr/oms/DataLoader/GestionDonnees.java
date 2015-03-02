package fr.oms.DataLoader;

import java.util.ArrayList;
import java.util.List;

import fr.oms.metier.Association;
import fr.oms.metier.Equipement;
import fr.oms.modele.Manager;
import fr.oms.modele.iAccesDonnees;

public class GestionDonnees implements iAccesDonnees{

	@Override
	public void lireDonnees() {
		List<Equipement> mesEquipements = new ArrayList<Equipement>();
		Association association = new Association(0,"Association 1",true,mesEquipements,null,null);
		Manager.getInstance().getListeAssociation().add(association);
		association = new Association(2,"Association 2",false,mesEquipements,null,null);
		Manager.getInstance().getListeAssociation().add(association);
		association = new Association(3,"Association 3",true,mesEquipements,null,null);
		Manager.getInstance().getListeAssociation().add(association);
		association = new Association(4,"Association 4",false,mesEquipements,null,null);
		Manager.getInstance().getListeAssociation().add(association);
		association = new Association(5,"Association 5",true,mesEquipements,null,null);
		Manager.getInstance().getListeAssociation().add(association);
		association = new Association(6,"Association 6",false,mesEquipements,null,null);
		Manager.getInstance().getListeAssociation().add(association);
		association = new Association(7,"Association 7",true,mesEquipements,null,null);
		Manager.getInstance().getListeAssociation().add(association);
		association = new Association(8,"Association 8", false,mesEquipements,null,null);
		Manager.getInstance().getListeAssociation().add(association);
		association = new Association(9,"Association 9",true,mesEquipements,null,null);
		Manager.getInstance().getListeAssociation().add(association);
		association = new Association(10,"Association 10",false,mesEquipements,null,null);
		Manager.getInstance().getListeAssociation().add(association);
	}

}
