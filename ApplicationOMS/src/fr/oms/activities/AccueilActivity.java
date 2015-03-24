package fr.oms.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
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
	        case R.id.plan_oms:
	        	onPlanOMS();
	            return true;
	        case R.id.guide_sport:
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void onPlanOMS(){
		String url = getResources().getString(R.string.lien_plan_OMS);
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}
	
	public void onGuideSport(){
		String url = getResources().getString(R.string.lien_guide_sport);
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
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
