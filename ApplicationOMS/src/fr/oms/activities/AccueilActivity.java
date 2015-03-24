package fr.oms.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class AccueilActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.accueil);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.accueil_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.action_info:
	            onInfoActivity();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void onLaunchAnnuaire(View v){
		Intent intent = new Intent(AccueilActivity.this, AnnuaireActivity.class);
		startActivity(intent);
	}
	
	@Override
	public void onBackPressed() {
		moveTaskToBack(true);
	}
	
	public void onInfoActivity(){
		Intent intent = new Intent(this, InfoActivity.class);
		startActivity(intent); 
	}
	
	public void onLaunchActu(View v){
		Intent intent = new Intent(AccueilActivity.this, AgendaActivity.class);
		startActivity(intent);
	}
	
	public void onLaunchMaPosition(View v){
	}
}
