package fr.oms.activities;

import java.util.ArrayList;
import java.util.List;

import fr.oms.DataLoader.GestionDonnees;
import fr.oms.adapter.AssociationAdapter;
import fr.oms.metier.Association;
import fr.oms.modele.Manager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

public class ListAssociationActivity extends Activity {

	private ListView listeAssociation;
	private int filtre;
	private CheckBox chkAdherent;
	private CheckBox chkNonAdherent;
	private List<Association> mesAssoc;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.list_association);
		Manager.getInstance().setAccesDonnees(new GestionDonnees());
		Manager.getInstance().lireDonnees();
		filtre = 0;
		chkAdherent = (CheckBox)findViewById(R.id.chkAdherents);
		chkNonAdherent = (CheckBox)findViewById(R.id.chkNonAdherents);
		listeAssociation = (ListView)findViewById(R.id.listeAssociation);
		mesAssoc = rendNouvelleListe();
		AssociationAdapter associationAdapter = new AssociationAdapter(this, 0, Manager.getInstance().getListeAssociation());
		listeAssociation.setAdapter(associationAdapter);
		touchAssoc();
	}
	
	private void touchAssoc(){
		listeAssociation.setOnItemClickListener(new ListView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Association assoc = Manager.getInstance().getListeAssociation().get(position);
				if(assoc.isAdherent()){
					Intent intent = new Intent(ListAssociationActivity.this, AssociationActivity.class);
					intent.putExtra("position", position);
					startActivity(intent);
				}
				else{
					Toast.makeText(ListAssociationActivity.this, ListAssociationActivity.this.getResources().getString(R.string.pasAdherent), Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	public void onClickChkAdherent(View v){
		if(!chkNonAdherent.isChecked()){
			chkAdherent.setChecked(true);
		}
		AfficheListe();
	}
	
	public void onClickChkNonAdherent(View v){
		if(!chkAdherent.isChecked()){
			chkNonAdherent.setChecked(true);
		}
		AfficheListe();
	}
	
	private void AfficheListe(){
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
		}
	}
	
}
