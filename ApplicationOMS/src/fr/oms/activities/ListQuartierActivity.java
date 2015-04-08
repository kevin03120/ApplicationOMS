package fr.oms.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import fr.oms.adapter.QuartierAdapter;
import fr.oms.metier.Equipement;
import fr.oms.metier.Quartier;
import fr.oms.modele.Manager;

public class ListQuartierActivity extends Activity {
	
	private ListView listeQuartier;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_quartier);
		setTitle(getResources().getString(R.string.quartier));
		getActionBar().setDisplayHomeAsUpEnabled(true);		
		listeQuartier = (ListView)findViewById(R.id.listeQuartier);
		QuartierAdapter quartierAdapter = new QuartierAdapter(this, 0, Manager.getInstance().getListeQuartier());
		listeQuartier.setAdapter(quartierAdapter);
		donneEquipement();
	}
	
	public void donneEquipement(){
		listeQuartier.setOnItemClickListener(new ListView.OnItemClickListener(){
			
			private ListView listeDialog;
			private Quartier quartier;
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Dialog dialog = new Dialog(ListQuartierActivity.this);
				dialog.setContentView(R.layout.dialog);
			    quartier = Manager.getInstance().getListeQuartier().get(position);
				dialog.setTitle(quartier.getNom() + " : ");
				
				listeDialog = (ListView)dialog.findViewById(R.id.listeDialog);
				List<Equipement> mesEquipements = quartier.getMesEquipements();
				List<String> mesNomsEquipements = new ArrayList<String>();
				for(Equipement e : mesEquipements){
					mesNomsEquipements.add(e.getNom());
				}
				ArrayAdapter<String> nomEquipementAdapter = new ArrayAdapter<String>(ListQuartierActivity.this, android.R.layout.simple_list_item_1, mesNomsEquipements);
				listeDialog.setAdapter(nomEquipementAdapter);
				clicSurUnEquipement();
				dialog.show();
			}
			
			private void clicSurUnEquipement(){
				listeDialog.setOnItemClickListener(new ListView.OnItemClickListener(){
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						Equipement equipement = quartier.getMesEquipements().get(position);
						Toast.makeText(ListQuartierActivity.this, equipement.getNom(), Toast.LENGTH_SHORT).show();
					}
				});
			}
		});
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            NavUtils.navigateUpFromSameTask(this);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
}
