package fr.oms.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AccueilActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accueil);
	}
	
	public void onLaunchAnnuaire(View v){
		Intent intent = new Intent(AccueilActivity.this, AnnuaireActivity.class);
		startActivity(intent);
	}
	
	public void onInfoActivity(View v){
		Intent intent = new Intent(this, InfoActivity.class);
		startActivity(intent); 
	}
	
	public void onLaunchActu(View v){
	}
	
	public void onLaunchMaPosition(View v){
		Intent intent = new Intent(AccueilActivity.this, AnnuaireActivity.class);
		startActivity(intent);
	}
}
