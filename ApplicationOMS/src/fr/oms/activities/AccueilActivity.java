package fr.oms.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class AccueilActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.accueil);
	}
	
	public void onInfoActivity(View v){
		Intent intent = new Intent(this, InfoActivity.class);
		startActivity(intent);
	}
	
	public void onAssociationActivity(View v){
		Intent intent = new Intent(this, ListAssociationActivity.class);
		startActivity(intent);
	}
	
	public void onEquipementActivity(View v){
		Intent intent = new Intent(this, ListEquipementActivity.class);
		startActivity(intent);
	}
	
	public void onDisciplineActivity(View v){
		Intent intent = new Intent(this, ListDisciplineActivity.class);
		startActivity(intent);
	}
	
	public void onQuartierActivity(View v){
		Intent intent = new Intent(this, ListQuartierActivity.class);
		startActivity(intent);
	}
}
