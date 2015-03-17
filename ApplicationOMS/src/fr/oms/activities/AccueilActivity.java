package fr.oms.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class AccueilActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.accueil);
	}
	
	public void onLaunchAnnuaire(View v){
		Intent intent = new Intent(AccueilActivity.this, AnnuaireActivity.class);
		startActivity(intent);
	}
	
	public void onLaunchActu(View v){
	}
	
	public void onLaunchMaPosition(View v){
	}
}
