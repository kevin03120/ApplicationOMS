package fr.oms.DataLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import fr.oms.activities.R;
import fr.oms.metier.Association;
import fr.oms.metier.Equipement;
import fr.oms.metier.Personne;
import fr.oms.metier.Sport;
import fr.oms.modele.Manager;

public class CSVParser {
	
	private InputStreamReader reader=null;
	private BufferedReader br = null;
	private String line ="";
	private Pattern pattern=Pattern.compile("\\d{4}");
	
	public CSVParser(Context context) {
		br=new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.export)));
	}
	
	
	public void readCSV(){
//		try {
//			br=new BufferedReader(new FileReader(new File(filePath)));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}		
		try {
			line=br.readLine();
			line=br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		while(!line.equals("")){
			String[]mots=line.replace("\"", "").split(";");			
			
			Matcher matcher=pattern.matcher(mots[0]);			
			if(mots.length>6 && matcher.matches()){
				System.out.println("je r�cup�re l'association n�"+mots[0]);
				recupererAssociation(mots);
			}			
			try {
				line=br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(line==null){
				System.out.println("Fin du parsing des donn�es");
				System.out.println("Nombre d'associations r�cup�r�es : "+Manager.getInstance().getListeAssociation().size());
				break;
			}
		}
	}


	private void recupererAssociation(String[] mots) {
		// Index 0 = ID de l'association
		
			int id=Integer.valueOf(mots[0]);
			String nom=mots[1];
			boolean adherent=false;
			if(mots[3].equals("Adh�rent")){
				adherent=true;
			}
			List<Equipement> equipements=recupererEquipement(mots);
			SportParser parser=new SportParser();
			List<Sport> sports=null;//parser.parse(mots[8]);
			Personne personne=recupererPersonne(mots);
			String horraire="non communiqu�";
			if(mots.length>20){
				horraire=mots[21];		
			}
			Manager.getInstance().getListeAssociation().add(new Association(id, nom, adherent, equipements, horraire, sports, personne));
	
		
	}


	private Personne recupererPersonne(String[] mots) {
		if(mots.length>30){
			Personne tmp = Manager.getInstance().recupereUnePersonneAPartirDuMail(mots[29]);
			if(tmp!=null){
				return tmp;
			}
			int id=Manager.getInstance().getListPersonne().size();
			String titre=mots[23];
			String nom=mots[24];
			String prenom=mots[25];
			String adresse=mots[26];
			String codePostal=mots[27];
			String ville = mots[28];
			String email=mots[29];
			String telfixe=mots[30];
			String telport=mots[31];
			
			tmp=new Personne(id, titre, nom, prenom, adresse, codePostal, ville, email, telfixe, telport);
			Manager.getInstance().getListPersonne().add(tmp);
			
			return tmp;
		}
		return null;
		
		
	}


	private List<Equipement> recupererEquipement(String[] mots) {
		ArrayList<Equipement> listeEquipements=new ArrayList<Equipement>();
		Equipement equip1=Manager.getInstance().recupererEquipementAPartirDuNom(mots[6]);
		Equipement equip2=Manager.getInstance().recupererEquipementAPartirDuNom(mots[7]);
		
		if(equip1!=null && equip2!=null){
			listeEquipements.add(equip1);
			listeEquipements.add(equip2);
			return listeEquipements;
		}		
		
		if(equip1==null){
			equip1=readEquipement(mots[6]);
		}
		
		if(equip2==null){
			equip2=readEquipement(mots[7]);
		}
		
		listeEquipements.add(equip1);
		listeEquipements.add(equip2);
		
		return listeEquipements;
	}


	private Equipement readEquipement(String mots) {
		int id=Manager.getInstance().getListeEquipement().size();
		String nom=mots;
		return null;
	}

}
