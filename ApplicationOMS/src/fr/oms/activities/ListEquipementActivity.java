package fr.oms.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ListView;
import fr.oms.adapter.EquipementAdapter;
import fr.oms.modele.Manager;

public class ListEquipementActivity extends Activity {

	private ListView listEquipement;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_equipement);
		setTitle(getResources().getString(R.string.equipement));
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		EquipementAdapter equipementAdapter = new EquipementAdapter(this, 0, Manager.getInstance().getListeEquipement());
		
		listEquipement = (ListView) findViewById(R.id.listeEquipement);
		listEquipement.setAdapter(equipementAdapter);
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
