package fr.oms.activities;

import fr.oms.adapter.EquipementAdapter;
import fr.oms.modele.Manager;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ListEquipementActivity extends Activity {

	private ListView listEquipement;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_equipement);
		setTitle(getResources().getString(R.string.equipement));
		
		EquipementAdapter equipementAdapter = new EquipementAdapter(this, 0, Manager.getInstance().getListeEquipement());
		
		listEquipement = (ListView) findViewById(R.id.listeEquipement);
		listEquipement.setAdapter(equipementAdapter);
	}
	
}
