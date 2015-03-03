package fr.oms.activities;

import fr.oms.DataLoader.GestionDonnees;
import fr.oms.adapter.EquipementAdapter;
import fr.oms.modele.Manager;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

public class ListEquipementActivity extends Activity {

	private ListView listEquipement;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.list_equipement);
		Manager.getInstance().setAccesDonnees(new GestionDonnees());
		Manager.getInstance().lireDonnees();
		
		EquipementAdapter equipementAdapter = new EquipementAdapter(this, 0, Manager.getInstance().getListeEquipement());
		
		listEquipement = (ListView) findViewById(R.id.listeEquipement);
		listEquipement.setAdapter(equipementAdapter);
	}
	
}
