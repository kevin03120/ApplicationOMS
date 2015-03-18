package fr.oms.activities;

import java.util.ArrayList;
import java.util.List;

import fr.oms.adapter.AssociationAdapter;
import fr.oms.metier.Association;
import fr.oms.metier.Sport;
import fr.oms.modele.Manager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListAssociationActivity extends Activity {

	private ListView listeAssociation;
	private int filtre;
	private CheckBox chkAdherent;
	private CheckBox chkNonAdherent;
	private List<Association> mesAssoc;
	private boolean isFiltreSport = false;
	private List<Association> mesAssocFiltreSport;
	private int idSport = 0;
	private TextView txtFiltre;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_association);
		setTitle(getResources().getString(R.string.association));
		filtre = 0;
		if(getIntent().getExtras()!=null){
			isFiltreSport = true;
			idSport = getIntent().getExtras().getInt("idSport");
			filtre = 3;
		}
		txtFiltre = (TextView)findViewById(R.id.filtre);
		chkAdherent = (CheckBox)findViewById(R.id.chkAdherents);
		chkNonAdherent = (CheckBox)findViewById(R.id.chkNonAdherents);
		listeAssociation = (ListView)findViewById(R.id.listeAssociation);
		mesAssoc = rendNouvelleListe();
		mesAssocFiltreSport = new ArrayList<Association>();
		if(isFiltreSport){
			for(Association a : Manager.getInstance().getListeAssociation()){
				for(Sport s : a.getListeSport()){
					if(s.getId() == idSport){
						txtFiltre.setText("Filtre " + s.getNom() + " (Cliquez ici pour le supprimer)");
						txtFiltre.setVisibility(0);
						mesAssocFiltreSport.add(a);
					}
				}
			}
			AssociationAdapter associationAdapter = new AssociationAdapter(this, 0, mesAssocFiltreSport);
			listeAssociation.setAdapter(associationAdapter);
			touchAssoc();
		}
		else{
			AssociationAdapter associationAdapter = new AssociationAdapter(this, 0, Manager.getInstance().getListeAssociation());
			System.out.println("Je passe la liste des assoc "+Manager.getInstance().getListeAssociation().size());
			listeAssociation.setAdapter(associationAdapter);
			touchAssoc();
		}
	}
	
	private void touchAssoc(){
		listeAssociation.setOnItemClickListener(new ListView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				List<Association> mesAssociationsAdherentes = new ArrayList<Association>();
				List<Association> mesAssociationsNonAdherentes = new ArrayList<Association>();
				List<Association> mesAssociationsAdherentesFiltresSport = new ArrayList<Association>();
				List<Association> mesAssociationsNonAdherentesFiltresSport  = new ArrayList<Association>();
				for(Association a : Manager.getInstance().getListeAssociation()){
					if(a.isAdherent()){
						mesAssociationsAdherentes.add(a);
						if(mesAssocFiltreSport.contains(a)){
							mesAssociationsAdherentesFiltresSport.add(a);
						}
					}
					else{
						mesAssociationsNonAdherentes.add(a);
						if(mesAssocFiltreSport.contains(a)){
							mesAssociationsNonAdherentesFiltresSport.add(a);
						}
					}
				}
				Association assoc = null;
				switch(ListAssociationActivity.this.filtre){
				case 0 : assoc = Manager.getInstance().getListeAssociation().get(position); break;
				case 1 : assoc = mesAssociationsAdherentes.get(position); break;
				case 2 : assoc = mesAssociationsNonAdherentes.get(position); break;
				case 3 : assoc = mesAssocFiltreSport.get(position); break;
				case 4 : assoc = mesAssociationsNonAdherentesFiltresSport.get(position); break;
				case 5 : assoc = mesAssociationsAdherentesFiltresSport.get(position); break;
				}
				if(assoc.isAdherent()){
					Intent intent = new Intent(ListAssociationActivity.this, AssociationActivity.class);
					intent.putExtra("position", assoc.getUid());
					startActivity(intent);
				}
				else{
					Toast.makeText(ListAssociationActivity.this, ListAssociationActivity.this.getResources().getString(R.string.pasAdherent), Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	public void onDeleteFiltre(View v){
		isFiltreSport = false;
		afficheListe();
		txtFiltre.setVisibility(4);
	}
	
	public void onClickChkAdherent(View v){
		if(!chkNonAdherent.isChecked()){
			chkAdherent.setChecked(true);
		}
		afficheListe();
	}
	
	public void onClickChkNonAdherent(View v){
		if(!chkAdherent.isChecked()){
			chkNonAdherent.setChecked(true);
		}
		afficheListe();
	}
	
	private void afficheListe(){
		if(!isFiltreSport){
			if(chkNonAdherent.isChecked()){
				if(chkAdherent.isChecked()){
					filtre = 0;
				}
				else{
					filtre = 2;
				}
			}
			else{
				filtre = 1;
			}
		}
		else{
			if(chkNonAdherent.isChecked()){
				if(chkAdherent.isChecked()){
					filtre = 3;
				}
				else{
					filtre = 4;
				}
			}
			else{
				filtre = 5;
			}
		}
		mesAssoc = rendNouvelleListe();
		rentreAssociationDansListeSelonFiltre(mesAssoc);
		AssociationAdapter associationAdapter = new AssociationAdapter(this, 0, mesAssoc);
		listeAssociation.setAdapter(associationAdapter);
	}
	
	private List<Association> rendNouvelleListe(){
		List<Association> assocs = new ArrayList<Association>();
		for(Association a : Manager.getInstance().getListeAssociation()){
			assocs.add(a);
		}
		return assocs;
	}
	
	private void rentreAssociationDansListeSelonFiltre(List<Association> mesAssocs){
		boolean sportExist = false;
		switch(filtre){
		case 0 : mesAssocs = Manager.getInstance().getListeAssociation();
				break;
		case 1 : for(Association a : Manager.getInstance().getListeAssociation()){
					if(!a.isAdherent()){
						mesAssocs.remove(a);
					}
				}
				break;
		case 2 : for(Association a : Manager.getInstance().getListeAssociation()){
					if(a.isAdherent()){
						mesAssocs.remove(a);
					}
				}
				break;
		case 3 : for(Association a : Manager.getInstance().getListeAssociation()){
						for(Sport s : a.getListeSport()){
							if(s.getId() == idSport){
								sportExist = true;
							}
						}
						if(!sportExist){
							mesAssocs.remove(a);
						}
						sportExist = false;
				 }
				break;
		
		case 4 : for(Association a : Manager.getInstance().getListeAssociation()){
					if(a.isAdherent()){
						mesAssocs.remove(a);
					}
					if(mesAssocs.contains(a)){
						for(Sport s : a.getListeSport()){
							if(s.getId() == idSport){
								sportExist = true;
							}
						}
						if(!sportExist){
							mesAssocs.remove(a);
						}
						sportExist = false;
					}
				}
				break;
		case 5 : for(Association a : Manager.getInstance().getListeAssociation()){
					if(!a.isAdherent()){
						mesAssocs.remove(a);
					}
					if(mesAssocs.contains(a)){
						for(Sport s : a.getListeSport()){
							if(s.getId() == idSport){
								sportExist = true;
							}
						}
						if(!sportExist){
							mesAssocs.remove(a);
						}
						sportExist = false;
					}
				}
				break;
		}
	}
	
}
